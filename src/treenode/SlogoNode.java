package treenode;

import turtle.Turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import turtle.Turtle;

/*
 * builds the abstract node class that all commands will follow
 */
public abstract class SlogoNode implements CommandInterface{
	protected int numchildren = 0;
	private List<SlogoNode> children = new ArrayList<>();
	
	/*
	 * adds a SlogoNode to the children nodelist
	 */
	public void addChild(SlogoNode n) {
		this.children.add(n);
	}
	public abstract double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> funct, Turtle turtle);
	
	public int getNumchildren(){
		return numchildren;
	}
	/*
	 * returns children
	 */
	public List<SlogoNode> getChildren(){
		return children;
	}
	
	public String getName() {
		return "does not exist";
	}
	
	/*
	 * check if the node is a leaf of the tree
	 */
	public boolean isLeaf() {
		if(this.children.size()==0){
			return true;}
		else {
			return false;
		}
	}
}
