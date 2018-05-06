package Movement;

import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Stamp extends SlogoNode{

	
	private void stamp(Map<Integer, Turtle> turtleMap) {
		for (int i : turtleMap.keySet()) {
			if (turtleMap.get(i).isActive()) {
				turtleMap.get(i).stamp();
			}
		}
	}


	public Stamp() {
		numchildren = 0;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap,
			Map<Integer, Turtle> turtleMap) {
		double s = getValue(VarMap, FunctMap, turtleMap);
		stamp(turtleMap);
		return s;
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Map<Integer, Turtle> turtleMap) {
		return 0;
	}

}
