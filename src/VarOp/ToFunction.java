package VarOp;

import java.util.List;
import java.util.Map;

//import Deprecated.VariableNode;
import treenode.SlogoNode;
import turtle.Turtle;

/*
 * after a function is called
 */
public class ToFunction extends SlogoNode {
	private SlogoNode head;

	
	public ToFunction(SlogoNode head) {
		this.head = head;
		numchildren = 1; //only one child which is the parameters
	}
	
	private int countNodes(SlogoNode head) { //assumes head is now at the first variable
		SlogoNode current = head;
		if (current == null) {
			return 0;
		}
		int  count=1;
		while (current.getChildren().get(0)!=null) {
			current = current.getChildren().get(0);
			count++;
		}
		return count;
	}


	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {

		//check if the number of variables match
		SlogoNode userInputVariable = this.getChildren().get(0); //first parameter variable
		SlogoNode numVariables = this.head.getChildren().get(1); //first dummy variable
		if (countNodes(userInputVariable)!=countNodes(numVariables)) {
			throw new IllegalArgumentException(); 
		}
		
		//begin going through the values and substituting
		SlogoNode variable = userInputVariable; //variable list -> this.getChildren().get(0);
		SlogoNode current = this.head.getChildren().get(2); //command list
		while(current.getChildren().get(0)!=null) {
			if (current instanceof VariableNode) { //if the node is a variable node
				String variableName = current.getVariableName();
				//changes the value of the variable node, and adds to the map the value of the node.
				VarMap.put(variableName, variable.getValue(VarMap, FunctMap, turtle));
				variable = variable.getChildren().get(0); //move to next node on the variable
			}
		}
		return getValue(VarMap, FunctMap, turtle); //returns the value of the node.
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Turtle turtle) {
		// TODO Auto-generated method stub
		//go through each variable and substitute the correct value into the node
		return this.head.getChildren().get(2).getValue(VarMap, funct, turtle); //returns the 3rd child (commands head value)
	}

}
