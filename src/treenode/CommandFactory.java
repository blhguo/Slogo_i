package treenode;

import java.util.ArrayList;
import java.util.List;

/*
 * takes in an Array of strings and uses NodeBuilder to build those strings
 */
public class CommandFactory {
	/*
	 * Create a method that parses through an array of strings 
	 */
	public static 

	/*
	 * method that converts a array of strings into an array of unique Nodes
	 */
	public static List<SlogoNode> convertStringtoNode(List<String> commandList) {
		List<SlogoNode> nodeList = new ArrayList<SlogoNode>();
		for (int i = 0; i<commandList.size();i++) { 
			String current = commandList.get(i);
			SlogoNode currentNode = NodeBuilder.buildNode(current);
			nodeList.set(i, currentNode);
		}
		return nodeList;
	}
	
}
