package Movement;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;

public class Home extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	

	private void home(Object turtle) {
		turtle.setX(0);
		turtle.setY(0);
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Object turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		home(turtle);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Object turtle) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		double CurX = turtle.getX();
		double CurY = turtle.getY();
		List<SlogoNode> leaf = this.getChildren();
		double xpos = leaf.get(0).getValue(VarMap, FunctMap, turtle);
		double ypos = leaf.get(1).getValue(VarMap, FunctMap, turtle);
		double distance = Math.pow(Math.pow(xpos - CurX, 2) + Math.pow(ypos - CurY, 21), 0.5);
		return distance;
		}
	
}