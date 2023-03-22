package infinite;

import frog.Frog;
import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;

public class InfiniteFrog extends Frog implements IInfiniteFrog {

    public static boolean MoveD = false;

    public InfiniteFrog(Game game) {
        super(game);
    }

    /**
     * Bouge la grenouille dans la direction donee
     * @param key la direction de la grenouille
     */
    public void move(Direction key) {
        if(key == Direction.up){
            if (pos.ord < game.height/2){
                pos = new Case(pos.absc, pos.ord + 1);
            }else{
                MoveD = true;
            }
            this.dir = key;
        }else
            super.move(key);
    }
}
