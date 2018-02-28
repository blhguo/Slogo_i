package TreeBuilding;

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
            buildcounter++;
            while(temp <= head.getNumchildren()){
                if (buildcounter >= array.length){
                    System.out.println("Out of bounds");
                    break;
                }
                head.addChild(build(array[buildcounter], array));
                buildcounter++;
                temp++;
            }
        }
        return head;
    }
    public static void main(String[] args){
        SlogoNode[] array = new SlogoNode[3];
        array[0] = new Forward();
        array[1] = new Forward();
        array[2] = new NumberNode(50);
        TreeBuilder builder = new TreeBuilder();
        SlogoNode headNode = builder.buildTree(array);
        System.out.println(headNode.getNumchildren());
        System.out.println(headNode.getChildren().get(0).getNumchildren());
        System.out.println(headNode.getChildren().get(0).getChildren().get(0).getNumchildren());
    }
}
