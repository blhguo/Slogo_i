package TreeReader;

import java.util.Map;

public class ReadTree {
	public double evaluate(SlogoNode node, Map<String, Double> VarMap, Object turtle) {
		return node.getExecute(VarMap, turtle);
	}
}
