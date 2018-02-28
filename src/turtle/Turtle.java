package turtle;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
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
	private ArrayList<Line> lines;
	private boolean isShowing;


    private boolean myPenUp;
	private Canvas myCanvas;
	public static final double TURTLESIZE = 50;
	private List<Observer> observers;
    public static final double BASEX = SlogoView.TURTLEVIEWX + 1.0 / 2 * SlogoView.TURTLEVIEWWIDTH
            - .5 * TURTLESIZE;
    public static final double BASEY = SlogoView.TURTLEVIEWY + 1.0 / 2 * SlogoView.TURTLEVIEWHEIGHT
            - .5 * TURTLESIZE;
    public static final Point2D originalLocation = new Point2D(BASEX, BASEY);

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
		lines = new ArrayList<>();
		myPenUp = false;
		//System.out.println(this.getClass().getTypeName());
	}

	/**
	 * Point representing the current location of the actor
	 * 
	 * @return Point location
	 */
	public Point2D getLocation()
	{
		return location;
	}
	public ImageView getImage(){return turtleview;}

	public void setLocation(Point2D p)
	{
        addLine(p);
        location = p;
		turtleview.setLayoutX(this.location.getX());
		turtleview.setLayoutY(this.location.getY());
		updateObservers();
	}

    private void addLine(Point2D p) {
        if (!myPenUp) {
            Line l = new Line();
            l.setFill(Color.BLACK);
            l.setStrokeWidth(2);
            l.setStartX(location.getX() + .5 * TURTLESIZE);
            l.setStartY(location.getY() + .5 * TURTLESIZE);
            l.setEndX(p.getX() + .5 * TURTLESIZE);
            l.setEndY(p.getY() + .5 * TURTLESIZE);
            lines.add(l);
        }
    }

    public List<Line> getLine(){
	    return lines;
    }
    public Point2D getOriginalLocation(){
	    return originalLocation;
    }
	/**
	 * An int representing the speed of the actor as it updates on the screen
	 * 
	 */
	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double s)
	{
		speed = s;
	}

	public double getHeading()
	{
		return heading;
	}

	public double isPenDown() {
		if (myPenUp==false) {
			return 1.0;
		}else {
			return 0.0;
		}
	}
	public void setHeading(double heading)
	{
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
	
	public double isShowing() {
		if (isShowing==true) {
			return 1.0;
		}else {
			return 0.0;
		}
	}
	
	public void update() {
		
	}
	
	public void clear(){
	    lines.clear();
	    updateObservers();
    }
	public void reset() {
		myCanvas.getGraphicsContext2D().clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
	}
	

    @Override
    public void updateObservers() {
        for (Observer o : observers){
            o.update(this);
        }
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }
}
