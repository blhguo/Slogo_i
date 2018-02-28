package treenode;

import java.util.Map;
//Needs to be completed
public class VariableNode extends SlogoNode{
	
		private String variableName;
		
	//node constructed is created with parameters for a string and a double value.	
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
		if (VarMap.containsKey(this.variableName)) {  //if key already exists
			VarMap.put(this.variableName, this.getChildren().get(0).getValue(VarMap));
		}else {
			VarMap.put(this.variableName, this.getChildren().get(0).getValue(VarMap)); //returns a default value of 0.0
		}
		return VarMap.get(this.variableName); //obtains value from map
	}

}
