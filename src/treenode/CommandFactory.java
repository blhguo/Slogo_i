package treenode;

import java.util.ArrayList;
import java.util.List;

/*
 * takes in an Array of strings and uses NodeBuilder to build those strings
 */
public class CommandFactory {
	/*
	 * method that converts a array of strings into an array of unique Nodes
	 */
	public static List<Class<?>> convertStringtoNode(List<String> commandList) {
		List<Class<?>> nodeList = new ArrayList<Class<?>>();
		for (int i = 0; i<commandList.size();i++) { 
			String current = commandList.get(i);
			Class<?> currentNode = NodeBuilder.createNode(current);
			nodeList.set(i, currentNode);
		}
		return nodeList;
	}
	
}
