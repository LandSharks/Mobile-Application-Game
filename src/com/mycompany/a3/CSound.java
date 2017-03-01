package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CSound extends Command{
	private static CSound sound;
	private BSound bg;
	private GameWorld gw;
	private CSound() {
		super("sound");
		/* The song: The Space Joy was created by Voytek Pavlik and is Available for
		 * any form of release. The audio file has been changed to .wav
		 * https://creativecommons.org/licenses/by/3.0/
		 */
		bg = new BSound("The_Space_Joy.wav");
		bg.toggle();
		
	}
	public static CSound getSound() {
		if(sound == null) {
			sound = new CSound();
		}
		return sound;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.setSound();
		bg.toggle();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
