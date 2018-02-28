package Movement;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Home extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	

	private void home(Turtle turtle) {
		turtle.setLocation(turtle.getOriginalLocation());
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		home(turtle);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		double CurX = turtle.getLocation().getX();
		double CurY = turtle.getLocation().getY();
		List<SlogoNode> leaf = this.getChildren();
		double xpos = 0;
		double ypos = 0;
		double distance = Math.pow(Math.pow(xpos - CurX, 2) + Math.pow(ypos - CurY, 2), 0.5);
		return distance;
		}
	
}