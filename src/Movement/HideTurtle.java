package Movement;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class HideTurtle extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	
	private void visible(Turtle turtle) {
		turtle.hide();
	}
	
	public HideTurtle() {
		numchildren = 0;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		visible(turtle);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		return 0;
	}
	
}