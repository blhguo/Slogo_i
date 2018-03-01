package TreeBuilding;

import MathOps.Sum;
import Movement.Forward;
import treenode.NumberNode;
import treenode.SlogoNode;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilder {

    private int buildcounter;
    public TreeBuilder(){
        buildcounter = 0;
    }
    public SlogoNode buildTree(SlogoNode[] array){
        System.out.println(array.length);
        SlogoNode currentNode = array[0];
        SlogoNode head = build(currentNode, array);
        buildcounter = 0;
        return head;
    }

    public SlogoNode build(SlogoNode head, SlogoNode[] array) {
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
