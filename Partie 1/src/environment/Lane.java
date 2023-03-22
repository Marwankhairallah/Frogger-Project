package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IFrog;
import graphicalElements.Element;

public class Lane {
	private Game game;
	protected int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private boolean isEmpty;
	public ArrayList<Integer> sAbs = new ArrayList<>();
	public boolean isRiver;
	private int template;
	private int fps;
	private double sc;

	public Lane(Game game, int ord, boolean isEmpty) {
		this.ord = ord;
		this.game = game;
		this.sc = 0.02;
		this.speed = this.game.randomGen.nextInt(4) + game.minSpeedInTimerLoops;
		this.leftToRight = this.game.randomGen.nextBoolean();
		if (!isEmpty) {
			isRiver = false;
			this.density = this.game.defaultDensity;
			template = 19;
			if (game.randomGen.nextDouble() < density) {
				isRiver = true;
				template = 20;
			}

		}
		else {
			System.out.println("Null");
			isRiver = false;
			template = 21;
			this.density = 0;
		}
		this.fps = 1;
		for (int i = 0; i < this.game.width; i++) {
			if (game.randomGen.nextDouble() < sc && !isRiver) {
				sAbs.add(i);
			}
			Case c = new Case(i, ord);
			if ((isRiver && !isSafe(c)) || (!isRiver && isSafe(c))) {
				if (game.randomGen.nextDouble() < density) {
					cars.add(new Car(game, c, leftToRight));
				}
			}
		}
	}


	// TODO : Constructeur(s)

	public void update() {
		for (int i = 0; i < game.width; i++) {
			game.getGraphic().add(new Element(i, ord, template));
		}
		for (int i : sAbs) {
			game.getGraphic().add(new Element(i, this.ord, 22));
		}
		if (!isEmpty) {
			for (Car c : cars) {                // Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
				c.addToGraphics();             // d'horloge" �gal � leur vitesse
			}                                 // Notez que cette m�thode est appel�e � chaque tic d'horloge
		}                                    // Les voitures doivent etre ajoutes a l interface graphique meme quand
		if (fps % speed == 0) {             // elle ne bougent pas
			for (Car c : cars) {           // A chaque tic d'horloge, une voiture peut �tre ajout�e
				c.moveC();
			}
			for (int i = 0; i < cars.size(); i++) {
				if (!cars.get(i).screen()) {
					cars.remove(i);
				}
			}
		}
		fps++;
		if (!isEmpty) {
			mayAddCar();
		}

	}

	// TODO : ajout de methodes

	public boolean isSafe(Case c) {
		for (Car car : cars) {
			if (car.getLeft() <= c.absc && car.getRight() >= c.absc) {
				return isRiver;
			}
		}
		return !isRiver;
	}

	public void moveF(IFrog frog) {
		if (isSafe(frog.getPosition()) && fps % speed == 1) {
			frog.moveR(leftToRight);
		}
	}

	public void Down() {
		this.ord--;
		for (Car c : cars) {
			c.Down();
		}
	}

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
