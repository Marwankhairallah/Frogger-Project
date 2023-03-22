package environment;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IFrog;

import gameCommons.Game;

import graphicalElements.Element;
import gameCommons.Direction;
import java.awt.Color;

public class Bird {

    private Case position;
    private Game game;
    private int v;
    private Direction direction;
    private int fps;

    /**
     *
     * @return la position de la grenouille
     */
    public Case getPosition() {

        return this.position;
    }

    /**
     * Donne des nouvelles coordonées
     */
    public void coor() {
        this.position = new Case(position.absc, position.ord - 1);
    }

    /**
     * update obstacle (bird)
     * @return true si toujours sur l'écran
     */
    public boolean update() {
        this.fps++;
        boolean Screen = true; //A faire dans Lane.java//
        if (fps % this.v == 0) {
            switch (this.direction) {
                case right:
                    if (position.absc == game.width - 1)
                        Screen = false;
                    else {
                        position = new Case(position.absc + 1, position.ord);
                        Screen = true;

                    }
                    break;
                case left:
                    if (position.absc == 0)
                        Screen = false;
                    else {
                        position = new Case(position.absc - 1, position.ord);
                        Screen = true;
                    }
                    break;
                case up:
                    if (position.ord == game.height - 1)
                        Screen = false;
                    else {
                        position = new Case(position.absc, position.ord + 1);
                        Screen = true;
                    }
                    break;
                default:
                    if (position.ord == 0)
                        Screen = false;
                    else {
                        position = new Case(position.absc, position.ord - 1);
                        Screen = true;
                    }
                    break;
            }
        }
        game.getGraphic().add(new Element(this.position, 18)); //Compléter Element pour initialisé color int.//
        return Screen;
    }

    public Bird(Game game, IFrog frog) {
        this.game = game;
        switch (this.game.randomGen.nextInt(4)) {
            case 0:
                this.direction = Direction.right;
                this.position = new Case(0, frog.getPosition().ord);
                break;
            case 1:
                this.direction = Direction.up;
                this.position = new Case(frog.getPosition().absc, 0);
                break;
            case 2:
                this.direction = Direction.left;
                this.position = new Case(game.width, frog.getPosition().ord);
                break;
            default:
                this.direction = Direction.down;
                this.position = new Case(frog.getPosition().absc, game.height);
                break;
        }
        this.v = 2;
        this.fps = 1;
    }

}
