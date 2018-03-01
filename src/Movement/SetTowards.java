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
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		setHead(turtle, step);
		return step;  //returns the final value of the node
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		double initial = turtle.getHeading(); //TODO: Update according to Jamie's stuff
		double CurX = turtle.getLocation().getX();
		double CurY = turtle.getLocation().getY();
		List<SlogoNode> leaf = this.getChildren();
		double xpos = leaf.get(0).getExecute(VarMap, FunctMap, turtle);
		double ypos = leaf.get(1).getExecute(VarMap, FunctMap, turtle);
		//hi here are some magic numbers please dont judge will be fixed later
		xpos = xpos + 489.282857;
		ypos = ypos + 251;
		
		double newhead = -1 * Math.toDegrees(Math.atan((xpos - CurX)/(ypos - CurY)));
		return newhead;
	}
	
}