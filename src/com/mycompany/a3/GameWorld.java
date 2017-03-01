package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;

public class GameWorld extends Observable {
	private final int MAXX = 1024, MAXY = 768;
	private GameObjectCollection objects;
	private int alienI, astroI, score, rescued, sneaked, totalAl, totalAs;
	private boolean soundstate;
	private Random rand;
	private Sound al_as, al_al, al_sp;

	public void init() {
		rand = new Random();
		astroI = 0;
		score = 0;
		rescued = 0;
		sneaked = 0;
		objects = new GameObjectCollection();
		SpaceShip ship = SpaceShip.getShip();
		objects.add(ship); // Position 0 is reserved for the Player
							// (SpaceShip)
		alienI = 1; // start of aliens in list. Keep track of positions
		for (int i = 0; i < 3; i++) { // Positions after that are reserved for
										// Aliens
			Aliens a = new Aliens();
			objects.add(a);
			totalAl++;
		}
		astroI = totalAl + 1; // start of astronauts in the list. To keep track
								// of positions in list
		al_as = new Sound("alien_astro_coll.wav"); 
		al_al = new Sound("alien_alien_col.wav");
		al_sp = new Sound("alien_spaceship_col.wav");
		 
		for (int i = 0; i < 4; i++) {
			objects.add(new Astronaut());
			totalAs++;
		}
		soundstate = true;
		this.setChanged();
		this.notifyObservers(this);
	}

	public int getWidth() {
		return MAXX;
	}

	public int getHeight() {
		return MAXY;
	}

	public int getScore() {
		return score;
	}

	public int getRescued() {
		return rescued;
	}

	public int sneaked() {
		return sneaked;
	}

	public int getTotalAs() {
		return totalAs;
	}

	public int getTotalAl() {
		return totalAl;
	}

	public boolean getSound() {
		return soundstate;
	}

	public void expand() {
		SpaceShip ship;
		if (objects.getIterator().elementAt(0) instanceof SpaceShip) {
			ship = (SpaceShip) objects.getIterator().elementAt(0);
			ship.expand();
			this.setChanged();
		}
		this.notifyObservers();
	}

	public void setSound() {
		if (soundstate) {
			soundstate = false;
		} else {
			soundstate = true;
		}
		this.setChanged();
		this.notifyObservers();
	}

	/*
	 * Method transfers the spaceship to the random location of a selected Alien
	 * If there are none availalbe it will not jump anywhere.
	 */
	public void jumpAl() {
		if (totalAl <= 0) {
			return;
		}
		int num = rand.nextInt(totalAl) + alienI;
		if (num > objects.getIterator().size() - 1) {
			return;
		} else {
			SpaceShip ship = (SpaceShip) objects.getIterator().elementAt(0);
			ship.jumpToLocation((GameObject) objects.getIterator().elementAt(num));
			this.setChanged();
		}
		this.notifyObservers();
	}

	/*
	 * Method transfers the spaceship to the random location of a selected
	 * Astronaut unless no astronaut exists.
	 */
	public void jumpAs() {
		if (totalAs <= 0) {
			return;
		}
		int num = rand.nextInt(totalAs) + astroI;
		if (num > objects.getIterator().size() - 1) {
			return;
		} else {
			SpaceShip ship = (SpaceShip) objects.getIterator().elementAt(0);
			ship.jumpToLocation((GameObject) objects.getIterator().elementAt(num));
			this.setChanged();
		}
		this.notifyObservers();
	}

	// updates the x positions of the spaceship (player)
	public void left() {
		SpaceShip ship = (SpaceShip) objects.getIterator().elementAt(0);
		ship.moveLeft();
		this.setChanged();
		this.notifyObservers();
	}

	public void right() {
		SpaceShip ship = (SpaceShip) objects.getIterator().elementAt(0);
		ship.moveRight();
		this.setChanged();
		this.notifyObservers();
	}

	public void up() {
		SpaceShip ship = (SpaceShip) objects.getIterator().elementAt(0);
		ship.moveUp();
		this.setChanged();
		this.notifyObservers();
	}

	public void down() {
		SpaceShip ship = (SpaceShip) objects.getIterator().elementAt(0);
		ship.moveDown();
		this.setChanged();
		this.notifyObservers();
	}

	public void contract() {
		SpaceShip ship = (SpaceShip) objects.getIterator().elementAt(0);
		ship.contract();
		this.setChanged();
		this.notifyObservers();
	}

	// updates all positions for objects that implement the Imoving interface.
	public void tick() {
		for (int i = 0; i < objects.getIterator().size(); i++) {
			if (objects.getIterator().elementAt(i) instanceof IMoving) {
				IMoving mObj = (IMoving) objects.getIterator().elementAt(i);
				mObj.move();
				this.setChanged();
			}
		}

		checkCollision();
		this.notifyObservers();

	}

	public void checkCollision() {
		try {
			IIterator i = objects.getIterator();
			while (i.hasNext()) { // Iterates through ALL objects (yes it does
									// this operation to every object at least
									// twice
				GameObject o = (GameObject) i.getNext();
				IIterator j = objects.getIterator();
				while (j.hasNext()) {
					GameObject g = (GameObject) j.getNext();
					// checks to see if this is an Alien v alien
					if (o instanceof Aliens && !o.equals(g) && o.collidesWith(g) && g instanceof Aliens) {
						((Aliens) o).handleCollision((Aliens) g, this);
						// collisionAl(o);
					} else if (o instanceof Aliens && g instanceof Astronaut && o.collidesWith(g)
							&& g.collidesWith(o)) { // Astronaut v alien
						((Astronaut) g).handleCollision((Aliens) o, this);
					} else if (o instanceof SpaceShip && g instanceof Aliens && o.collidesWith(g)) { // Al v As
						((SpaceShip) o).handleCollision((Aliens) g, this);
					} else if (o instanceof SpaceShip && g instanceof Astronaut && o.collidesWith(g)) { //S v A
						((SpaceShip) o).handleCollision((Astronaut) g, this);
					}
				}
			}
		} catch (Exception e) { //To stop a null pointer exception
			return;
		}
	}

	// Grabs the astronauts/aliens and updates score accordingly.
	public void score(GameObject o) {
		SpaceShip ship = (SpaceShip) objects.getIterator().elementAt(0);
		IIterator it = objects.getIterator();
		boolean found = false;
		while (it.hasNext() && !found) { // searches for the object it collided
											// with so it can remove it properly
			GameObject temp = (GameObject) it.getNext();
			found = temp.equals(o);
		}
		// if (ship.getX() == o.getX() && ship.getY() == o.getY()) {
		if (o instanceof Aliens) { // if it is an alien it needs to adjust
									// certain scores and removes objects
			it.remove();
			totalAl--;
			sneaked++;
			score--;
			astroI--;
			al_sp.play();
		} else if (o instanceof Astronaut) { // adjusts scores and removes
												// object
			it.remove();
			totalAs--;
			rescued++;
			score += ((Astronaut) o).getHealth();
		}
		this.setChanged();
		this.notifyObservers(this);

	}

	// Collision with an alien has occured. They will duplicate aliens.
	public void collisionAl(ICollider o, ICollider a) {
		if (totalAl < 2) {
			System.out.println("Err, not enough aliens for this type of collision");
		} else if (totalAl >= 30) {
			// to keep the aliens from getting bigger than 30 on the screen
		} else {
			// ((Aliens)o).changeColl(true);
			// ((Aliens)a).changeColl(true);
			Aliens temp;
			temp = new Aliens(((int) ((GameObject) o).getX()), ((int) ((GameObject) o).getY()));
			// temp.changeColl(true);
			temp.addToVector(o); // adds all aliens present (including
									// generated)
			temp.addToVector(a); // to eachothers collision vectors so they
			((Aliens) o).addToVector(temp); // cannot make another one until
											// later
			((Aliens) o).addToVector(a);
			((Aliens) a).addToVector(temp);
			((Aliens) a).addToVector(o);
			objects.add(temp); // adds new alien to the list
			totalAl++;
			this.setChanged();
		}
		al_al.play();
		this.notifyObservers(this);
	}

	// Collision has occured between an alien and astronaut. Astronaut loses
	// health and will
	// die if hp goes below 0.
	public void collisionAs(Astronaut a) {

		if (totalAs < 1 || totalAl < 1) {
			System.out.println("err, no astronauts or aliens present for collision");
		} else {
			IIterator it = objects.getIterator();
			while (it.hasNext()) { // iterates through all objects so that it
									// can remove it if need be
				GameObject o = (GameObject) it.getNext();
				if (o instanceof Astronaut && o.equals(a)) {
					((Astronaut) o).takeDamage();
					if (((Astronaut) o).getHealth() < 1) {
						it.remove();
						totalAs--;
					}
					this.setChanged();
				}
			}
		}
		al_as.play();
		this.notifyObservers(this);
	}

	// Prints the scores of the current game.
	public void printPoints() {
		System.out.println("Score:" + score + " Rescued:" + rescued + " Aliens on board:" + sneaked
				+ " Astronauts Left:" + totalAs + " Aliens Left:" + totalAl);
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
	}

	// outputs the map (all objects and their positions/data).
	public void map() {
		IIterator iterator = objects.getIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.getNext());
		}
		/*
		 * for(int i = 0; i < objects.getIterator().size(); i++) {
		 * System.out.println(objects.getIterator().elementAt(i)); }
		 */
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------");
	}

	public GameObjectCollection getGameObjects() {
		return objects;
	}

	// Prompts for a yes or no on exiting the game.
	public void exit() {
		System.exit(0);
	}
}
