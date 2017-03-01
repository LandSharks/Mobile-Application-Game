package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CScore extends Command{
	private static CScore score;
	private GameWorld gw;
	private CScore() {
		super("Score");
	}
	public static CScore getScore() {
		if(score == null) {
			score = new CScore();
		}
		return score;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
