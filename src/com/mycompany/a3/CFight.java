package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CFight extends Command{
	private static CFight fight;
	private GameWorld gw;
	private CFight() {
		super("Fight");
	}
	public static CFight getFight() {
		if(fight == null) {
			fight = new CFight();
		}
		return fight;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//gw.collisionAs();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
