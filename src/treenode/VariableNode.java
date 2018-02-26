package treenode;

import java.util.Map;
//Needs to be completed
public class VariableNode extends SlogoNode{
	
		private String variableName;
		private double value;
		
	//node constructed is created with parameters for a string and a double value.	
	public VariableNode(String n, Double value){ 
		this.variableName = n;
		this.value = value;
	}

	@Override
	public double getExecute(Map<String, Double> VarMap, Object turtle) {
		// TODO Auto-generated method stub
		return getValue(VarMap);
	}

	@Override
	public double getValue(Map<String, Double> VarMap) {
		// TODO Auto-generated method stub
		if (VarMap.containsKey(this.variableName)) {  //if key already exists
			VarMap.put(this.variableName, this.value);
		}else {
			VarMap.put(this.variableName, 0.0); //returns a default value of 0.0
		}
		return VarMap.get(this.variableName); //obtains value from map
	}

}
