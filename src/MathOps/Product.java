package MathOps;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Product extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
	public Product() {
		numchildren = 2;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		List<SlogoNode> leaf = this.getChildren();
		double x = leaf.get(0).getExecute(VarMap, FunctMap, turtle);
		double y = leaf.get(1).getExecute(VarMap, FunctMap, turtle);
		return x * y;
		}
	
}