package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class ScoreView extends Container implements Observer {
	public ScoreView() {
		Label gameInfo = new Label();
		gameInfo.getStyle().setFgColor(ColorUtil.BLUE);
		this.setLayout(new FlowLayout());
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		this.getAllStyles().setPaddingLeft(200);
		this.addComponent(gameInfo);
	}
	public void update(Observable observable, Object data) {
		if(observable instanceof GameWorld) {
			GameWorld gw = ((GameWorld)observable);
			gw.printPoints();
			((Label)(this.getComponentAt(0))).setText("Score: " + gw.getScore() + " Astronauts Rescued: " + gw.getRescued() + " Aliens Sneaked In: "
					+ gw.sneaked() + " Astronauts Remaining: " + gw.getTotalAs() + " Aliens Renamining: " + gw.getTotalAl() + " Sound: " + gw.getSound());
			
		} else {
			System.out.println("ERROR, something wrong with notify");
		}
	}

}
