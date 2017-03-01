package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CMoveDown extends Command{
	private static CMoveDown down;
	private GameWorld gw;
	private CMoveDown() {
		super("Down");
	}
	public static CMoveDown getMoveDown() {
		if(down == null) {
			down = new CMoveDown();
		}
		return down;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.down();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
