package TreeReader;

import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class TreeReader {
	public double evaluate(SlogoNode node, Map<String, Double> VarMap, Map<String, SlogoNode> funct, Turtle turtle) {
		return node.getExecute(VarMap, funct, turtle);
	}
	public TreeReader() {};
}