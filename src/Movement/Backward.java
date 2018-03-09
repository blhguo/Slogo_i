package Movement;

import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import treenode.SlogoNode;
import turtle.Turtle;

public class Backward extends SlogoNode{
	
//	private double value = 0;
//	private double distance = 0;
	public Backward() {
		numchildren = 1;
	}

	private void backward(Map<Integer, Turtle> turtleMap, double distance) {
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive) {
		Point2D point = new Point2D(turtleMap.get(n).getLocation().getX() + distance * Math.sin(Math.toRadians(turtleMap.get(n).getHeading())),
		turtleMap.get(n).getLocation().getY() + distance * Math.cos(Math.toRadians((turtleMap.get(n).getHeading()))));
		turtleMap.get(n).setLocation(point);
			}
		}
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtleMap);
		backward(turtleMap, step);
		return step;  //returns the final value of the node
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		List<SlogoNode> leaf = this.getChildren();
		return leaf.get(0).getExecute(VarMap, FunctMap, turtleMap);
	}
	
}
