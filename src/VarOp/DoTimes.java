package VarOp;

import treenode.SlogoNode;
import turtle.Turtle;

import java.util.List;
import java.util.Map;

public class DoTimes extends SlogoNode{

    public DoTimes(){}

    @Override
    public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
        List<SlogoNode> leaf = this.getChildren();
        double ret = 0;
        for (SlogoNode s : leaf){
            ret = s.getExecute(VarMap, FunctMap, turtle);
        }
        return ret;
    }

    @Override
    public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle){
        double step = getValue(VarMap, FunctMap, turtle);
        return step;  //returns the final value of the node
    }
}
