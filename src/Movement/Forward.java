package Movement;


import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import treenode.SlogoNode;
import turtle.Turtle;

public class Forward extends SlogoNode{
	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}

	private void forward(Turtle turtle, double distance) {
        Point2D newlocation = new Point2D(turtle.getLocation().getX() + distance * Math.sin(turtle.getHeading()),
                turtle.getLocation().getY() + distance * Math.cos(turtle.getHeading()));
        turtle.setLocation(newlocation);
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		forward(turtle, step);
		return step;  //returns the final value of the node
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		List<SlogoNode> leaf = this.getChildren();
		return leaf.get(0).getValue(VarMap, FunctMap, turtle);
	}
	
}
