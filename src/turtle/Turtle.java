package turtle;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
*
* Turtle holds knowledge about themselves that can be
* used for display.
* 
*/
public class Turtle {
	private double myX;
	private double myY;
	private Point2D myLocation;
	private double mySpeed;
	private double heading;
	private boolean isShowing;
	private boolean myPenUp;
	private Canvas myCanvas;
	
	private ImageView image;
	/**
	 * Variety of getters and setters used to access Information in actors, for
	 * update and display
	 **/
	public Turtle() {
		this(0, 0, new Point2D(0, 0), 0);
		myCanvas = new Canvas();

	}


	public Turtle(double xPosition, double yPosition, Point2D location, double speed) {
		this.myX = xPosition;
		this.myY = yPosition;
		this.myLocation = location;
		this.mySpeed = speed;

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

	public void setLocation(Point2D p) {
		myLocation = p;
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
		return image.getImage();
	}
	
	public void hide() {
		isShowing = false;
		myCanvas.getGraphicsContext2D().clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
	}
	
	public boolean isShowing() {
		return isShowing;
	}

	public void update() {
		
	}
	
	public void reset() {
		myCanvas.getGraphicsContext2D().clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
	}
	

}
