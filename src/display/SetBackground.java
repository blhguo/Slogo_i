package display;

import java.util.List;
import java.util.Map;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import treenode.SlogoNode;
import turtle.Turtle;

public class SetBackground extends SlogoNode {

	public SetBackground() {
		numchildren = 1;
	}
	
	private double SetBackground(String color, double index) {
		Color bgColor = new Color(index, index, index, index);
		
		List<Color> colors;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Turtle turtle) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
