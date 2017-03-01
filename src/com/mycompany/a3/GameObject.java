package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
/* Game Object
 * Purpose: To establish rules for all entities involved with the game.
 * Keeps track of x/y positions and forces all child classes to
 * create and manage their own sizes/colors.
 * 
 */
public abstract class GameObject implements IDrawable, ICollider{
	private double x,y;
	public GameObject() {
		
	}
	public void setY(double pos) {
		y = pos;
	}
	public void setX(double pos) {
		x = pos;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public abstract int getSize();
	public abstract int getColor();
	public abstract void setColor(int c);
	public abstract void changeSize(int num);
}
