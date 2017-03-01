package com.mycompany.a3;

import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Shape;

/* This is a child class of opponent. This class
 * specifically involves the astronauts the player
 * must rescue. It keeps track of health and also 
 * allows the player to move. Health is directly
 * proportional to health for this class.
 */
public class Astronaut extends Opponent implements ISelectable {
	private int health, dir, cons, color; // health is directly correlated
								// to health
	private Random rand;
	private String name = "Astronaut";
	private Vector<ICollider> collided;
	private double angleX, angleY;
	//private Shape s;
	private boolean selected;

	public Astronaut() {
		color = 250;
		setColor(color);
		health = 5;
		rand = new Random();
		cons = 1;
		setX(rand.nextInt(768));
		setY(rand.nextInt(1280));
		collided = new Vector();
		dir = rand.nextInt(360); // Picks a random number between 0-359
		angleX = Math.cos(Math.toRadians(dir)) * health * cons; // Converts
		angleY = Math.sin(Math.toRadians(dir)) * health * cons;	
		//s = new Shape();
	}
	public void heal() { //resets object and heals it
		health = 5;
		setColor(200);
	}
	public boolean collidesWith(ICollider otherObject) {
		boolean result = false;
		int thisCenterX = (int) (this.getX() + (((GameObject) otherObject).getSize() / 2)); // find
																							// centers
		int thisCenterY = (int) (this.getY() + (((GameObject) otherObject).getSize() / 2));
		int otherCenterX = (int) (((GameObject) otherObject).getX() + (((GameObject) otherObject).getSize() / 2));
		int otherCenterY = (int) (((GameObject) otherObject).getY() + (((GameObject) otherObject).getSize() / 2));
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx * dx + dy * dy);
		// find square of sum of radii
		int thisRadius = (((GameObject) otherObject).getSize() / 2);
		int otherRadius = (((GameObject) otherObject).getSize() / 2);
		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) {
			collided.add(otherObject);
			result = true;
		}
		collided.remove(otherObject);
		return result;
	}

	public void handleCollision(ICollider otherObject, GameWorld gw) {
		if (otherObject instanceof Aliens) {
			gw.collisionAs(this);
		}
	}

	public void move() {
		if(rand.nextInt(50) == 49) {
			dir = rand.nextInt(360); // Picks a random number between 0-359
			angleX = Math.cos(Math.toRadians(dir)) * health * cons; // Converts
			angleY = Math.sin(Math.toRadians(dir)) * health * cons;			
		}
		if((getX() + angleX) > 768) {
			setX(768);
			dir = rand.nextInt(360); // Picks a random number between 0-359
			angleX = Math.cos(Math.toRadians(dir)) * health * cons; // Converts
			angleY = Math.sin(Math.toRadians(dir)) * health * cons;		
		} else if((getX() + angleX) < 0) {
			setX(0);
			dir = rand.nextInt(360); // Picks a random number between 0-359
			angleX = Math.cos(Math.toRadians(dir)) * health * cons; // Converts
			angleY = Math.sin(Math.toRadians(dir)) * health * cons;		
		}
		else {
			setX(getX() + angleX); // Updates the X/y positions.
		}
		if((getY() + angleY) > 1024) {
			setY(1024);
			dir = rand.nextInt(360); // Picks a random number between 0-359
			angleX = Math.cos(Math.toRadians(dir)) * health * cons; // Converts
			angleY = Math.sin(Math.toRadians(dir)) * health * cons;		
		} else if((getY() + angleY) <0) {
			setY(0);
			dir = rand.nextInt(360); // Picks a random number between 0-359
			angleX = Math.cos(Math.toRadians(dir)) * health * cons; // Converts
			angleY = Math.sin(Math.toRadians(dir)) * health * cons;		
		}
		else {
			setY(getY() + angleY);
		}
	}

	public void draw(Graphics g, Point p) {
		g.setColor(ColorUtil.rgb(getColor(), 0, 0));
		if (selected) {
			g.fillRect((int) (p.getX() + getX()), (int) (p.getY() + getY()), getSize(), getSize());
		} else {
			g.drawRect((int) (p.getX() + getX()), (int) (p.getY() + getY()), getSize(), getSize());
			//int x[] = {(int)(p.getX()-getX()), (int)(p.getX() + getX())/2, (int)(p.getX()+getX())};
			//int y[] = {(int)(p.getY()-getY()), (int)(p.getY()+getY()), (int)((p.getY() + getY())/2)};
			//g.drawPolygon(x, y, 3);
		}
	}

	public void takeDamage() {
		health--;
		color -= 25;
		setColor(color);
		// setColor
	}

	public int getHealth() {
		return health;
	}

	public int getSpeed() {
		return health;
	}

	public String toString() {
		String x = "" + getX();
		int index = x.indexOf(".");
		String y = "" + getY();
		int in = y.indexOf(".");
		return name + ": loc =" + x.substring(0, index + 2) + "," + y.substring(0, in + 2) + " color=" + getColor()
				+ " health=" + health + " size=" + getSize() + " direction=" + dir;
	}
	//selects the objects below
	public void setSelected(boolean yesNo) {
		selected = yesNo;

	}

	public boolean isSelected() { 
		return selected;
	}

	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parent’s origin
		int xLoc = (int)(pCmpRelPrnt.getX() + getX());// shape location relative
		int yLoc = (int)(pCmpRelPrnt.getY() + getY());// to parent’s origin
		if ((px >= xLoc) && (px <= xLoc + getSize()+2) && (py >= yLoc) && (py <= yLoc + getSize()+2))
			return true;
		else
			return false;
	}
}
