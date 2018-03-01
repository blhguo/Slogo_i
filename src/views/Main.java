package views;

import javafx.application.Application;
import javafx.stage.Stage;
import treenode.CommandFactory;
import treenode.NodeBuilder;
import treenode.SlogoNode;
import turtle.Turtle;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import TreeBuilding.TreeBuilder;
import TreeReader.TreeReader;

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
		updateVarView();
	}
	
	


	@Override
	public void update(Object o) {
	    //TODO Implement this backend stuff
		//backend.pass(simulation.getPassValue(), (Turtle)o);
		//System.out.println(simulation.getPassValue());
		TreeBuilder Builder = new TreeBuilder();
		CommandFactory factory = new CommandFactory() {};
		TreeReader reader = new TreeReader();
		//System.out.println(simulation.getPassValue());
		SlogoNode[] BufferArray = factory.convertStringtoNode(simulation.getPassValue());
		//System.out.println(BufferArray[0]);
		SlogoNode Head = Builder.buildTree(BufferArray);
        System.out.println(reader.evaluate(Head, variables, functions, (Turtle) o));
        simulation.updateScreen();
		updateVarView();

	}
    public static void openWebPage(String url) {
	    try {
            Desktop.getDesktop().browse(new java.net.URI(url));
        }
        catch (URISyntaxException e){
	        System.out.println("DONE");
        }
        catch (IOException e){
	        System.out.println("DONE");
        }
    }
	public void updateVarView(){
	    simulation.updateVarView(variables);
    }
}
