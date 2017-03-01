package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CInformation extends Command{
	private static CInformation info;
	private GameWorld gw;
	private CInformation() {
		super("info");
	}
	public static CInformation getCInformation() {
		if(info == null) {
			info = new CInformation();
		}
		return info;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
