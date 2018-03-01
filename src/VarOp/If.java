package VarOp;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class If extends SlogoNode {
	public If() {
		numchildren = 2;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		return step;
	}


	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		List<SlogoNode> leaf = this.getChildren();
		double expr = leaf.get(0).getValue(VarMap, FunctMap, turtle);
		SlogoNode command = leaf.get(1);
		if (expr != 0) {
			command.getExecute(VarMap, FunctMap, turtle);
			return command.getValue(VarMap, FunctMap, turtle);
		}
		return 0;
	}

}
