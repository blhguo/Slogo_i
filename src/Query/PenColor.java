package Query;

import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import treenode.SlogoNode;
import turtle.Turtle;

public class PenColor extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	
	public PenColor() {
		numchildren = 0;
	}

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
			List<SlogoNode> leaf = this.getChildren();
			double index = turtle.getPenColor();
			return index;
		}
	
}