package com.mycompany.a3;

import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
/* This class is a child of Rescuer
 * and is the player character. This class
 * keeps track of speed and color.
 * Following the interface provided by Iguideable,
 * This class implements move methods and use
 * trig values to get a more accurate feeling of rotation.
 * 
 */
public class SpaceShip extends Rescuer{
	private int speed, cons = 1;
	private String name = "SpaceShip";
	private static SpaceShip ship;
	private Random rand;
	private Vector<ICollider> collided;
	private void init() {
		ship.setColor(1);
		ship.changeSize(100);
		ship.speed = 5;
		ship.rand = new Random();
		ship.setX(rand.nextInt(768));
		ship.setY(rand.nextInt(1280));
		collided = new Vector();
	}
	private SpaceShip() {
		
	}
	public static SpaceShip getShip() {
		if(ship == null) {
			ship = new SpaceShip();
			ship.init();
		}
		return ship;
	}
	public void moveRight(){	//moves the spaceship in the 0 direction
		double angleX = Math.cos(Math.toRadians(0)) * speed * cons;
		double angleY = Math.sin(Math.toRadians(0)) * speed * cons;
		ship.setX(ship.getX() + angleX);
		ship.setY(ship.getY() + angleY);
		if((getX() + angleX) > 768) {
			setX(768);
		} else if((getX() + angleX) < 0) {
			setX(0);
		}
		else {
			setX(getX() + angleX); // Updates the X/y positions.
		}
		if((getY() + angleY) > 1024) {
			setY(1024);
		} else if((getY() + angleY) <0) {
			setY(0);
		}
		else {
			setY(getY() + angleY);
		}
	}
	public void moveLeft() { //moves the spaceship in the 180 direction
		double angleX = Math.cos(Math.toRadians(180)) * speed * cons;
		double angleY = Math.sin(Math.toRadians(180)) * speed * cons;
		ship.setX(ship.getX() + angleX);
		ship.setY(ship.getY() + angleY);
		if((getX() + angleX) > 768) {
			setX(768);
		} else if((getX() + angleX) < 0) {
			setX(0);
		}
		else {
			setX(getX() + angleX); // Updates the X/y positions.
		}
		if((getY() + angleY) > 1024) {
			setY(1024);
		} else if((getY() + angleY) <0) {
			setY(0);
		}
		else {
			setY(getY() + angleY);
		}
	}
	public void moveUp() { //moves the spaceship in the 90 direction
		double angleX = Math.cos(Math.toRadians(90)) * speed * cons;
		double angleY = Math.sin(Math.toRadians(90)) * speed * cons;
		ship.setX(ship.getX() + angleX);
		ship.setY(ship.getY() + angleY);
		if((getX() + angleX) > 768) {
			setX(768);
		} else if((getX() + angleX) < 0) {
			setX(0);
		}
		else {
			setX(getX() + angleX); // Updates the X/y positions.
		}
		if((getY() + angleY) > 1024) {
			setY(1024);
		} else if((getY() + angleY) <0) {
			setY(0);
		}
		else {
			setY(getY() + angleY);
		}
	}
	public void moveDown() { //moves the spaceship in the 270 direction
		double angleX = Math.cos(Math.toRadians(270)) * speed * cons;
		double angleY = Math.sin(Math.toRadians(270)) * speed * cons;
		ship.setX(ship.getX() + angleX);
		ship.setY(ship.getY() + angleY);
		if((getX() + angleX) > 768) {
			setX(768);
		} else if((getX() + angleX) < 0) {
			setX(0);
		}
		else {
			setX(getX() + angleX); // Updates the X/y positions.
		}
		if((getY() + angleY) > 1024) {
			setY(1024);
		} else if((getY() + angleY) <0) {
			setY(0);
		}
		else {
			setY(getY() + angleY);
		}
	}
	//Jumps to the location of whatever selected object is provided.
	public void jumpToLocation(GameObject o) {
		ship.setX(o.getX());
		ship.setY(o.getY());
	}
	//Makes the "door" smaller, capped at 50.
	public void contract() {
		if(getSize() - 50 < 50) {
			ship.changeSize(50);
		} else {
			ship.changeSize(getSize()-50);
		}
	}
	//Makes the "door" larger. capped at the max size of the screen
	public void expand() {
		if( getSize() + 50 > 1024) {
			ship.changeSize(1024);
		} else {
			ship.changeSize(getSize()+50);
		}
	}
	public boolean collidesWith(ICollider otherObject) {
		boolean result = false;
		int thisCenterX = (int)(this.getX() + (((GameObject)otherObject).getSize()/2)); // find centers
		int thisCenterY = (int)(this.getY() + (((GameObject)otherObject).getSize()/2));
		int otherCenterX = (int)(((GameObject)otherObject).getX() + (((GameObject)otherObject).getSize()/2));
		int otherCenterY = (int)(((GameObject)otherObject).getY() + (((GameObject)otherObject).getSize()/2));
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		// find square of sum of radius
		int thisRadius = (((GameObject)otherObject).getSize()/2);
		int otherRadius = (((GameObject)otherObject).getSize()/2);
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
		+ otherRadius*otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) { 
			collided.add(otherObject);
			result = true ; 
		}
		collided.remove(otherObject);
		return result;
	}
	public void handleCollision(ICollider otherObject, GameWorld gw){
		gw.score((GameObject)otherObject);
	}
	public int getSpeed() {
		return speed;
	}
	public void draw(Graphics g, Point p) {
		g.setColor(ColorUtil.BLUE);
		g.drawRect((int)(p.getX() + getX()), (int)(p.getY() + getY()), getSize()/2, getSize()/2);
	}
	public String toString() {
		String x = ""+ship.getX();
		int index = x.indexOf(".");
		String y = ""+ship.getY();
		int in = y.indexOf(".");
		return ship.name + ": loc ="+ x.substring(0, index+2) + "," + y.substring(0, in+2) + " color=" + ship.getColor() + " size=" + ship.getSize();
	}
}
