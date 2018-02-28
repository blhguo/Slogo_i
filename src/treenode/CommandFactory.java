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
	public static List<SlogoNode> convertStringtoNode(List<String> commandList) throws IllegalAccessException, InstantiationException {
		List<SlogoNode> nodeList = new ArrayList<SlogoNode>();
		for (int i = 0; i<commandList.size();i++) { 
			
			String current = commandList.get(i);
			if (isNumberNode(current)) { //if the value is as number
				NumberNode numberNode = new NumberNode(Integer.parseInt(current));
				nodeList.set(i,numberNode);
				continue;
			}
			SlogoNode currentNode = getInstance(NodeBuilder.createNode(current));
			nodeList.set(i, currentNode);
		}
		return nodeList;
	}

/*
 * command factory builds the variable and Number nodes separately from the classMap
 */

	public static <T> SlogoNode getInstance(Class<T> theClass)
		    throws IllegalAccessException, InstantiationException {

		    return (SlogoNode) theClass.newInstance();
		}
	
	/*
	 * method to check if a number is an integer (for creating number nodes)	   
	 */
	public static boolean isNumberNode(String s) {
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