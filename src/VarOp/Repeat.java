package VarOp;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Repeat extends SlogoNode{

    public Repeat(){
        this.setNumChildren(2);
    }
    
    @Override
    public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
    		int counter;
    		List<SlogoNode> leaf = this.getChildren();
        double ret = 0;
        counter = (int) leaf.get(0).getExecute(VarMap, FunctMap, turtle);
        for (int i = 0; i < counter; i ++) {
        	ret = leaf.get(1).getExecute(VarMap, FunctMap, turtle);
        }
        return ret;
    }

    @Override
    public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
        return this.getValue(VarMap, FunctMap, turtle);
    }
}
