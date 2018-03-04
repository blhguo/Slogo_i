package VarOp;

import java.util.Map;

import treenode.SlogoNode;
import turtle.Turtle;

public class Repeat extends SlogoNode{

    public Repeat(){
        this.setNumChildren(1);
    }

    @Override
    public double getValue(Map<String, Double> VarMap, Map<String, SlogoNode> funct, Turtle turtle) {
        return 0;
    }

    @Override
    public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
        return this.getChildren().get(0).getExecute(VarMap, FunctMap, turtle);
    }
}
