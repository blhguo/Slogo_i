package views;

import javafx.application.Application;
import javafx.stage.Stage;
import treenode.CommandFactory;
import treenode.SlogoNode;
import turtle.Turtle;

import java.util.HashMap;
import java.util.Map;

import TreeBuilding.TreeBuilder;
import TreeReader.ReadTree;

public class Main extends Application implements Observer{
	
	private static final String TITLE = "SLogo";
	private static Stage mainStage;
	private SlogoView simulation;

    private Map<String, Double> variables;
    private Map<String, SlogoNode> functions;

	public static void main(String[] args) {
		launch(args);
		}
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		simulation = new SlogoView();
		//mainStage=simulation.initializeStartScene(primaryStage);
		primaryStage.setResizable(false);
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(simulation.initializeStartScene());
		primaryStage.show();
		simulation.addObserver(this);
        variables = new HashMap<>();
        functions = new HashMap<>();
		variables.put("x", 5.0);
		variables.put("y", 10.0);
        variables.put("a", 5.0);
        variables.put("s", 10.0);
        variables.put("d", 5.0);
        variables.put("f", 10.0);
        variables.put("g", 5.0);
        variables.put("h", 10.0);
        variables.put("j", 5.0);
        variables.put("k", 10.0);
        variables.put("l", 5.0);
        variables.put("y", 10.0);
        variables.put("r", 5.0);
        variables.put("e", 10.0);
        variables.put("w", 5.0);
        variables.put("q", 10.0);
		updateVarView();
	}


	@Override
	public void update(Object o) {
	    //TODO Implement this backend stuff
		//backend.pass(simulation.getPassValue(), (Turtle)o);
		TreeBuilder Builder = new TreeBuilder();
		CommandFactory factory = new CommandFactory() {};
		ReadTree reader = new ReadTree();
		System.out.println(simulation.getPassValue());
		SlogoNode[] BufferArray = factory.convertStringtoNode(simulation.getPassValue());
		SlogoNode Head = Builder.buildTree(BufferArray);
		double buff = reader.evaluate(Head, variables, functions, (Turtle) o);
		updateVarView();
		
	}
	public void updateVarView(){
	    simulation.updateVarView(variables);
    }
}
