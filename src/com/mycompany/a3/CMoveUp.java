package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CMoveUp extends Command{
	private static CMoveUp up;
	private GameWorld gw;
	private CMoveUp() {
		super("up");
	}
	public static CMoveUp getMoveup() {
		if(up == null) {
			up = new CMoveUp();
		}
		return up;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.up();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
