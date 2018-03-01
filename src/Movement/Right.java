package Movement;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Right extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	public Right(){
		numchildren = 1;
	}
	private void right(Turtle turtle, double angle) {
		turtle.setHeading(turtle.getHeading() - angle); //TODO: Update according to Jamie's stuff
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		right(turtle, step);
		return step;  //returns the final value of the node
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		List<SlogoNode> leaf = this.getChildren();
		return leaf.get(0).getExecute(VarMap, FunctMap, turtle);
	}
	
}
