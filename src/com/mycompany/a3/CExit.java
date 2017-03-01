package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;

public class CExit extends Command{
	private static CExit exit;
	private GameWorld gw;
	private Dialog d;
	private CExit() {
		super("exit");
		d = new Dialog("Exit");
        TextArea popupBody = new TextArea("This is the body of the popup", 3, 10);
        popupBody.setUIID("PopupBody");
        popupBody.setEditable(false);
        d.setLayout(new BorderLayout());
        d.add(BorderLayout.CENTER, popupBody);
	}
	public static CExit getexit() {
		if(exit == null) {
			exit = new CExit();
		}
		return exit;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.exit();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
