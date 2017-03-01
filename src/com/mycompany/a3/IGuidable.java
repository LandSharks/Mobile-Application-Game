package com.mycompany.a3;
/*This interface assures that every
 * Object that wants to be guided by user input
 * MUST provide up,down,left,right and jump location
 * methods.
 */
public interface IGuidable {
	public void moveRight();
	public void moveLeft();
	public void moveUp();
	public void moveDown();
	public void jumpToLocation(GameObject o);
}
