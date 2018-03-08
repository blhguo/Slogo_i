package VarOp;

import treenode.SlogoNode;
import turtle.Turtle;

import java.util.Map;

public class DoTimes extends SlogoNode{

    public DoTimes(){
        numchildren = 1;
    }

    @Override
    public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Turtle turtle) {
        return 0;
    }

    @Override
    public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
        return 0;
    }
}
