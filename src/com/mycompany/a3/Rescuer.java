package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
/* Rescuer is a child class of GameObject
 * and implements the IGuidable interface.
 * This is the object for all player interactions.
 * It is forced to keep track of color and size.
 * 
 */
public abstract class Rescuer extends GameObject implements IGuidable{
	private int color, size;
	private double x,y;
	private ColorUtil cou;
	public Rescuer () {
		
	}
	public int getColor() {
		return color;
	}
	public void setColor(int c) {
		cou.rgb(c,c,c);
		color = c;
	}
	public void changeSize(int num) {
		size = num;
	}

	public int getSize() {

		return size;
	}
	public abstract String toString();
	public abstract int getSpeed();
}
