package Bools;

import java.util.List;
import java.util.Map;
import treenode.SlogoNode;

public class NotEqual extends SlogoNode{
//	
//	private double value = 0;
//	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}

	@Override
	public double getExecute(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Object turtle) {
		// TODO Auto-generated method stub
		double step = getValue(VarMap, FunctMap, turtle);
		return step;  //returns the final value of the node
	}
	
	@Override
	public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Object turtle) {
		// TODO Auto-generated method stub
		//TODO: Update according to Jamie's stuff
		List<SlogoNode> leaf = this.getChildren();
		double x = leaf.get(0).getValue(VarMap, FunctMap, turtle);
		double y = leaf.get(1).getValue(VarMap, FunctMap, turtle);
		if (x == y) {
			return 0;
		}
		else
			return 1;
	}		
	
}