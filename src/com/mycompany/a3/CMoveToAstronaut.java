package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CMoveToAstronaut extends Command{
	private static CMoveToAstronaut a;
	private GameWorld gw;
	private CMoveToAstronaut() {
		super("MoveToAstronaut");
	}
	public static CMoveToAstronaut getMoveToAstronaut() {
		if(a == null) {
			a = new CMoveToAstronaut();
		}
		return a;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.jumpAs();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
