package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CExpand extends Command{
	private static CExpand expand;
	private GameWorld gw;
	private CExpand() {
		super("expand");
	}
	public static CExpand getExpand() {
		if(expand == null) {
			expand = new CExpand();
		}
		return expand;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.expand();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
