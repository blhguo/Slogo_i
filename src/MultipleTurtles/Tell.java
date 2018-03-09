package MultipleTurtles;

import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Tell extends SlogoNode {

	public Tell() {
		this.numchildren = 1;
	}
	
	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		//add turtles to the turtle arraylist
		SlogoNode leaf = this.getChildren().get(0);
		Double turtleNum = leaf.getValue(VarMap, FunctMap, turtleMap);
//		for (int i = 0; i <turtleNum; i++) {
//			turtleMap.get
//		}
		return 0;
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		return 0;
	}

}
