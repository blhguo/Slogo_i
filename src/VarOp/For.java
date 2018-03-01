package VarOp;

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
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Turtle turtle) {
		// TODO Auto-generated method stub
		return 0;
	}

}
