package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CMoveToAlien extends Command{
	private static CMoveToAlien a;
	private GameWorld gw;
	private CMoveToAlien() {
		super("MoveToAlien");
	}
	public static CMoveToAlien getMoveToAlien() {
		if(a == null) {
			a = new CMoveToAlien();
		}
		return a;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.jumpAl();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
