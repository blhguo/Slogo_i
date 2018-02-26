package views;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SlogoView extends Application{
	
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
	public static final double CONSOLEX = CMDHISTORYWIDTH;
	public static final double CONSOLEY = TOOLBARHEIGHT;
	public static final double CONSOLEWIDTH = WINDOWWIDTH - CMDHISTORYWIDTH;
	public static final double PERCENTHEIGHT = .8;
	public static final double CONSOLEHEIGHT = PERCENTHEIGHT * (WINDOWHEIGHT - TOOLBARHEIGHT);
	/*
	 * Constants relating to the Command Prompt section of the main window
	 */
	public static final double CMDPROMPTX = CMDHISTORYWIDTH;
	public static final double CMDPROMPTY = TOOLBARHEIGHT + CONSOLEHEIGHT;
	public static final double CMDPROMPTWIDTH = CONSOLEWIDTH;
	public static final double CMDPROMPTHEIGHT = (1 - PERCENTHEIGHT) * (WINDOWHEIGHT - TOOLBARHEIGHT);
	
	/*
	 * Local variables governing JavaFX objects in the main window
	 */
	private Group myRoot;
	private Stage myStage;
	private Scene myScene;
	private History myHistory;
	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args); 
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		myStage = primaryStage;
		initializeVariables();
		myScene = initializeWindow(WINDOWHEIGHT, WINDOWWIDTH, BACKGROUND);
		myStage.setScene(myScene);
		myStage.show();
	}

	private void initializeVariables() {
		myHistory = new History();
	}

	private Scene initializeWindow(int height, int width, Color background) {
		Group root = new Group();
		myRoot = root;
		root.getChildren().addAll(getElements());
		Scene returnscene = new Scene(root, width, height, background);
		return returnscene;
	}
	private Group getElements(){
		Group retgroup = new Group();
		retgroup.getChildren().add(getConsole());
		retgroup.getChildren().add(getTurtleDisplay());
		retgroup.getChildren().add(getCommandHistory());
		retgroup.getChildren().add(getToolBar());
		retgroup.getChildren().add(getVariableView());
		 
		return retgroup;
	}

	private Node getVariableView() {
		Rectangle toAdd = new Rectangle(VARIABLEVIEWX, VARIABLEVIEWY, VARIABLEVIEWWIDTH, VARIABLEVIEWHEIGHT);
		toAdd.setFill(Color.CYAN);
		return toAdd;
	}

	private Node getToolBar() {
		//The Tool Bar is on the top, so no need to set X and Y values
		Rectangle toAdd = new Rectangle(TOOLBARWIDTH, TOOLBARHEIGHT,Color.WHITE);
		ToolBar toolbar = new ToolBar(
			     new Button("New"),
			     new Button("Open"),
			     new Button("Save"),
			     new Separator(),
			     new Button("Clean"),
			     new Button("Compile"),
			     new Button("Run"),
			     new Separator(),
			     new Button("Debug"),
			     new Button("Profile"),
			     new Separator(),
			     new Label("Hi Everyone!")
		);
		toolbar.setMinSize(TOOLBARWIDTH, TOOLBARHEIGHT);
		return toolbar;
	}

	private Node getCommandHistory() {
		Rectangle toAdd = new Rectangle(CMDHISTORYX, CMDHISTORYY, CMDHISTORYWIDTH, CMDHISTORYHEIGHT);
		toAdd.setFill(Color.BLUE);
		return toAdd;
	}

	private Node getTurtleDisplay() {
		Rectangle toAdd = new Rectangle(CMDPROMPTX, CMDPROMPTY, CMDPROMPTWIDTH, CMDPROMPTHEIGHT);
		toAdd.setFill(Color.ORANGE);
		return toAdd;
	}

	private Node getConsole() {
		Rectangle toAdd = new Rectangle(CONSOLEX, CONSOLEY, CONSOLEWIDTH, CONSOLEHEIGHT);
		toAdd.setFill(Color.RED);
		return toAdd;
	}
	

}
