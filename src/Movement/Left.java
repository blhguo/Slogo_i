package Movement;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Left extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	public Left(){
		numchildren = 1;
	}
	private void left(Map<Integer, Turtle> turtleMap, double angle) {
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive()) {
		turtleMap.get(n).setHeading(turtleMap.get(n).getHeading() + angle); //TODO: Update according to Jamie's stuff
	}}}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtleMap);
		left(turtleMap, step);
		return step;  //returns the final value of the node
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		List<SlogoNode> leaf = this.getChildren();
		return leaf.get(0).getExecute(VarMap, FunctMap, turtleMap);
	}
	
}
