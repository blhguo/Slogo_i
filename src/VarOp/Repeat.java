package VarOp;

import java.util.List;
import java.util.Map;

import treenode.NumberNode;
import treenode.SlogoNode;
import turtle.Turtle;

public class Repeat extends SlogoNode{
	private double value = 1.0;

	public Repeat() {
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
		// TODO Auto-generated method stub
		List<SlogoNode> leaf = this.getChildren();
		double expr = leaf.get(0).getValue(VarMap, FunctMap, turtle);
		SlogoNode commands = leaf.get(1);
		for (double i = value; i < expr+1; i++ ) {
			VarMap.put("repcout", value);
			commands.getExecute(VarMap, FunctMap, turtle);
		}
		return expr;
	}
}
