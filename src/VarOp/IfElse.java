package VarOp;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class IfElse extends SlogoNode{

	public IfElse() {
		numchildren = 3;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		double step = getValue(VarMap, FunctMap, turtle);
		return step;
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		List<SlogoNode> leaf = this.getChildren();
		double expr = leaf.get(0).getValue(VarMap, FunctMap, turtle);
		SlogoNode trueCommand = leaf.get(1);
		SlogoNode falseCommand = leaf.get(2);
		if (expr != 0) {
			trueCommand.getExecute(VarMap, FunctMap, turtle);
			return trueCommand.getValue(VarMap, FunctMap, turtle);
		} else {
			falseCommand.getExecute(VarMap, FunctMap, turtle);
			return falseCommand.getValue(VarMap, FunctMap, turtle);
		}
	}
}
