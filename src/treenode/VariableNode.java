package treenode;

import java.util.Map;
//Needs to be completed
public class VariableNode extends SlogoNode{
	
		private String variableName;
		
	public VariableNode(String n){
		
		this.variableName = n;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap, Object turtle) {
		// TODO Auto-generated method stub
		return getValue(VarMap);
	}

	@Override
	public double getValue(Map<String, Double> VarMap) {
		// TODO Auto-generated method stub
		return VarMap.get(this.variableName); //obtains value from map
	}

}
