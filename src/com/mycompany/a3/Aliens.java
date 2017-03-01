package com.mycompany.a3;

import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/* This is a child class of opponent. This class
 * specifically involves the aliens the player
 * must avoid. It keeps track of speed and direction.
 */
public class Aliens extends Opponent {
	private int speed, cons, dir; // speed is directly correlated to health
	private Random rand;
	private double angleX, angleY;
	private String name = "Alien";
	private GameWorld gw;
	private Vector<ICollider> collided;
	private boolean collision;
	public Aliens() {
		setColor(3);
		speed = 5;
		rand = new Random();
		cons = 1;
		setX(rand.nextInt(768));
		setY(rand.nextInt(1280));
		collided = new Vector<ICollider>();
		collision = true;
		dir = rand.nextInt(360); // Picks a random number between 0-359
		angleX = Math.cos(Math.toRadians(dir)) * speed * cons; // Converts
		angleY = Math.sin(Math.toRadians(dir)) * speed * cons;	
	}
	public Aliens(int x, int y) {
		setColor(3);
		speed = 5;
		rand = new Random();
		cons = 1;
		setX(x);
		setY(y);
		collided = new Vector<ICollider>();
	}
	public boolean collidesWith(ICollider otherObject) {
		//boolean result = false;
		int thisCenterX = (int)(this.getX() + (((GameObject)otherObject).getSize()/2)); // find centers
		int thisCenterY = (int)(this.getY() + (((GameObject)otherObject).getSize()/2));
		int otherCenterX = (int)(((GameObject)otherObject).getX() + (((GameObject)otherObject).getSize()/2));
		int otherCenterY = (int)(((GameObject)otherObject).getY() + (((GameObject)otherObject).getSize()/2));
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		// find square of sum of radii
		int thisRadius = (((GameObject)otherObject).getSize()/2);
		int otherRadius = (((GameObject)otherObject).getSize()/2);
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
		+ otherRadius*otherRadius);
		//needs to make sure that the object isn't present BEFORE checking its distance.
		if ((otherObject instanceof Aliens) && (((Aliens)otherObject).getVector().contains(this) || this.collided.contains(otherObject))){
			return false;
		}
		if (distBetweenCentersSqr <= radiiSqr && !collided.contains(otherObject)) { //checks if it exists in object
			return true; 
		} else if (distBetweenCentersSqr <= radiiSqr && collided.contains(otherObject)) {
			//if a hit is occurring but it has already been detected
			return false;
		} else if (collided.contains(otherObject)) { //remove it otherwise
			collided.remove(otherObject);
			return false;
		}	
		return false;
	}
	public Vector getVector() {
		return collided;
	}
	public void addToVector(ICollider o) {
		collided.add(o);
	}
	public void changeColl(boolean flag) {
		collision = flag;
	}
	public void handleCollision(ICollider otherObject, GameWorld gw) {
		if (otherObject instanceof Aliens){ //&& !collided.contains(otherObject)) {
			gw.collisionAl(otherObject, this); //calls a gameworld to handle the rest of the collision
		}
	}
	public void move() {
		if(rand.nextInt(50) > 45) { //keeps it on a trajectory for a certain "random" amount of time
			dir = rand.nextInt(360); // Picks a random number between 0-359
			angleX = Math.cos(Math.toRadians(dir)) * speed * cons; // Converts
			angleY = Math.sin(Math.toRadians(dir)) * speed * cons;			
		}
		//below prevents object from leaving boundries
		if((getX() + angleX) > 768) {
			setX(768);
			dir = rand.nextInt(360); // Picks a random number between 0-359
			angleX = Math.cos(Math.toRadians(dir)) * speed * cons; // Converts
			angleY = Math.sin(Math.toRadians(dir)) * speed * cons;		
		} else if((getX() + angleX) < 0) {
			setX(0);
			dir = rand.nextInt(360); // Picks a random number between 0-359
			angleX = Math.cos(Math.toRadians(dir)) * speed * cons; // Converts
			angleY = Math.sin(Math.toRadians(dir)) * speed * cons;		
		}
		setX(getX() + angleX); // Updates the X/y positions.
		if((getY() + angleY) > 1024) {
			setY(1024);
			dir = rand.nextInt(360); // Picks a random number between 0-359
			angleX = Math.cos(Math.toRadians(dir)) * speed * cons; // Converts
			angleY = Math.sin(Math.toRadians(dir)) * speed * cons;	
			setY(getY() + angleY);
		} else if((getY() + angleY) <0) {
			setY(0);
			dir = rand.nextInt(360); // Picks a random number between 0-359
			angleX = Math.cos(Math.toRadians(dir)) * speed * cons; // Converts
			angleY = Math.sin(Math.toRadians(dir)) * speed * cons;	
			setY(getY() + angleY);
		}
		setY(getY() + angleY);

	}

	public int getSpeed() {
		return speed;
	}

	public int getDirection() {
		return dir;
	}

	public String toString() {
		String x = "" + getX();
		int index = x.indexOf(".");
		String y = "" + getY();
		int in = y.indexOf(".");
		return name + ": loc =" + x.substring(0, index + 2) + "," + y.substring(0, in + 2) + " color=" + getColor()
				+ " size=" + getSize() + " direction=" + dir;
	}

	public void draw(Graphics g, Point p) {
		g.setColor(ColorUtil.GREEN);
		g.drawArc((int) (p.getX() + getX()), (int) (p.getY() + getY()), getSize(), getSize(), 0, 360);
	}
}
