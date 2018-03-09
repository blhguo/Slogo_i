package Movement;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;
import views.SlogoView;

public class SetTowards extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	public SetTowards(){
		numchildren = 2;
	}
	private void setHead(Turtle turtle, double angle) {
		turtle.setHeading(angle);
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
		double newhead = 0;
		List<SlogoNode> leaf = this.getChildren();
		double xpos = leaf.get(0).getExecute(VarMap, FunctMap, turtleMap);
		double ypos = leaf.get(1).getExecute(VarMap, FunctMap, turtleMap);
		//hi here are some magic numbers please dont judge will be fixed later
		xpos = xpos + 489.282857;
		ypos = ypos + 251;
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive()) {
		double initial = turtleMap.get(n).getHeading(); //TODO: Update according to Jamie's stuff
		double CurX = turtleMap.get(n).getLocation().getX();
		double CurY = turtleMap.get(n).getLocation().getY();

		newhead = -1 * Math.toDegrees(Math.atan((xpos - CurX)/(ypos - CurY)));
		setHead(turtleMap.get(n), newhead);
		
	}}return newhead;}
	
}