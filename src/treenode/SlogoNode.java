package treenode;

import java.util.ArrayList;
import java.util.List;

/*
 * builds the abstract node class that all commands will follow
 */
public abstract class SlogoNode implements CommandInterface{
	
	public static Turtle turtle = new Turtle(); //creating a turtle object that can be used in all subclasses
	
	private List<SlogoNode> children = new ArrayList<SlogoNode>();
	private 
	
	public SlogoNode() {
		
	}
	
	/*
	 * adds a SlogoNode to the children nodelist
	 */
	public void addChild(SlogoNode n) {
		this.children.add(n);
	}
	
	/*
	 * returns children
	 */
	public List<SlogoNode> getChildren(){
		return children;
	}

//	/*
//	 * return value of the node
//	 */
//	public T getData() {
//		return this.data;
//	}
//	
//	/*
//	 * set the value of the node
//	 */
//	public void setData(T data) {
//		this.data = data;
//	}
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
