package VarOp;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;
import views.Main;
/*
 * Only function to add the name of the function and the head of the function node to the function map.
 */
public class MakeUserInstruction extends SlogoNode {

		//commandName , variable list, command list
	public MakeUserInstruction() {
		numchildren = 3; //first child is the variables, second child is the commands.	
		//three children: (1) name of function command, (2) node of variable parameters, (3) commands
	}
	
	
	/* one child is a list node of all the variables that need to be specif
	 */
	// another child is a list node of all the commands with variables 
	
	
	@Override
	public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
		// TODO Auto-generated method stub
		FunctMap.put(this.getChildren().get(0).getVariableName(), this); //adds name of function and head node to the function map
		return this.getValue(VarMap, FunctMap, turtle); //returns the name of the value node
	}

	@Override
	public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Turtle turtle) {
		// TODO Auto-generated method stub
		return 0; //should not return any value because the function has yet to be called
	}
	
	

}
