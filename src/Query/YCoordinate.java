package Query;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;
import views.SlogoView;

public class YCoordinate extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		return -1*(turtle.getLocation().getY() - (SlogoView.TURTLEVIEWY + .5 * SlogoView.TURTLEVIEWHEIGHT - .5 * Turtle.TURTLESIZE));
		}
	
}