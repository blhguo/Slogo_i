package treenode;

import java.util.Map;

import turtle.Turtle;

/*
 * basic node for storing a number value, commonly used for storing argument values
 */
public class NumberNode extends SlogoNode {
	
	private double val;


	public NumberNode(double n) {
		numchildren = 0;
		this.val = n;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		return getValue(VarMap, FunctMap, turtle);
	}

	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		return this.val;
	}
	
	
	
	
}
