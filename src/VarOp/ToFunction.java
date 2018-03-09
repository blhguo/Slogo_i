package VarOp;

import java.util.Map;

//import Deprecated.VariableNode;
import treenode.SlogoNode;
import treenode.VariableNode;
import turtle.Turtle;

/*
 * after a function is called
 */
public class ToFunction extends SlogoNode {
	private SlogoNode head;

	
	public ToFunction(SlogoNode headOfCommands) {
		this.head = headOfCommands;
		numchildren = 1; //only one child with all of the variables
	}
	
	//changes the value of the variable
	public void changeValue(Map<String, Double> VarMap, String variableName, double val) {
			VarMap.put(variableName, val);
	}
	
	

	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		int numOfParameters = this.getChildren().get(0).getNumchildren();
		double[] parameterArray = new double[numOfParameters];
		for (int i = 0; i<numOfParameters; i++) {
			parameterArray[i]= this.getChildren().get(0).getChildren().get(i).getExecute(VarMap, FunctMap, turtle);
		}
		//parameterArray now has all the parameters for the commands
		//head is the head of the command master node.
		int numOfVariableNodes = 0;
		for (int i = 0; i<this.head.getNumchildren(); i++) {
			SlogoNode current = this.head.getChildren().get(i);
			if (current instanceof VariableNode) {
				numOfVariableNodes++;				
			}
		}
		//check if the parameterArray does not have the right number of arguments
		if (parameterArray.length!=numOfVariableNodes) {
			throw new IllegalArgumentException();
		}	
		//run through each node and convert each variable node to return a value. 
		int count = 0;
		for (int i = 0; i<this.head.getNumchildren(); i++) {
			SlogoNode current = this.head.getChildren().get(i);
			if (current instanceof VariableNode) {
				VariableNode curr = (VariableNode) current;
				changeValue(VarMap, curr.getVariableName(), parameterArray[count]);
				count++;
			}
		}
		return this.head.getExecute(VarMap, FunctMap, turtle);
		
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Turtle turtle) {
		// TODO Auto-generated method stub
		return this.head.getExecute(VarMap, funct, turtle); //returns the value of the head
	}

}
