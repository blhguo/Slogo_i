package treenode;

import javafx.geometry.Point2D;
import turtle.Turtle;

import java.util.List;
import java.util.Map;

public class MasterNode extends SlogoNode{

    //	private double value = 0;
//	private double distance = 0;
    public MasterNode() {
    }

    @Override
    public double getExecute(Map<String, Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
        // TODO Auto-generated method stub
        double step = getValue(VarMap, FunctMap, turtle);
        return step;  //returns the final value of the node
    }


    @Override
    public double getValue(Map<String,Double> VarMap, Map<String, SlogoNode> FunctMap, Turtle turtle) {
        // TODO Auto-generated method stub
        List<SlogoNode> leaf = this.getChildren();
        double ret = 0;
        for (SlogoNode s : leaf){
            ret = s.getExecute(VarMap, FunctMap, turtle);
        }
        return ret;
    }

}