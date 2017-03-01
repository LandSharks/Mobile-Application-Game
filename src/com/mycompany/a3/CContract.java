package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CContract extends Command{
	private static CContract contract;
	private GameWorld gw;
	private CContract() {
		super("contract");
	}
	public static CContract getContract() {
		if(contract == null) {
			contract = new CContract();
		}
		return contract;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.contract();
	}
	public void setTarget(GameWorld w) {
		gw = w;
	}
}
