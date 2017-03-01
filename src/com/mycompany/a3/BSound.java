package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BSound implements Runnable{
	private Media m;
	private boolean toggle;
	public void run() {
		m.setTime(0);
		toggle = false;
		m.play();
	}
	public BSound(String fileName) {
		try {
			InputStream is = Display.getInstance().
					getResourceAsStream(getClass(), "/"+fileName);
			m = MediaManager.createMedia(is, "audio/wav", this);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean toggle() {
		toggle = !toggle;
		if(toggle) {
			m.play();
			return true;
		} else {
			m.pause();
			return false;
		}
	}
	private void pause() {
		m.pause();
	}
	private void play() {
		m.play();
	}
}
