package Movement;


import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import treenode.SlogoNode;
import turtle.Turtle;

public class SetPosition extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}

	private double[] location;
	
	private void setpos(Turtle turtle, double[] loc) {
		turtle.setX(loc[0]);
		turtle.setY(loc[1]);
        Point2D newpos = new Point2D(loc[0], loc[1]);
        turtle.setLocation(newpos);

	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		setpos(turtle, location);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		double CurX = turtle.getLocation().getX();
		double CurY = turtle.getLocation().getY();
		List<SlogoNode> leaf = this.getChildren();
		double xpos = leaf.get(0).getValue(VarMap, FunctMap, turtle);
		double ypos = leaf.get(1).getValue(VarMap, FunctMap, turtle);
		location[0] = xpos;
		location[1] = ypos;
		double distance = Math.pow(Math.pow(xpos - CurX, 2) + Math.pow(ypos - CurY, 21), 0.5);
		return distance;
	}
	
}