package treenode;

import java.util.Map;

//interface for setting the structure of a Command Node
public interface CommandInterface {
	/*
	 * getExecute() performs the function of the Node, encapsulates function within each Node
	 * returns a double file for prior nodes to access its value.
	 */
	public double getExecute(Map<String, Double> VarMap, Object turtle);
	public double getValue();
//	/*
//	 * addChild Node to the commandNode
//	 */
//	public void addChild();
	
	
}
