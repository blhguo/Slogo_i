package Movement;

import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class ClearStamp extends SlogoNode{

	private void clearStamp(Map<Integer, Turtle> turtleMap) {
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive()) {
				turtleMap.get(n).clearStamps();
			}
		}
	}
	public ClearStamp() {
		numchildren = 0;
	}
	
	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap,
			Map<Integer, Turtle> turtleMap) {
		double step = getValue(VarMap, FunctMap, turtleMap);
		System.out.println("hi");
		clearStamp(turtleMap);
		return step;
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		return 0;
	}

}