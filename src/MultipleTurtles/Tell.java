package MultipleTurtles;

import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Tell extends SlogoNode {

	public Tell() {
		this.numchildren = 1;
	}
	
	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		//add turtles to the turtle arraylist
		SlogoNode leaf = this.getChildren().get(0);
		Double turtleNum = leaf.getValue(VarMap, FunctMap, turtle);
		for (int i = 0; i <turtleNum; i++) {
			turtle.get
		}
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Turtle turtle) {
		// TODO Auto-generated method stub
		return 0;
	}

}
