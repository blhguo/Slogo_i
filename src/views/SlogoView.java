package views;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import turtle.Turtle;
import views.SceneElements.Console;
import views.SceneElements.CurrentState;
import views.SceneElements.History;
import views.SceneElements.Observable;
import views.SceneElements.Palettes;
import views.SceneElements.SceneElement;
import views.SceneElements.Toolbar;
import views.SceneElements.TurtleDisplay;
import views.SceneElements.VariableView;

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
	public static final int WINDOWHEIGHT = 650;
	public static final int WINDOWWIDTH = 1000;
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
	public static final double CMDHISTORYWIDTH = 1.7 / 7 * WINDOWWIDTH;
	public static final double CMDHISTORYHEIGHT = 4.5 / 10 * WINDOWHEIGHT;

	/*
	 * Constants relating to the Variable View section of the main window
	 */
	public static final double VARIABLEVIEWX = 0;
	public static final double VARIABLEVIEWY = 1.0 / 10 * WINDOWHEIGHT + CMDHISTORYHEIGHT;
	public static final double VARIABLEVIEWWIDTH = 1.7 / 7 * WINDOWWIDTH;
	public static final double VARIABLEVIEWHEIGHT = 4.5 / 10 * WINDOWHEIGHT;
	/*
	 * Constants relating to the Console section of the main window
	 */
	public static final double TURTLEVIEWX = CMDHISTORYWIDTH;
	public static final double TURTLEVIEWY = TOOLBARHEIGHT;
	public static final double TURTLEVIEWWIDTH = WINDOWWIDTH - 2* CMDHISTORYWIDTH;
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
	 * Constants relating to the State Prompt section of the main window
	 */
	public static final double STATEX = CMDHISTORYWIDTH + TURTLEVIEWWIDTH;
	public static final double STATEY = 1.0 / 10 * WINDOWHEIGHT;
	public static final double STATEWIDTH = 1.7 / 7 * WINDOWWIDTH;
	public static final double STATEHEIGHT = 4.5 / 10 * WINDOWHEIGHT;
	/*
	 * Constants relating to the Palette Prompt section of the main window
	 */
	public static final double PALETTEX = CMDHISTORYWIDTH + TURTLEVIEWWIDTH;
	public static final double PALETTEY = 1.0 / 10 * WINDOWHEIGHT + STATEHEIGHT;
	public static final double PALETTEWIDTH = 1.7 / 7 * WINDOWWIDTH;
	public static final double PALETTEHEIGHT = 4.5 / 10 * WINDOWHEIGHT;




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
	private Toolbar myToolbar;
	private VariableView myVariableView;
	private Palettes myPalette;
	private CurrentState myCurrentState;


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
		//addEventHandler for hovering over turtle
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
    		//for(int i = 0; i<turtles.size();i++) {
        myToolbar.addObserver(turtles.get(0));
    		//}
	}
    public void setConsole(Double d){
	    myConsole.setLittleField(d.toString());
    }
	private void initializeSceneElements() {
        sceneElements = new ArrayList<>();
        myConsole = new Console(turtles.get(0));
        sceneElements.add(myConsole);
        History myHistory = myConsole.getHistory();
		sceneElements.add(myHistory);
		myVariableView = new VariableView();
		sceneElements.add(myVariableView);
		//loop that adds all the turtles to the view
		myToolbar = new Toolbar();
		//for (int i = 0; i<turtles.size();i++) {
			TurtleDisplay myTurtleDisplay = new TurtleDisplay(turtles.get(0));
			sceneElements.add(myTurtleDisplay);
			myToolbar.addObserver(myTurtleDisplay);
		//}
		sceneElements.add(myToolbar);
		myCurrentState = new CurrentState(turtles.get(0));
		sceneElements.add(myCurrentState);
		myPalette = new Palettes();
		sceneElements.add(myPalette);		
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
        //for (int i = 0; i<turtles.size();i++) {
        myRoot.getChildren().addAll(turtles.get(0).getLines());
//		CurrentState currState = new CurrentState(turtles.get(0));
//		currState.setTextArea(currState.getTurtleInfo());
//      
		updateObservers();
		
	}
	public void updateScreen(){
		myRoot.getChildren().removeAll(myRoot.getChildren());
		for (SceneElement element : sceneElements){
			myRoot.getChildren().add(element.getField());
		}
		//for (int i= 0; i<turtles.size();i++) {
			myRoot.getChildren().addAll(turtles.get(0).getLines());
		//}
	}


    @Override
    public void updateObservers() {
        for (Observer o : observers){
//        		for (int i = 0; i< turtles.size();i++) {
            o.update(turtles.get(0));
//        		}
        }
    }

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void updateVarView(Map<String, Double> variables) {
		myVariableView.updateVarView(variables);
	}

//	public void updateState() {
//		myCurrentState.updateState();
//	}
	public void updatePalette(Map<String, String> palettes) {
		myPalette.updatePalette(palettes);
	}
//	public void update() {
//		myCurrentState.reset();
////		myCurrentState.updateState();		
//	}
}
