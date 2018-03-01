package views;

import javafx.application.Application;
import javafx.stage.Stage;
import treenode.CommandFactory;
import treenode.SlogoNode;
import turtle.Turtle;

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
		simulation.addObserver(this);
		//mainStage=simulation.initializeStartScene(primaryStage);
		primaryStage.setResizable(false);
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(simulation.initializeStartScene());
		primaryStage.show();
	}


	@Override
	public void update(Object o) {
	    //TODO Implement this backend stuff
		//backend.pass(simulation.getPassValue(), (Turtle)o);
		TreeBuilder Builder = new TreeBuilder();
		CommandFactory factory = new CommandFactory() {};
		ReadTree reader = new ReadTree();
		SlogoNode[] BufferArray = factory.convertStringtoNode(simulation.getPassValue());
		SlogoNode Head = Builder.buildTree(BufferArray);
		double buff = reader.evaluate(Head, variables, functions, (Turtle) o);
		
		
	}
}
