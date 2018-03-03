package TreeBuilding;

import Query.BracketNode;
import VarOp.Repeat;
import treenode.MasterNode;
import treenode.NumberNode;
import treenode.SlogoNode;
import turtle.Turtle;

import java.util.List;
import java.util.Map;

public class TreeBuilder {

    private int buildcounter;
    private List<SlogoNode> heads;
    private SlogoNode master;
    private Map<String, Double> VarMap;
    private Map<String, SlogoNode> FunctMap;
    private Turtle turtle;
    public TreeBuilder(Map<String, Double> VarMap,  Map<String, SlogoNode> FunctMap, Turtle turtle){
        buildcounter = 0;
        this.VarMap = VarMap;
        this.FunctMap = FunctMap;
        this.turtle = turtle;
    }
    public SlogoNode buildTree(SlogoNode[] array){
        //System.out.println(array.length);

        master = new MasterNode();
        //heads = new ArrayList<>();
        SlogoNode currentNode = array[0];
        if (currentNode.getClass().equals(new BracketNode().getClass())){
            buildcounter++;
            master = buildList(array);
        }
        else if (currentNode.getClass().equals(new Repeat().getClass())){
            master = handleRepeat(currentNode, array);
        }
        else {
            master.addChild(build(currentNode, array));
        }

        buildcounter = 0;
        return master;
    }
    private SlogoNode handleRepeat(SlogoNode currentNode, SlogoNode[] array){
        SlogoNode retNode = new MasterNode();
        SlogoNode expression;
        SlogoNode list;
        buildcounter++;
        if (buildcounter >= array.length){
            System.out.println("Out of bounds1");
            retNode.addChild(new NumberNode(0));
        }
        SlogoNode node = array[buildcounter];
        expression = build(node, array);
        double value = expression.getExecute(VarMap, FunctMap, turtle);
        System.out.println(value);
        buildcounter++;
        if (buildcounter >= array.length){
            System.out.println("Out of bounds2");
            expression = new NumberNode(0);
            return expression;
        }
        System.out.println(buildcounter);
        list = buildList(array);
        for (SlogoNode s : list.getChildren()){
            System.out.println(s.getClass().getTypeName());
        }

        for (int i = 0; i < value; i++){
            retNode.addChild(list);
        }
        return retNode;
    }

    private SlogoNode buildList(SlogoNode[] array) {
        SlogoNode retNode = new MasterNode();
        SlogoNode current;
        if (!array[buildcounter].getClass().equals(new BracketNode().getClass())){
            System.out.println("Sorry, you don't have the right number of brackets");
            return new NumberNode(0);
        }
        buildcounter++;
        while(buildcounter < array.length){
            current = array[buildcounter];
            if (current.getClass().equals(new BracketNode().getClass())){
                break;
            }
            retNode.addChild(build(current, array));
            buildcounter++;
        }
        return retNode;
    }

    private SlogoNode build(SlogoNode head, SlogoNode[] array) {
        if (head.getNumchildren() == 0){
            //System.out.println("Buildcounter: " + buildcounter);
            //System.out.println("Stuff");
            return head;
        }
        else {
            int temp = 0;
            while(temp < head.getNumchildren()){
                buildcounter++;
                temp++;
                if (buildcounter >= array.length){
                    //System.out.println("Out of bounds");
                    break;
                }
                head.addChild(build(array[buildcounter], array));

            }
        }
        return head;
    }
//    public static void main(String[] args){
//        SlogoNode[] array = new SlogoNode[3];
//        array[0] = new Sum();
//        array[1] = new NumberNode(50);
//        array[2] = new NumberNode(50);
//        TreeBuilder builder = new TreeBuilder();
//        SlogoNode headNode = builder.buildTree(array);
//        System.out.println(headNode.getNumchildren());
//        System.out.println(headNode.getChildren().get(0).getNumchildren());
//        System.out.println(headNode.getChildren().get(1).getNumchildren());
//    }
}
