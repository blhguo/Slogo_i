package views;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import views.SceneElements.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SlogoView extends Application implements Observer{
	
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
	
	public static final double TURTLESIZE = 60;

	
	/*
	 * Local variables governing JavaFX objects in the main window
	 */
	private Group myRoot;
	private Stage myStage;
	private Scene myScene;

	private Console myConsole;
	private History myHistory;
	private Toolbar myToolbar;
	private TurtleDisplay myTurtleDisplay;
	private VariableView myVariableView;

	
	private Image myTurtleImage;
	private String myTurtleString = "images/turtle.png";
	
	private List<ImageView> myTurtleImageViews = new ArrayList<ImageView>();
	private List<Rectangle> myTurtles = new ArrayList<Rectangle>();
	private List<Line> myLines = new ArrayList<Line>();

	private double initX = 0;
	private double initY = 0;
	private double myDefaultOrientation = 0;
	private Pane myTurtlePane;

	// Line features
	private double myLineWidth = 2 ;
	private Color myLineColor = Color.BLACK;
	
	
	private List<SceneElement> sceneElements;
	/**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args); 
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		myStage = primaryStage;
		myStage.setResizable(false);
		initializeSceneElements();
		initializeObservers();
		myScene = initializeWindow(WINDOWHEIGHT, WINDOWWIDTH, BACKGROUND);
		myStage.setScene(myScene);
		myStage.show();
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
		myTurtleDisplay = new TurtleDisplay();
		sceneElements.add(myTurtleDisplay);
		myToolbar = new Toolbar();
		sceneElements.add(myToolbar);
		
		myTurtlePane = new Pane();
		setTurtleImage(myTurtleString);	
		drawTurtle(initX, initY, myDefaultOrientation);

	}

	private Scene initializeWindow(int height, int width, Color background) {
		Group root = new Group();
		myRoot = root;
		root.getChildren().addAll(getElements());
		root.getChildren().add(myTurtlePane);
		return new Scene(root, width, height, background);
	}
	private Group getElements(){
		Group retgroup = new Group();
		for (SceneElement element : sceneElements){
		    retgroup.getChildren().add(element.getField());
        }
		return retgroup;
	}
	
	public void update(){
        myRoot.getChildren().removeAll(myRoot.getChildren());
        for (SceneElement element : sceneElements){
            myRoot.getChildren().add(element.getField());
        }
	}
	
	public void setTurtleImage(String turtleString){
		try {
			Image image = new Image(new FileInputStream(turtleString));
			myTurtleImage = image;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void setTurtle(Rectangle t, double x, double y, double orientation){
		double leftX =  x + TURTLEVIEWX + TURTLEVIEWWIDTH/2 - TURTLESIZE/2;
		double topY = -y + TURTLEVIEWY + TURTLEVIEWHEIGHT/2 - TURTLESIZE/2;
		setImageView(myTurtleImageViews.get(myTurtles.indexOf(t)), leftX, topY, orientation);
	}
	
	private void setImageView(ImageView iv, double leftX, double topY, double orientation){
		iv.setX(leftX);
		iv.setY(topY);
		iv.setRotate(orientation);
	}
	
	private Rectangle drawTurtle(double x, double y, double orientation){
		Rectangle r = new Rectangle(x, y, TURTLEVIEWWIDTH, TURTLEVIEWHEIGHT);
		r.setFill(Color.TRANSPARENT);
		myTurtles.add(r);
		myTurtlePane.getChildren().add(r);
		drawCursorImage(myTurtleImage);
		setTurtle(r, x, y, orientation);
		return r;		
	}
	
	private ImageView drawCursorImage(Image image) {
		ImageView imgView = new ImageView(image);
		imgView.setFitWidth(TURTLESIZE);
		imgView.setFitHeight(TURTLESIZE);
		myTurtlePane.getChildren().add(imgView);
		myTurtleImageViews.add(imgView);
		return imgView;		
	}
	
	private void drawLine (double x1, double x2, double y1, double y2) {
		Line newLine = new Line(0, 0 , 10, 10);
//		Line newLine = new Line(x1, x2 , y1, y2);
		newLine.setStrokeWidth(myLineWidth);
		newLine.setStroke(myLineColor);
		myLines.add(newLine);
		myTurtlePane.getChildren().add(newLine);
	}
	
	
}
