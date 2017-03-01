package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
/* Opponent Class
 * This class is a child of GameObject and implements the
 * interface IMoving. 
 * 
 * This particular class will track information like color
 * and size. It is the class for Non-player characters within the game.
 */
public abstract class Opponent extends GameObject implements IMoving{
	private boolean noResize;
	private Random rand;
	private int color, size;
	private ColorUtil cou;
	public Opponent() {
		rand = new Random();
		size = rand.nextInt(31) + 20; //Setting its size to a random number between 20 and 50
	}
	public int getColor() {
		return color;
	}
	public void setColor(int c) {
		cou.rgb(c,c,c);				//Setting up its color, everything will be a base for now.
		color = c;
	}
	public void changeSize(int num) { 
		size = num;
	}

	public int getSize() {

		return size;
	}
	public abstract int getSpeed();
	public abstract String toString();
}
