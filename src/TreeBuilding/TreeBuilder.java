package TreeBuilding;

import treenode.SlogoNode;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilder {


    public TreeBuilder(){

    }
    public SlogoNode buildTree(SlogoNode[] array){
        SlogoNode currentNode = array[0];
        SlogoNode head = currentNode;
        int headindex = 0;
        build(currentNode, array, headindex);


        return head;
    }

    private SlogoNode build(SlogoNode head, SlogoNode[] array, int headindex) {
        if (head.getNumchildren() == 0){
            return head;
        }
        else {
            for (int i = headindex + 1; i <= headindex + head.getNumchildren(); i++){
                head.addChild(build(array[i], array, i));
            }
        }
    }
}
