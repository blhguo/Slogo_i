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

	private void backward(Turtle turtle, double distance) {
		Point2D point = new Point2D(turtle.getLocation().getX() + distance * Math.sin(Math.toRadians(turtle.getHeading())),
				turtle.getLocation().getY() + distance * Math.cos(Math.toRadians((turtle.getHeading()))));
		turtle.setLocation(point);
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		backward(turtle, step);
		return step;  //returns the final value of the node
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		List<SlogoNode> leaf = this.getChildren();
		return leaf.get(0).getExecute(VarMap, FunctMap, turtle);
	}
	
}
