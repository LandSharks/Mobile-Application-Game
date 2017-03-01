package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CPause extends Command {
	private static CPause pause;
	private Game g;
	private boolean toggle;

	private CPause() {
		super("pause");
	}

	public static CPause getPause() {
		if (pause == null) {
			pause = new CPause();
		}
		return pause;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		g.toggle();

	}

	public void setTarget(Game w) {
		g = w;
	}
}
