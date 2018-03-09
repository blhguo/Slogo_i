package TreeBuilding;

import Query.BracketNode;
import VarOp.DoTimes;
import VarOp.For;
import VarOp.If;
import VarOp.IfElse;
import VarOp.MakeUserInstruction;
import VarOp.MakeVariable;
import VarOp.Repeat;
import VarOp.ToFunction;
import treenode.MasterNode;
import treenode.NumberNode;
import treenode.SlogoNode;
import treenode.StringNode;
import VarOp.For;
import turtle.Turtle;

import java.util.List;
import java.util.Map;

public class TreeBuilder {

    private int buildcounter;
    private List<SlogoNode> heads;
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
    	
        SlogoNode master = new MasterNode();
        //heads = new ArrayList<>();
        SlogoNode currentNode = array[0];
        if (currentNode.getClass().equals(new BracketNode().getClass())){
            buildcounter++;
            master = buildList(array);
        }
        else if (currentNode.getClass().equals(new DoTimes().getClass())){
            master = handleDotimes(currentNode, array);
        }
        else if (currentNode.getClass().equals(new Repeat().getClass())){
            master = handleRepeat(array);
        }
        else if (currentNode.getClass().equals(new For().getClass())){
            master = handleFor(array);
        }
        else if (currentNode.getClass().equals(new If().getClass())) {
        	master = handleIf(array);
        }
        else if (currentNode.getClass().equals(new IfElse().getClass())) {
        	master = handleElseIf(array);
        }
        else if (currentNode.getClass().equals(new MakeUserInstruction().getClass())) {
        	master = handleMakeUserInstruction(array);
        }
        else if (currentNode.getClass().equals(new ToFunction(null, null).getClass())) {
        ToFunction toNode = new ToFunction(null, null);
        toNode = (ToFunction) currentNode;
        String name = toNode.getToName();
        	master = handleTo(array, currentNode, name);
        }
        else {
            master.addChild(build(currentNode, array));
        }

        buildcounter = 0;
        return master;
    }

    private SlogoNode handleDotimes(SlogoNode currentNode, SlogoNode[] array) {
        SlogoNode retNode = new MasterNode();
        SlogoNode expression;
        SlogoNode list;
        buildcounter++;
        if (!array[buildcounter].getClass().equals(new BracketNode().getClass())){
            System.out.println("Sorry, you don't have the right number of brackets");
            return new NumberNode(0);
        }
        else {
            array[buildcounter] = new MakeVariable();
        }
        //buildcounter--; //For build, which automatically adds one to buildcounter;
        expression = build(array[buildcounter], array);
        double value = expression.getExecute(VarMap, FunctMap, turtle);
        //System.out.println(value);
        String name = expression.getChildren().get(0).getName();
        //double val = expression.getChildren().get(1).getValue(VarMap, FunctMap, turtle);
        buildcounter += 2;
        if (buildcounter >= array.length){
            System.out.println("Out of bounds2");
            expression = new NumberNode(0);
            return expression;
        }
        //System.out.println(buildcounter);
        list = buildList(array);

        for (double i = 0; i < value; i++){

            VarMap.put(name, i + 1);
            retNode.addChild(new NumberNode(list.getExecute(VarMap, FunctMap, turtle)));
        }
        //TODO Figure out how to modify variable values at execution
        //VarMap.put(name, value);
        return retNode;
    }
        private SlogoNode handleFor(SlogoNode[] array) {
            SlogoNode retNode = new MasterNode();
            SlogoNode var, start, end, increment;
            SlogoNode list;
            buildcounter++;
            if (!array[buildcounter].getClass().equals(new BracketNode().getClass())){
                System.out.println("Sorry, you don't have the right number of brackets");
                return new NumberNode(0);
            }
            else {
                buildcounter++;
                //array[buildcounter] = new MakeVariable();
            }
            //buildcounter--; //For build, which automatically adds one to buildcounter;
            var = build(array[buildcounter], array);
            buildcounter++;
            start = build(array[buildcounter], array);
            buildcounter++;
            end = build(array[buildcounter], array);
            buildcounter++;
            increment = build(array[buildcounter], array);
            

            double startval = start.getExecute(VarMap, FunctMap, turtle);
            System.out.println(startval);
            double endval = end.getExecute(VarMap, FunctMap, turtle);
            System.out.println(endval);
            double incval = increment.getExecute(VarMap, FunctMap, turtle);
            System.out.println(incval);
            String name = var.getName();
            buildcounter++;
            if (buildcounter >= array.length){
                System.out.println("Out of bounds2");
                retNode = new NumberNode(0);
                return retNode;
            }
            //System.out.println(buildcounter);
            System.out.println(buildcounter);
            buildcounter++;
            list = buildList(array);
            for (double i = startval; i < endval; i+= incval){
                VarMap.put(name, i + 1);
                retNode.addChild(new NumberNode(list.getExecute(VarMap, FunctMap, turtle)));
            }

            //TODO Figure out how to modify variable values at execution
            //VarMap.put(name, value);
            return retNode;
        }
        

        
        private SlogoNode handleRepeat(SlogoNode[] array){
            SlogoNode retNode = new Repeat();
            SlogoNode expression;
            SlogoNode list;
            if (array[buildcounter].getClass().equals(new BracketNode().getClass())){
                System.out.println("Sorry, you don't have the right number of brackets");
                return new NumberNode(0);
            }
            buildcounter++;
            if (buildcounter >= array.length){
                System.out.println("Out of bounds1");
                retNode.addChild(new NumberNode(0));
            }
            SlogoNode node = array[buildcounter];
            retNode.addChild(build(node,array));

            buildcounter++;
            if (buildcounter >= array.length){
                System.out.println("Out of bounds2");
                expression = new NumberNode(0);
                return expression;
            }

            retNode.addChild(buildList(array));
            return retNode;
        }


    private SlogoNode handleMakeUserInstruction(SlogoNode[] array) {
    		SlogoNode retNode = new MakeUserInstruction();
  //  		SlogoNode functionName;
    		System.out.println(array.length);
    		SlogoNode variableList;
    		SlogoNode commandList;
    		buildcounter++; //increments past the To word
    		
    		System.out.println(array[buildcounter]);
    		//checks if the node is a string
//         if (!array[buildcounter].getClass().equals(new StringNode("").getClass())){
//               System.out.println("Sorry, you didn't give a name to the function");
//               return new NumberNode(0);
//          }
         //builds the string node
         SlogoNode node = array[buildcounter];
         System.out.println(node);
         retNode.addChild(build(node, array));
         //increments to the next command
         buildcounter++; //check if the second node
         if (buildcounter >= array.length){
             System.out.println("Out of bounds1");
             variableList = new NumberNode(0);
             return variableList;
         }
         SlogoNode node2 = array[buildcounter];
         retNode.addChild(buildList(array));
         
         buildcounter++; //check the third node
//         if (buildcounter >= array.length){
//             System.out.println("Out of bounds2");
//             commandList = new NumberNode(0);
//             return commandList;
//         }
         retNode.addChild(buildList(array));
         return retNode;
         
    }
    
    private SlogoNode handleTo(SlogoNode[] array, SlogoNode currentNode, String name) {
    		System.out.println(currentNode);
    		System.out.println(name + "blalalal");
    		System.out.println(this.FunctMap.keySet());
    		System.out.println(this.FunctMap.get(name));  //giving a null pointer error
    		SlogoNode retNode = new ToFunction(this.FunctMap.get(name), name);
    		SlogoNode list;
    		if (array.length==1) { //assume that there are no variables
    			System.out.println("assumes no parameters");
    			return retNode;
    		}
    		buildcounter++;
            if (buildcounter >= array.length){
                System.out.println("Out of bounds2");
                list = new NumberNode(0);
                return list;
            }
            //System.out.println(buildcounter);
            retNode.addChild(buildList(array));          
            return retNode;
    }
    
    private SlogoNode handleIf(SlogoNode[] array){
        SlogoNode retNode = new If();
        SlogoNode expression;
        SlogoNode list;
        if (array[buildcounter].getClass().equals(new BracketNode().getClass())){
            System.out.println("Sorry, you don't have the right number of brackets");
            return new NumberNode(0);
        }
        buildcounter++;
        if (buildcounter >= array.length){
            System.out.println("Out of bounds1");
            retNode.addChild(new NumberNode(0));
        }
        SlogoNode node = array[buildcounter];
        retNode.addChild(build(node, array));
        buildcounter++;
        if (buildcounter >= array.length){
            System.out.println("Out of bounds2");
            expression = new NumberNode(0);
            return expression;
        }
        //System.out.println(buildcounter);
        retNode.addChild(buildList(array));
        
        return retNode;
    }
    
    
    private SlogoNode handleElseIf(SlogoNode[] array){
        SlogoNode retNode = new IfElse();
        SlogoNode expression;
        SlogoNode list;
        if (array[buildcounter].getClass().equals(new BracketNode().getClass())){
            System.out.println("Sorry, you don't have the right number of brackets");
            return new NumberNode(0);
        }
        buildcounter++;
        if (buildcounter >= array.length){
            System.out.println("Out of bounds1");
            retNode.addChild(new NumberNode(0));
        }
        SlogoNode node = array[buildcounter];
        retNode.addChild(build(node, array));
        buildcounter++;
        if (buildcounter >= array.length){
            System.out.println("Out of bounds2");
            expression = new NumberNode(0);
            return expression;
        }
        //System.out.println(buildcounter);
        retNode.addChild(buildList(array));
        
        buildcounter++;
        if (buildcounter >= array.length){
            System.out.println("Out of bounds2");
            expression = new NumberNode(0);
            return expression;
        }
        //System.out.println(buildcounter);
        retNode.addChild(buildList(array));
        
        return retNode;
    }
    
    private SlogoNode buildList(SlogoNode[] array) {
        SlogoNode retNode = new MasterNode();
        SlogoNode current;
        if (!array[buildcounter].getClass().equals(new BracketNode().getClass())){
            System.out.println("Sorry, you don't have the right number of brackets");
            System.out.println(buildcounter);
            return new NumberNode(0);
        }
        buildcounter++;
        while(buildcounter < array.length){
            current = array[buildcounter];
            if (current.getClass().equals(new BracketNode().getClass())){
                break;
            }
            else if (current.getClass().equals(new Repeat().getClass())){
                retNode.addChild(handleRepeat(array));
            }
            else if (current.getClass().equals(new DoTimes().getClass())){
                retNode.addChild(handleDotimes(current, array));
            }
            else if (current.getClass().equals(new For().getClass())){
                retNode.addChild(handleFor(array));
            }
            else {
                retNode.addChild(build(current, array));
            }
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
