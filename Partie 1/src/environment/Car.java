package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;
	private boolean L;
	boolean isL;
	private int template;

	//TODO Constructeur(s)

	public Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.isL = isL;
		if (isL) {
			length = game.randomGen.nextInt(4) + 2;
			if (isL) {
				length = 1;
			}
		} else {
			length = game.randomGen.nextInt(2) + 1;
		}
		if (this.leftToRight) {
			if (this.length == 1) {
				this.template = game.randomGen.nextInt(2) + 2;
			} else {
				this.template = 5;
			}
		} else {
			if (this.length == 1) {
				this.template = game.randomGen.nextInt(2);
			} else {
				this.template = 4;
			}
		}
		if (isL)
			this.template = 23;
		addToGraphics();
	}

	//TODO : ajout de methodes

	/**
	 * @return La case à droite de car
	 */
	public int getRight() {

		return leftPosition.absc + length - 1;
	}

	/**
	 * @return La case à gauche de car
	 */
	public int getLeft() {
		return leftPosition.absc;
	}

	/**
	 * @return true si la voiture est sur le screen
	 */
	public boolean screen() {
		return (this.leftPosition.absc > -6 && this.leftPosition.absc < game.width + 6);
	}

	/**
	 * Deplace le spawn de voiture
	 */
	public void Down() {
		this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord - 1);
	}

	/**
	 * Deplace car
	 */
	public void moveC() {
		if (!isL) {
			if (this.leftToRight) {
				this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
			}
			else {
				this.leftPosition = new Case(this.leftPosition.absc - 1, this.leftPosition.ord);
			}
		}
	}

	
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	protected void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
