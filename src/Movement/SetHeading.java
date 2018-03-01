package Movement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class SetHeading extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	public SetHeading(){
		numchildren = 1;
	}
	private void setHead(Turtle turtle, double angle) {
		turtle.setHeading(-1 * angle); //TODO: Update according to Jamie's stuff
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
        double origHeading = turtle.getHeading();
		double step = getValue(VarMap, FunctMap, turtle);
		setHead(turtle, step);
		return Math.abs(Math.min(origHeading - step, step - origHeading));
	}


	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		List<SlogoNode> leaf = this.getChildren();
		double buffer = leaf.get(0).getExecute(VarMap, FunctMap, turtle);

		return buffer;

	}
	
}
