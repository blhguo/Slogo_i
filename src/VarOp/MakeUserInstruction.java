package VarOp;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import treenode.VariableNode;
import turtle.Turtle;
import views.Main;
/*
 * Only function to add the name of the function and the head of the function node to the function map.
 */
public class MakeUserInstruction extends SlogoNode {

		//commandName , variable list, command list
	public MakeUserInstruction() {
		numchildren = 3; //first child is the variables, second child is the commands.	
		//three children: (1) name of function command, (2) node of variable parameters, (3) masternode of commands
	}
	
	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		int numOfVariables = this.getChildren().get(1).getNumchildren(); //returns the number of variables
		//increment through each node of the command and determine if each one is a variable node
		int numOfVariableNodes = 0;
		for (int i = 0; i<this.getChildren().get(2).getNumchildren(); i++) {
			SlogoNode current = this.getChildren().get(2).getChildren().get(i);
			if (current instanceof VariableNode) {
				numOfVariableNodes++;				
			}
		}
		if (numOfVariables == numOfVariableNodes) {
			//number of variables matches the number of variables in the command line, therefore it gets added to function map
			FunctMap.put(this.getChildren().get(0).getVariableName(), this.getChildren().get(2)); //adds name of function and head node to the function map
			return 1;
			//this.getChildren().get(0).getVariableName() == name of the function
			//this.getChildren().get(2) == head node of the commands. 
		}else {
			return this.getValue(VarMap, FunctMap, turtle);
		}
		
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Turtle turtle) {
		// TODO Auto-generated method stub
		return 0; //should not return any value because the function has yet to be called
	}
	
	

}
