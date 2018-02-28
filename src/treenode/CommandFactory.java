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
	public static List<SlogoNode> convertStringtoNode(List<String> commandList){
		List<SlogoNode> nodeList = new ArrayList<SlogoNode>();
		for (int i = 0; i<commandList.size();i++) { 
			
			String current = commandList.get(i);
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
			
			nodeList.set(i, currentNode);
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