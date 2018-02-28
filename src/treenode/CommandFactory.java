package treenode;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/*
 * takes in an Array of strings and uses NodeBuilder to build those strings
 */
public class CommandFactory {
	/*
	 * method that converts a array of strings into an array of unique Nodes
	 */
	public static SlogoNode[] convertStringtoNode(String[] commandList){
		SlogoNode[] nodeList = new SlogoNode[commandList.length];
		for (int i = 0; i<commandList.length;i++) { 
			
			String current = commandList[i];
			SlogoNode currentNode = null;
			//If case for variable node, number node, or normal command node
			if(isNumber(current)) {
				currentNode = NodeBuilder.createNumberNode(current);
			}
			else if (isVariable(current)) {
				currentNode = NodeBuilder.createVariableNode(current);
			}
			else {
			currentNode = NodeBuilder.createNode(current);
			}
			
			nodeList[i]=currentNode;
		}
		return nodeList;
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