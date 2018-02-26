package Movement;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;

public class Forward extends SlogoNode{
	
	private double value = 0;
	private double distance = 0;
//	public Forward() {
//		this.val = getValue();
//	}

	private void forward(Object turtle) {
		List<SlogoNode> leaf = this.getChildren(); //get leaf method from abstract class
		if (leaf.isNumberNode()) {
			distance = leaf.getValue();
		}
		turtle.setPosition(turtle.getDistance()+distance);
	}

	@Override
	public double getExecute(Map<String, Double> VarMap, Object turtle) {
		// TODO Auto-generated method stub
		forward(turtle);
		return getValue();  //returns the final value of the node
	}


	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return distance;
	}
	
}
