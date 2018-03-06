package treenode;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Deprecated.VariableNode;
import VarOp.MakeVariable;

/*
 * takes in an Array of strings and uses NodeBuilder to build those strings
 */
public class CommandFactory {
	
	private Map<String, SlogoNode> functionMap;

	/*
	 * method that converts a array of strings into an array of unique Nodes
	 */
	public CommandFactory(Map<String, SlogoNode> functions) {
		this.functionMap = functions;
	}
	
	private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	//checks if the word is only made of strings.
	private Boolean isString(String input) {
		return input.matches("[a-zA-Z]+");
	}
	
	public SlogoNode[] convertStringtoNode(String[] commandList, Map<String, SlogoNode> functions){
		SlogoNode[] nodeList = new SlogoNode[commandList.length];
		NodeBuilder nodeBuilder = new NodeBuilder(functions);
		for (int i = 0; i<commandList.length;i++) { 
			String current = commandList[i];
			SlogoNode currentNode = null;
			//If case for variable node, number node, or normal command node
			if(isDouble(current) ) {
				currentNode = nodeBuilder.createNumberNode(current);
			}
			else if (isVariable(current)) {
				currentNode = nodeBuilder.createVariableNode(current);
			}
			else if (isString(current) && !nodeBuilder.checkFunctionMap(current)) {
				currentNode = nodeBuilder.createStringNode(current);
			}
			else {
			currentNode = nodeBuilder.createNode(current);
			}
			
			nodeList[i]=currentNode;
		}
		convertLoneVariables(nodeList);//check for lone variables
		return nodeList;
	}
	private void convertLoneVariables(SlogoNode[] nodeList) {
		for (int i = 1; i<nodeList.length; i++) {
			if ((nodeList[i-1] instanceof MakeVariable)&&(nodeList[i] instanceof VariableNode)) {
				nodeList[i].setNumChildren(0);
			}
		}
	}

	/*
	 * method to check if a number is an integer (for creating number nodes)	   
	 */
	public static boolean isNumber(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	/*
	 * regex check for variable node
	 */
	public static Boolean isVariable(String input) {
		return input.matches(":(.*)");
	}
}