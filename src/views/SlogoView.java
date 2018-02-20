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
	 * Constants relating to the characteristics of the main window as a whole
	 */
	private final int WINDOWHEIGHT = 600;
	private final int WINDOWWIDTH = 800;
	private final Color BACKGROUND = Color.ANTIQUEWHITE;
	/*
	 * Constants relating to the tool bar and its dimensions
	 */
	private final double TOOLBARHEIGHT = 1.0/10 * WINDOWHEIGHT;
	private final double TOOLBARWIDTH = WINDOWWIDTH;
	/*
	 * Constants relating to the Command History section of the main window
	 */
	private final double CMDHISTORYX = 0;
	private final double CMDHISTORYY = 1.0 / 10 * WINDOWHEIGHT;
	private final double CMDHISTORYWIDTH = 2.0 / 7 * WINDOWWIDTH;
	private  final double CMDHISTORYHEIGHT = 4.5 / 10 * WINDOWHEIGHT;
	
	/*
	 * Constants relating to the Variable View section of the main window
	 */
	private final double VARIABLEVIEWX = 0;
	private final double VARIABLEVIEWY = 1.0 / 10 * WINDOWHEIGHT + CMDHISTORYHEIGHT;
	private final double VARIABLEVIEWWIDTH = 2.0 / 7 * WINDOWWIDTH;
	private  final double VARIABLEVIEWHEIGHT = 4.5 / 10 * WINDOWHEIGHT;
	/*
	 * Constants relating to the Console section of the main window
	 */
	private final double CONSOLEX = CMDHISTORYWIDTH;
	private final double CONSOLEY = TOOLBARHEIGHT;
	private final double CONSOLEWIDTH = WINDOWWIDTH - CMDHISTORYWIDTH;
	private final double PERCENTHEIGHT = .8;
	private  final double CONSOLEHEIGHT = PERCENTHEIGHT * (WINDOWHEIGHT - TOOLBARHEIGHT);
	/*
	 * Constants relating to the Command Prompt section of the main window
	 */
	private final double CMDPROMPTX = CMDHISTORYWIDTH;
	private final double CMDPROMPTY = TOOLBARHEIGHT + CONSOLEHEIGHT;
	private final double CMDPROMPTWIDTH = CONSOLEWIDTH;
	private  final double CMDPROMPTHEIGHT = (1 - PERCENTHEIGHT) * (WINDOWHEIGHT - TOOLBARHEIGHT);
	
	/*
	 * Local variables governing JavaFX objects in the main window
	 */
	private Group myRoot;
	private Stage myStage;
	private Scene myScene;
	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args); 
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		myStage = primaryStage;
		myScene = initializeWindow(WINDOWHEIGHT, WINDOWWIDTH, BACKGROUND);
		myStage.setScene(myScene);
		myStage.show();
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
