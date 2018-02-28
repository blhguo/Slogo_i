package TreeReader;

import java.util.Map;

import treenode.SlogoNode;

public class ReadTree {
	public double evaluate(SlogoNode node, Map<String, Double> VarMap, Map<String, SlogoNode> funct, Object turtle) {
		return node.getExecute(VarMap, funct, turtle);
	}
}
