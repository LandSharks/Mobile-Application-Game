package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;

	public MapView(int x, int y) {
		Label label = new Label();
		this.setLayout(new FlowLayout());
		this.getAllStyles().setPadding(x, x, y, y);
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		this.addComponent(label);
	}

	public void update(Observable observable, Object data) {
		if (observable instanceof GameWorld) {
			gw = ((GameWorld) observable);
			gw.map();
			this.repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (IIterator i = gw.getGameObjects().getIterator(); i.hasNext();) {
			GameObject o = (GameObject) i.getNext();
			Point p = new Point(super.getAbsoluteX(), super.getAbsoluteY());
			o.draw(g, p);
		}
	}
	@Override
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		IIterator i = gw.getGameObjects().getIterator();
		while(i.hasNext()) {
			GameObject o = (GameObject)(i.getNext());
			if(o instanceof Astronaut) {
				if(((Astronaut)o).contains(pPtrRelPrnt, pCmpRelPrnt)){
					((Astronaut) o).setSelected(true);
				} else {
					((Astronaut) o).setSelected(false);
				}
			}
		}
		repaint();
	}
}
