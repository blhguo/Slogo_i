package Movement;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;

public class Backward extends SlogoNode{
	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}

	private void backward(Object turtle, double distance) {
		turtle.setX(turtle.getX() - distance * Math.sin(turtle.getheading())); //TODO: Update according to Jamie's stuff
		turtle.setY(turtle.getY() - distance * Math.cos(turtle.getheading())); //TODO: Update according to Jamie's stuff
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Object turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		backward(turtle, step);
		return step;  //returns the final value of the node
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Object turtle) {
		// TODO Auto-generated method stub
		List<SlogoNode> leaf = this.getChildren();
		return leaf.get(0).getValue(VarMap, FunctMap, turtle);
	}
	
}
