package TreeBuilding;

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

    private SlogoNode build(SlogoNode head, SlogoNode[] array) {
        if (head.getNumchildren() == 0){
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
        
    }
}
