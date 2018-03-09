package views;

import javafx.application.Application;
import javafx.stage.Stage;
import treenode.CommandFactory;
import treenode.NodeBuilder;
import treenode.SlogoNode;
import turtle.Turtle;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import TreeBuilding.TreeBuilder;
import TreeReader.TreeReader;

public class Main extends Application implements Observer{
	
	private static final String TITLE = "SLogo";
	private static Stage mainStage;
	private SlogoView simulation;

    private Map<String, Double> variables;
    private static Map<String, SlogoNode> functions;
    private Map<Integer, Turtle> TurtleMap;

    public Main() {
    	//constructor
    }
    
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
	
	public String[] sanitize(String[] array) {
		ArrayList<String> list = new ArrayList<String>();
			for (String s : array) {
			    if (!s.matches("#(.*)"))
			        list.add(s);
		}
			return list.toArray(new String[list.size()]);
	}


	@Override
	public void update(Object o) {
	    //TODO Implement this backend stuff
		//backend.pass(simulation.getPassValue(), (Turtle)o);
		//System.out.println(simulation.getPassValue());
		TreeBuilder Builder = new TreeBuilder(variables, functions, (Turtle) o);
		CommandFactory factory = new CommandFactory(functions) {};
		TreeReader reader = new TreeReader();
		//System.out.println(simulation.getPassValue());
		SlogoNode[] BufferArray = factory.convertStringtoNode(sanitize(simulation.getPassValue()), functions);
		//System.out.println(BufferArray[0]);
		SlogoNode Head = Builder.buildTree(BufferArray);
        simulation.setConsole(reader.evaluate(Head, variables, functions, (Turtle) o));
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
	/*
	public ArrayList<Turtle> getActive(Map<Integer, Turtle> iliketurtles) {
		ArrayList<Turtle> turtles = new ArrayList<Turtle>();
		for (Map.Entry<Integer, Turtle> entry : TurtleMap.entrySet()) {
			if (entry.getValue().isActive())
			turtles.add(entry.getValue());
		}
		return turtles;
	}
	*/
	
}
