package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CMoveLeft extends Command{
	private static CMoveLeft left;
	private GameWorld gw;
	private CMoveLeft() {
		super("Left");
	}
	public static CMoveLeft getMoveLeft() {
		if(left == null) {
			left = new CMoveLeft();
		}
		return left;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.left();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
