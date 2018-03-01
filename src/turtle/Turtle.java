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

/**
*
* Turtle holds knowledge about themselves that can be
* used for display.
* 
*/
public class Turtle implements Observable, Observer{
	private Point2D currentpos;
	private double speed;
	private double heading;
	private ImageView turtleview;
	private Line line;
	private ArrayList<Line> lines;
	private Color lineColor = Color.BLACK;
	private boolean isShowing;
	public static double initHeading = 0;
	public static double CMDBUFF = 60;
    private double oldHeading;


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


	public Turtle(Point2D currentpos, double speed)
	{
	    observers = new ArrayList<>();
		this.currentpos = currentpos;
		this.speed = speed;
		Image turtle = new Image("turtle.png", TURTLESIZE,TURTLESIZE,true,true);
		turtleview = new ImageView(turtle);
		turtleview.setLayoutX(this.currentpos.getX());
		turtleview.setLayoutY(this.currentpos.getY());
		line = new Line();
		lines = new ArrayList<>();
		myPenUp = false;
		isShowing = true;
	}

	/**
	 * Point representing the current currentpos of the actor
	 * 
	 * @return Point currentpos
	 */
	public Point2D getLocation()
	{
		return currentpos;
	}
	public ImageView getImage(){return turtleview;}

	public void setAbsoluteLocation(Point2D newpos){
	    setLocation(new Point2D(getOriginalLocation().getX() + newpos.getX(), getOriginalLocation().getY() +
                newpos.getY()));
    }
    public double getOldHeading() {
        return oldHeading;
    }
	public void setLocation(Point2D newpos)
	{
		if ((newpos.getX() < SlogoView.TURTLEVIEWX) || (newpos.getX() > (SlogoView.TURTLEVIEWX + SlogoView.TURTLEVIEWWIDTH - TURTLESIZE * 0.5) || (newpos.getY() < SlogoView.TURTLEVIEWY) || (newpos.getY() > (SlogoView.TURTLEVIEWY - CMDBUFF + SlogoView.TURTLEVIEWHEIGHT + TURTLESIZE * 0.5))))
			return;
        addLine(newpos);
        currentpos = newpos;
		turtleview.setLayoutX(this.currentpos.getX());
		turtleview.setLayoutY(this.currentpos.getY());
		//updateObservers();
	}
    private void setRotate(double degrees){
	    turtleview.setRotate(-1 * degrees);
    }
    private void addLine(Point2D newpos) {
        if (!myPenUp) {
            Line l = new Line();
            l.setStroke(lineColor);
            l.setStrokeWidth(2);
            l.setStartX(currentpos.getX() + .5 * TURTLESIZE);
            l.setStartY(currentpos.getY() + .5 * TURTLESIZE);
            l.setEndX(newpos.getX() + .5 * TURTLESIZE);
            l.setEndY(newpos.getY() + .5 * TURTLESIZE);
            lines.add(l);
            System.out.println(lineColor);
        }
    }

    public List<Line> getLines(){
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
		if (!myPenUp) {
			return 1.0;
		}else {
			return 0.0;
		}
	}
	public void setHeading(double heading)
	{
	    oldHeading = this.heading;
	    this.heading = heading % 360;
	    setRotate(heading);
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
		turtleview.setOpacity(1);
		return turtleview.getImage();
	}
	
	public void hide() {
		isShowing = false;
		turtleview.setOpacity(0);
	}
	
	public double isShowing() {
		if (isShowing) {
			return 1.0;
		}else {
			return 0.0;
		}
	}
	
	public void update() {
		
	}
	
	public void clear(){
	    lines.clear();
	    setHeading(initHeading);
	    //updateObservers();
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

	@Override
	public void update(Object o) {
//		for (Line line : lines){
//			line.setStroke((Color)o);
//		}
		lineColor = (Color)o;
	}
}
