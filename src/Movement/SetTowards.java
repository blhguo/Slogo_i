package Movement;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;

public class SetTowards extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}

	private void setHead(Object turtle, double angle) {
		turtle.setheading(angle);
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Object turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		setHead(turtle, step);
		return step;  //returns the final value of the node
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Object turtle) {
		// TODO Auto-generated method stub
		double initial = turtle.getHeading(); //TODO: Update according to Jamie's stuff
		double CurX = turtle.getX();
		double CurY = turtle.getY();
		List<SlogoNode> leaf = this.getChildren();
		double xpos = leaf.get(0).getValue(VarMap, FunctMap, turtle);
		double ypos = leaf.get(1).getValue(VarMap, FunctMap, turtle);
		
		double newhead = Math.tan((xpos - CurX)/(ypos - CurY));
		return newhead - initial;
	}
	
}