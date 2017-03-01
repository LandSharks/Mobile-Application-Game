package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public interface ISelectable {
	public void setSelected(boolean yesNo);
	// a way to test whether an object is selected
	public boolean isSelected();
	// a way to determine if a pointer is â€œinâ€? an object
	// pPtrRelPrnt is pointer position relative to the parent origin
	// pCmpRelPrnt is the component position relative to the parent origin
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	// a way to â€œdrawâ€? the object that knows about drawing
	// different ways depending on â€œisSelectedâ€?
	public void draw(Graphics g, Point pCmpRelPrnt);
}
