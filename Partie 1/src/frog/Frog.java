package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import gameCommons.Case;
import gameCommons.Direction;

public class Frog implements IFrog {

	private Game game;
	public Case pos;
	private Direction dir;

	public Frog(Game game) {
		this.game = game;
		this.dir = Direction.up;
		this.pos = new Case(game.width / 2, 0);
	}

	/**
	 *
	 * @return position frog.
	 */
	public Case getPosition() {
		return this.pos;
	}

	/**
	 * @return direction frog.
	 */
	public Direction getDirection() {
		return this.dir;
	}

	/**
	 * Deplace frog.
	 * @param key la direction de frog.
	 */

	public void move(Direction key) {
		switch(key) {
			case up:
				if (pos.ord < game.height - 1);
				pos = new Case(pos.absc, pos.ord + 1);
				break;
			case down:
				if (pos.ord > 0)
					pos = new Case(pos.absc, pos.ord - 1);
				break;
			case left:
				if (pos.absc > 0)
					pos = new Case(pos.absc - 1, pos.ord);
				break;
			case right:
				if (pos.absc < game.width - 1)
					pos = new Case(pos.absc + 1, pos.ord);
				break;
			default:
				break;
		}
		this.dir = key;
	}

	@Override
	public void moveR(boolean leftToRight) {

	}

	/**
	 * Bouge frog sur une buche en river.
	 * @param LtR la direction des buches.
	 */
	public void riverMove(boolean LtR) {
		if (LtR && pos.absc < game.width - 1) {
			this.pos = new Case(this.pos.absc + 1, this.pos.ord);
		}
		else if(pos.absc > 0) {
			this.pos = new Case(this.pos.absc - 1, this.pos.ord);
		}
	}

}
