package TreeBuilding;

import MathOps.Sum;
import Movement.Forward;
import treenode.MasterNode;
import treenode.NumberNode;
import treenode.SlogoNode;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilder {

    private int buildcounter;
    private List<SlogoNode> heads;
    private SlogoNode master;
    public TreeBuilder(){
        buildcounter = 0;
    }
    public SlogoNode buildTree(SlogoNode[] array){
        //System.out.println(array.length);
        master = new MasterNode();
        //heads = new ArrayList<>();
        SlogoNode currentNode = array[0];
        while(buildcounter < array.length){
            currentNode = array[buildcounter];
            master.addChild(build(currentNode, array));
            buildcounter++;
        }
        buildcounter = 0;
        return master;
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
//        SlogoNode[] array = new SlogoNode[2];
//        array[0] = new Forward();
//        array[1] = new NumberNode(50);
//        SlogoNode headNode = buildTree(array);
//        System.out.println(headNode.getNumchildren());
//        System.out.println(headNode.getChildren().get(0).getNumchildren());
//    }
    
    
    public static void main(String[] args){
        SlogoNode[] array = new SlogoNode[3];
        array[0] = new Sum();
        array[1] = new NumberNode(50);
        array[2] = new NumberNode(50);
        TreeBuilder builder = new TreeBuilder();
        SlogoNode headNode = builder.buildTree(array);
//        System.out.println(headNode.getNumchildren());
//        System.out.println(headNode.getChildren().get(0).getNumchildren());
//        System.out.println(headNode.getChildren().get(1).getNumchildren());
    }
}
