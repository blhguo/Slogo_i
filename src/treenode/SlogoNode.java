package treenode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * builds the abstract node class that all commands will follow
 */
public abstract class SlogoNode implements CommandInterface{
	
	private List<SlogoNode> children = new ArrayList<SlogoNode>();
	
	/*
	 * adds a SlogoNode to the children nodelist
	 */
	public void addChild(SlogoNode n) {
		this.children.add(n);
	}
	public abstract double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> funct, Object turtle);
	/*
	 * returns children
	 */
	public List<SlogoNode> getChildren(){
		return children;
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
