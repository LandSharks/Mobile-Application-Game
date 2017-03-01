package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CGenerateAlien extends Command{
	private static CGenerateAlien a;
	private GameWorld gw;
	private CGenerateAlien() {
		super("GenerateAlien");
	}
	public static CGenerateAlien getGenerateAlien() {
		if(a == null) {
			a= new CGenerateAlien();
		}
		return a;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Aliens temp = new Aliens();
		gw.getGameObjects().add(temp);
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
