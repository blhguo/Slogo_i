package turtle;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import views.Observer;
import views.SceneElements.Observable;
import views.SlogoView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
*
* Turtle holds knowledge about themselves that can be
* used for display.
* 
*/
public class Turtle implements Observable {
	private Point2D location;
	private double speed;
	private double heading;
	private ImageView turtleview;
	private Line line;
	private boolean isShowing;
	private boolean myPenUp;
	private Canvas myCanvas;
	public static final double TURTLESIZE = 50;
	private List<Observer> observers;
    private double BASEX = SlogoView.TURTLEVIEWX + 1.0 / 2 * SlogoView.TURTLEVIEWWIDTH
            - .5 * TURTLESIZE;
    private double BASEY = SlogoView.TURTLEVIEWY + 1.0 / 2 * SlogoView.TURTLEVIEWHEIGHT
            - .5 * TURTLESIZE;
	/**
	 * Variety of getters and setters used to access Information in actors, for
	 * update and display
	 **/
	public Turtle() {
		this(new Point2D(SlogoView.TURTLEVIEWX + 1.0 / 2 * SlogoView.TURTLEVIEWWIDTH
                - 25,SlogoView.TURTLEVIEWY + 1.0 / 2 * SlogoView.TURTLEVIEWHEIGHT
                - 25), 0);
	}


	public Turtle(Point2D location, double speed)
	{
	    observers = new ArrayList<>();
		this.location = location;
		this.speed = speed;
		Image turtle = new Image("turtle.png", TURTLESIZE,TURTLESIZE,true,true);
		turtleview = new ImageView(turtle);
		turtleview.setLayoutX(this.location.getX());
		turtleview.setLayoutY(this.location.getY());
		line = new Line();
		//System.out.println(this.getClass().getTypeName());
	}

	public double getX() {
		return myX;
	}
	
	public double getY() {
		return myY;
	}

	public void setX(double xPosition) {
		myX = xPosition;
	}
	
	public void setY(double yPosition) {
		myY = yPosition;
	}
	
	/**
	 * Point representing the current location of the turtle
	 * 
	 * @return Point location
	 */
	public Point2D getLocation() {
		return myLocation;
	}
	public ImageView getImage(){return turtleview;}

	public void setLocation(Point2D p)
	{
	    line.setFill(Color.BLACK);
	    line.setStrokeWidth(2);
	    line.setStartX(location.getX());
	    line.setStartY(location.getY());
	    line.setEndX(p.getX());
	    line.setEndY(p.getY());
		location = p;
		turtleview.setLayoutX(this.location.getX());
		turtleview.setLayoutY(this.location.getY());
		updateObservers();
	}
    public Line getLine(){
	    return line;
    }
	/**
	 * An int representing the speed of the turtle as it updates on the screen
	 * 
	 */
	public double getSpeed() {
		return mySpeed;
	}

	public void setSpeed(double s) {
		mySpeed = s;
	}

	public double getHeading() {
		return heading;
	}

	public void setHeading(double heading) {
		this.heading = Math.floorMod((int) heading, 360);
	}
	
	public void rotate(double angle) {
		setHeading(heading + angle);
	}
	
	public void penUp() {
		myPenUp = true;
	}
	
	public void penDown() {
		myPenUp = false;
	}
	
	public Image show() {
		isShowing = true;
		return turtleview.getImage();
	}
	
	public void hide() {
		isShowing = false;
		turtleview.setOpacity(0);
	}
	
	public boolean isShowing() {
		return isShowing;
	}

	public void update() {
		
	}
	
	public void reset() {
		myCanvas.getGraphicsContext2D().clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
	}
	

    @Override
    public void updateObservers() {
        for (Observer o : observers){
            o.update(new Object());
        }
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }
}
