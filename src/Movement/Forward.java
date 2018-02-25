package Movement;

import treenode.SlogoNode;

public class Forward extends SlogoNode{

	@Override
	public double getExecute(Turtle turtle) {
		// TODO Auto-generated method stub
		return forward(turtle);
	}
	
	private Turtle forward(int distance) {
		turtle.setPosition(turtle.getDistance()+distance));
	}

}
