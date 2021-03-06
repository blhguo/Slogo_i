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

	public YCoordinate() {
		numchildren = 0;
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
		//TODO: Update according to Jamie's stuff
		double ret = 0;
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive()) {
		ret = -1*(turtleMap.get(n).getLocation().getY() - (SlogoView.TURTLEVIEWY + .5 * SlogoView.TURTLEVIEWHEIGHT - .5 * Turtle.TURTLESIZE));
		}} return ret; }
	
}