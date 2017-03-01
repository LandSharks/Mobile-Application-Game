package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

/* This object keeps track of all game involved information.
 * Interacts directly with game world (the controller) 
 * of the overall game. Operates as the "view"
 * 
 */
public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private boolean flag, play;
	private Toolbar tool;
	private Button heal;
	private UITimer t;

	public Game() { // Creates the game world, initializes it, and beings
					// playing.
		gw = new GameWorld();
		mv = new MapView(gw.getWidth(), gw.getHeight());
		sv = new ScoreView();
		gw.addObserver(mv);
		gw.addObserver(sv);
		play = true;
		t = new UITimer(this);
		t.schedule(20, true, this);
		gw.init();
		play();
	}

	public void run() {
		gw.tick(); //moves objects
	}

	public void toggle(){
		play = !play; //sets pause
		if(play) {// play
			t.schedule(20, true, this);
			heal.setEnabled(false);
			heal.getAllStyles().setFgColor(ColorUtil.BLUE);
			heal.getAllStyles().setBgTransparency(255);
			heal.getAllStyles().setPadding(50, 50, 50, 50);
			heal.getAllStyles().setBgColor(ColorUtil.WHITE);
		} else {
			t.schedule(0, false, this); //pause
			heal.setEnabled(true);
			heal.getAllStyles().setFgColor(ColorUtil.WHITE);
			heal.getAllStyles().setBgTransparency(255);
			heal.getAllStyles().setPadding(50, 50, 50, 50);
			heal.getAllStyles().setBgColor(ColorUtil.BLUE);
		}
	}

	private void play() {

		flag = false;
		tool = new Toolbar();
		tool.setGlobalToolbar(true);
		setToolbar(tool);
		// Setting Up Containers
		Container west = new Container();
		west.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		Container east = new Container();
		east.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		Container south = new Container();
		south.getUnselectedStyle().setAlignment(CENTER, true);
		south.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		this.setLayout(new BorderLayout());

		// Button Set up
		Button expand = new Button("Expand");
		Button up = new Button("Up");
		Button down = new Button("Down");
		Button moveToAs = new Button("MoveToAstronaut");
		Button contract = new Button("Contract");
		Button left = new Button("Left");
		Button right = new Button("Right");
		Button moveToAl = new Button("MoveToAliens");
		//Button newAlien = new Button("NewAlien");
		Button pause = new Button("Pause");
		heal = new Button("heal");

		// Styling for Buttons
		expand.getAllStyles().setFgColor(ColorUtil.WHITE);
		expand.getAllStyles().setBgTransparency(255);
		expand.getAllStyles().setPadding(50, 50, 50, 50);
		expand.getAllStyles().setBgColor(ColorUtil.BLUE);
		
		heal.getAllStyles().setFgColor(ColorUtil.BLUE);
		heal.getAllStyles().setBgTransparency(255);
		heal.getAllStyles().setPadding(50, 50, 50, 50);
		heal.getAllStyles().setBgColor(ColorUtil.WHITE);

		pause.getAllStyles().setFgColor(ColorUtil.WHITE);
		pause.getAllStyles().setBgTransparency(255);
		pause.getAllStyles().setPadding(50, 50, 50, 50);
		pause.getAllStyles().setBgColor(ColorUtil.BLUE);

		/*newAlien.getAllStyles().setFgColor(ColorUtil.WHITE);
		newAlien.getAllStyles().setBgTransparency(255);
		newAlien.getAllStyles().setPadding(50, 50, 50, 50);
		newAlien.getAllStyles().setBgColor(ColorUtil.BLUE);*/

		moveToAl.getAllStyles().setFgColor(ColorUtil.WHITE);
		moveToAl.getAllStyles().setBgTransparency(255);
		moveToAl.getAllStyles().setPadding(50, 50, 50, 50);
		moveToAl.getAllStyles().setBgColor(ColorUtil.BLUE);

		contract.getAllStyles().setFgColor(ColorUtil.WHITE);
		contract.getAllStyles().setBgTransparency(255);
		contract.getAllStyles().setPadding(50, 50, 50, 50);
		contract.getAllStyles().setBgColor(ColorUtil.BLUE);

		right.getAllStyles().setFgColor(ColorUtil.WHITE);
		right.getAllStyles().setBgTransparency(255);
		right.getAllStyles().setPadding(50, 50, 50, 50);
		right.getAllStyles().setBgColor(ColorUtil.BLUE);

		left.getAllStyles().setFgColor(ColorUtil.WHITE);
		left.getAllStyles().setBgTransparency(255);
		left.getAllStyles().setPadding(50, 50, 50, 50);
		left.getAllStyles().setBgColor(ColorUtil.BLUE);

		up.getAllStyles().setFgColor(ColorUtil.WHITE);
		up.getAllStyles().setBgTransparency(255);
		up.getAllStyles().setPadding(50, 50, 50, 50);
		up.getAllStyles().setBgColor(ColorUtil.BLUE);

		down.getAllStyles().setFgColor(ColorUtil.WHITE);
		down.getAllStyles().setBgTransparency(255);
		down.getAllStyles().setPadding(50, 50, 50, 50);
		down.getAllStyles().setBgColor(ColorUtil.BLUE);

		moveToAs.getAllStyles().setFgColor(ColorUtil.WHITE);
		moveToAs.getAllStyles().setBgTransparency(255);
		moveToAs.getAllStyles().setPadding(50, 50, 50, 50);
		moveToAs.getAllStyles().setBgColor(ColorUtil.BLUE);

		// Styling for Containers
		west.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		west.getAllStyles().setPaddingTop(100);
		east.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		east.getAllStyles().setPaddingTop(100);
		south.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		south.getAllStyles().setPaddingLeft(650);

		// Creating Commands
		CMoveDown cdown = CMoveDown.getMoveDown();
		CMoveLeft cleft = CMoveLeft.getMoveLeft();
		CMoveRight cright = CMoveRight.getMoveRight();
		CMoveUp cup = CMoveUp.getMoveup();
		CFight cfight = CFight.getFight();
		CExpand cexpand = CExpand.getExpand();
		CContract ccontract = CContract.getContract();
		CMoveToAlien calien = CMoveToAlien.getMoveToAlien();
		CMoveToAstronaut castro = CMoveToAstronaut.getMoveToAstronaut();
		CScore cscore = CScore.getScore();
		CPause cpause = CPause.getPause();
		//CGenerateAlien cgen = CGenerateAlien.getGenerateAlien();
		CExit exit = CExit.getexit();
		CHelp help = CHelp.getHelp();
		CInfo info = CInfo.getinfo();
		CSound sound = CSound.getSound();
		CHeal cheal = CHeal.getHeal();

		// Setting Target GameWorld
		cdown.setTarget(gw);
		cup.setTarget(gw);
		cleft.setTarget(gw);
		cright.setTarget(gw);
		cfight.setTarget(gw);
		cexpand.setTarget(gw);
		ccontract.setTarget(gw);
		calien.setTarget(gw);
		castro.setTarget(gw);
		cpause.setTarget(this);
		//cgen.setTarget(gw);
		cscore.setTarget(gw);
		cdown.setTarget(gw);
		exit.setTarget(gw);
		help.setTarget(gw);
		info.setTarget(gw);
		sound.setTarget(gw);
		cheal.setTarget(gw);

		// Linking Commands to buttons
		expand.setCommand(cexpand);
		up.setCommand(cup);
		down.setCommand(cdown);
		moveToAs.setCommand(castro);
		moveToAl.setCommand(calien);
		contract.setCommand(ccontract);
		left.setCommand(cleft);
		right.setCommand(cright);
		pause.setCommand(cpause);
		//newAlien.setCommand(cgen);
		heal.setCommand(cheal);

		// Setting up key listeners
		addKeyListener('e', cexpand);
		addKeyListener('c', ccontract);
		addKeyListener('s', cscore);
		addKeyListener('r', cright);
		addKeyListener('l', cleft);
		addKeyListener('u', cup);
		addKeyListener('d', cdown);
		addKeyListener('o', castro);
		addKeyListener('a', calien);
		//addKeyListener('w', cgen);
		addKeyListener('f', cfight);
		addKeyListener('t', cpause);

		// Adding Toolbars
		tool.addCommandToSideMenu(cscore);
		tool.addCommandToSideMenu(exit);
		tool.addCommandToSideMenu(info);
		tool.addCommandToRightBar(help);
		tool.addCommandToSideMenu(sound);
		tool.setTitle("Space Jam TM");

		// Adding To Layout
		west.add(expand);
		west.add(up);
		west.add(left);
		west.add(moveToAs);
		this.add(BorderLayout.WEST, (west));
		east.add(contract);
		east.add(down);
		east.add(right);
		east.add(moveToAl);
		this.add(BorderLayout.EAST, east);
		//south.add(newAlien);
		south.add(pause);
		south.add(heal);
		this.add(BorderLayout.SOUTH, south);
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);
		// Label myLabel = new Label("Enter a Command:");
		// this.addComponent(myLabel);
		// final TextField myTextField = new TextField();
		// this.addComponent(myTextField);
		this.show();
	} // new ActionListener()
}
