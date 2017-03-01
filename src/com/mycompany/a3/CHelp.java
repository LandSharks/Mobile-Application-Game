package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;

public class CHelp extends Command{
	private static CHelp help;
	private Dialog d;
	private GameWorld gw;
	private CHelp() {
		super("Help");
		d = new Dialog("Help");
        TextArea popupBody = new TextArea("This is the body of the popup", 3, 10);
        popupBody.setUIID("PopupBody");
        popupBody.setEditable(false);
        d.setLayout(new BorderLayout());
        d.add(BorderLayout.CENTER, popupBody);
	}
	public static CHelp getHelp() {
		if(help == null) {
			help = new CHelp();
		}
		return help;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
