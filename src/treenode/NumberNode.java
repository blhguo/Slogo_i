package treenode;

import java.util.Map;

public class NumberNode extends SlogoNode {
	
	private double val;

	public NumberNode(double n) {
		this.val = n;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap, Object turtle) {
		// TODO Auto-generated method stub
		return getValue(VarMap);
	}

	@Override
	public double getValue(Map<String, Double> VarMap) {
		// TODO Auto-generated method stub
		return this.val;
	}
	
	
	
	
}
