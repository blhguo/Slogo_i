package MathOps;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Difference extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
	public Difference() {
		numchildren = 2;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtleMap);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		List<SlogoNode> leaf = this.getChildren();
		double x = leaf.get(0).getExecute(VarMap, FunctMap, turtleMap);
		double y = leaf.get(1).getExecute(VarMap, FunctMap, turtleMap);
		return x - y;
		}
	
}