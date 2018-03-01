package views;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import turtle.Turtle;
import views.SceneElements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SlogoView implements Observer, Observable{
	
	/*
	 * Make all constants public and static
	 * no need to prevent state manipulation
	 */

	/*
	 * Constants relating to the characteristics of the main window as a whole
	 */
	public static final int WINDOWHEIGHT = 600;
	public static final int WINDOWWIDTH = 800;
	public static final Color BACKGROUND = Color.ANTIQUEWHITE;
	/*
	 * Constants relating to the tool bar and its dimensions
	 */
	public static final double TOOLBARHEIGHT = 1.0/10 * WINDOWHEIGHT;
	public static final double TOOLBARWIDTH = WINDOWWIDTH;
	/*
	 * Constants relating to the Command History section of the main window
	 */
	public static final double CMDHISTORYX = 0;
	public static final double CMDHISTORYY = 1.0 / 10 * WINDOWHEIGHT;
	public static final double CMDHISTORYWIDTH = 2.0 / 7 * WINDOWWIDTH;
	public static final double CMDHISTORYHEIGHT = 4.5 / 10 * WINDOWHEIGHT;
	
	/*
	 * Constants relating to the Variable View section of the main window
	 */
	public static final double VARIABLEVIEWX = 0;
	public static final double VARIABLEVIEWY = 1.0 / 10 * WINDOWHEIGHT + CMDHISTORYHEIGHT;
	public static final double VARIABLEVIEWWIDTH = 2.0 / 7 * WINDOWWIDTH;
	public static final double VARIABLEVIEWHEIGHT = 4.5 / 10 * WINDOWHEIGHT;
	/*
	 * Constants relating to the Console section of the main window
	 */
	public static final double TURTLEVIEWX = CMDHISTORYWIDTH;
	public static final double TURTLEVIEWY = TOOLBARHEIGHT;
	public static final double TURTLEVIEWWIDTH = WINDOWWIDTH - CMDHISTORYWIDTH;
	public static final double PERCENTHEIGHT = .8;
	public static final double TURTLEVIEWHEIGHT = PERCENTHEIGHT * (WINDOWHEIGHT - TOOLBARHEIGHT);
	/*
	 * Constants relating to the Command Prompt section of the main window
	 */
	public static final double CONSOLEX = CMDHISTORYWIDTH;
	public static final double CONSOLEY = TOOLBARHEIGHT + TURTLEVIEWHEIGHT;
	public static final double CONSOLEWIDTH = TURTLEVIEWWIDTH;
	public static final double CONSOLEHEIGHT = (1 - PERCENTHEIGHT) * (WINDOWHEIGHT - TOOLBARHEIGHT);
	
	/*
	 * Local variables governing JavaFX objects in the main window
	 */
	private Group myRoot;
	private Stage myStage;
	private Scene myScene;


    /*
     * Local SceneElement variables
     */
	private Console myConsole;
	private History myHistory;
	private Toolbar myToolbar;
	private TurtleDisplay myTurtleDisplay;
	private VariableView myVariableView;

	/*
	 * Data structures for SceneElements, variables,
	 * functions
	 */
    private List<Turtle> turtles;
	private List<SceneElement> sceneElements;
	private List<Observer> observers;

	public SlogoView(){
		//constructor
	}
	/**
     * Start the program.
     */
	public Scene initializeStartScene() {
		initializeDataStructures();
		initializeSceneElements();
		initializeObservers();
		observers = new ArrayList<>();
		Scene myScene = initializeWindow(WINDOWHEIGHT, WINDOWWIDTH, BACKGROUND);
		myScene.setOnKeyPressed(e -> quit(e.getCode()));
		return myScene;
	}

	private void quit(KeyCode code) {
		if (code == KeyCode.Q){
			System.exit(0);
		}
	}

	public String[] getPassValue() {
		return myConsole.getPassValue();
	}
	private void initializeDataStructures() {
        turtles = new ArrayList<>();
        turtles.add(new Turtle());
	}

	private void initializeObservers() {
    	for (SceneElement element: sceneElements){
    	    element.addObserver(this);
        }
	}

	private void initializeSceneElements() {
        sceneElements = new ArrayList<>();
        myConsole = new Console();
        sceneElements.add(myConsole);
        myHistory = myConsole.getHistory();
		sceneElements.add(myHistory);
		myVariableView = new VariableView();
		sceneElements.add(myVariableView);
		myTurtleDisplay = new TurtleDisplay(turtles.get(0));
		sceneElements.add(myTurtleDisplay);
		myToolbar = new Toolbar();
		myToolbar.addObserver(myTurtleDisplay);
		sceneElements.add(myToolbar);
	}

	private Scene initializeWindow(int height, int width, Color background) {
		Group root = new Group();
		myRoot = root;
		root.getChildren().addAll(getElements());
		return new Scene(root, width, height, background);
	}
	private Group getElements(){
		Group retgroup = new Group();
		for (SceneElement element : sceneElements){
		    retgroup.getChildren().add(element.getField());
        }
		return retgroup;
	}
	public void update(Object o){
        myRoot.getChildren().removeAll(myRoot.getChildren());
        for (SceneElement element : sceneElements){
            myRoot.getChildren().add(element.getField());
        }
        myRoot.getChildren().addAll(turtles.get(0).getLines());
//        if (o.getClass().getTypeName().equals("java.lang.String")){
//            getHostServices().showDocument((String)o);
//        }

        updateObservers();
	}


    @Override
    public void updateObservers() {
        for (Observer o : observers){
            o.update(turtles.get(0));
        }
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void updateVarView(Map<String, Double> variables) {
        myVariableView.updateVarView(variables);
	}
}
