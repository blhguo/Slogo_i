package Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import treenode.NumberNode;
import treenode.SlogoNode;
import turtle.Turtle;

public class Tell extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}
	
	public Tell() {
	    //numchildren = 1;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtleMap);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Map<Integer, Turtle> turtleMap) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		List<SlogoNode> leaf = this.getChildren().get(0).getChildren();
		List<Double> leafnums = new ArrayList<>();
		for (SlogoNode s : leaf){
		    System.out.println(s);
		    leafnums.add(s.getValue(VarMap, FunctMap, turtleMap));
        }

        for (Double d : leafnums){
            System.out.println(d);
        }
		double ret = 0;
		for (int n : turtleMap.keySet()) {
			if (leafnums.contains((double) n)) {
				turtleMap.get(n).setActive(true);
				ret = n;
	        }
	        else {
			    turtleMap.get(n).setActive(false);
            }
		}
		for (double d : leafnums){
		    if (!turtleMap.containsKey((int) d)) {
                Turtle t = new Turtle();
                t.setActive(true);
                turtleMap.put((int) d, t);
            }

        }
		return ret;}
	
}