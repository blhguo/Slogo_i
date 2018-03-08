package Movement;

import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import treenode.SlogoNode;
import turtle.Turtle;

public class SetPalette extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	
	public SetPalette() {
		numchildren = 4;
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
			double index = leaf.get(0).getExecute(VarMap, FunctMap, turtle);
			double r = leaf.get(1).getExecute(VarMap, FunctMap, turtle);
			double g = leaf.get(2).getExecute(VarMap, FunctMap, turtle);
			double b = leaf.get(3).getExecute(VarMap, FunctMap, turtle);

			turtle.addColor(index, Color.rgb((int) r, (int) g, (int) b, 1.0));
			return index;
		}
	
}