package VarOp;

import java.util.List;
import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class If extends SlogoNode{

    public If(){
        this.setNumChildren(2);
    }
    
    @Override
    public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
        List<SlogoNode> leaf = this.getChildren();
        double ret = 0;
        double boolin = leaf.get(0).getExecute(VarMap, FunctMap, turtle);
        if (boolin != 0){
        	ret = leaf.get(1).getExecute(VarMap, FunctMap, turtle);
        }
        return ret;
    }

    @Override
    public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
        return this.getValue(VarMap, FunctMap, turtle);
    }
}
