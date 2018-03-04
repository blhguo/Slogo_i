package VarOp;

import treenode.SlogoNode;
import turtle.Turtle;

import java.util.Map;

public class For extends SlogoNode{

    public For(){
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
