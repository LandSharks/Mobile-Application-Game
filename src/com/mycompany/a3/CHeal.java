package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CHeal extends Command{
	private static CHeal heal;
	private GameWorld gw;
	private CHeal() {
		super("heal");
	}
	public static CHeal getHeal() {
		if(heal == null) {
			heal = new CHeal();
		}
		return heal;
	}
	@Override
	public void actionPerformed(ActionEvent e) { //finds objects that are selected and heal them
		IIterator i = gw.getGameObjects().getIterator();
		while(i.hasNext()) {
			GameObject o = (GameObject)i.getNext();
			if(o instanceof Astronaut) {
				if(((Astronaut) o).isSelected()) {
					((Astronaut) o).heal();
				}
			}
		}
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}

}
