package VarOp;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class For extends SlogoNode{

	public For() {
		numchildren = 5;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		double step = getValue(VarMap, FunctMap, turtle);
		return step;
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		List<SlogoNode> leaf = this.getChildren();
		double variable = leaf.get(0).getValue(VarMap, FunctMap, turtle);
		double start = leaf.get(1).getValue(VarMap, FunctMap, turtle);
		double end = leaf.get(2).getValue(VarMap, FunctMap, turtle);
		double increment = leaf.get(3).getValue(VarMap, FunctMap, turtle);
		SlogoNode command = leaf.get(4);
		if (command != null) {
			for (double i = start; i < end; i+= increment) {
				command.getExecute(VarMap, FunctMap, turtle);
			}
			return command.getValue(VarMap, FunctMap, turtle);
		}
		return 0;
	}

}
