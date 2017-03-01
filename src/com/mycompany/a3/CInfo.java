package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;

public class CInfo extends Command{
	private static CInfo info;
	private GameWorld gw;
	private Dialog d;
	private CInfo() {
		super("info");
		d = new Dialog("About");
        TextArea popupBody = new TextArea("This is the body of the popup", 3, 10);
        popupBody.setUIID("PopupBody");
        popupBody.setEditable(false);
        d.setLayout(new BorderLayout());
        d.add(BorderLayout.CENTER, popupBody);
       
	}
	public static CInfo getinfo() {
		if(info == null) {
			info = new CInfo();
		}
		return info;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		d.show();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
