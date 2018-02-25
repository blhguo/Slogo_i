package treenode;

import java.util.ArrayList;
import java.util.List;

/*
 * builds the abstract node class that all commands will follow
 */
public abstract class SlogoNode<T> implements CommandInterface{
	
	public static Turtle turtle = new Turtle(); //creating a turtle object that can be used in all subclasses
	
	private List<SlogoNode<T>> children = new ArrayList<SlogoNode<T>>();
	private T data = null;
	private SlogoNode<T> parent = null;
	
	/*
	 * Constructor that creates a node with a value assigned to it.
	 */
	public SlogoNode(T data) {
		this.data = data;
	}
	/*
	 * adds a SlogoNode to the children nodelist
	 */
	public void addChild(SlogoNode<T> n) {
		this.children.add(n);
	}
	
	/*
	 * returns children
	 */
	public List<SlogoNode<T>> getChildren(){
		return children;
	}
	
	/*
	 * return value of the node
	 */
	public T getData() {
		return this.data;
	}
	
	/*
	 * set the value of the node
	 */
	public void setData(T data) {
		this.data = data;
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
