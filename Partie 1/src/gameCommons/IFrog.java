package gameCommons;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection();
	
	/**
	 * D�place frog
	 * @param key
	 */
	public void move(Direction key);

	/**
	 * Déplace frog dans riviere
	 * @param leftToRight
	 */
	public void moveR(boolean leftToRight);
}

