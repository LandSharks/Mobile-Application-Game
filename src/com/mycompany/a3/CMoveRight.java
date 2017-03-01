package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CMoveRight extends Command{
	private static CMoveRight right;
	private GameWorld gw;
	private CMoveRight() {
		super("Right");
	}
	public static CMoveRight getMoveRight() {
		if(right == null) {
			right = new CMoveRight();
		}
		return right;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.right();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
