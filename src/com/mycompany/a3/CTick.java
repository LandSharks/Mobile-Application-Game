package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CTick extends Command{
	private static CTick tick;
	private GameWorld gw;
	private CTick() {
		super("tick");
	}
	public static CTick getTick() {
		if(tick == null) {
			tick = new CTick();
		}
		return tick;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.tick();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
