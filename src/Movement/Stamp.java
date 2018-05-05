package Movement;

import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Stamp extends SlogoNode{

	private void stamp(Map<Integer, Turtle> turtleMap) {
		for (int n : turtleMap.keySet()) {
			if (turtleMap.get(n).isActive()) {
				turtleMap.get(n).stamp();
			}
		}
	}
	public Stamp() {
		numchildren = 0;
	}
	
	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap,
			Map<Integer, Turtle> turtleMap) {
		double step = getValue(VarMap, FunctMap, turtleMap);
		System.out.println("hi");
		stamp(turtleMap);
		return step;
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		return 0;
	}

}
