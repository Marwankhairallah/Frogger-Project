package infinite;

import gameCommons.IFrog;
import environment.Environment;
import environment.Lane;
import gameCommons.Game;
import gameCommons.Direction;

import java.util.ArrayList;

public class InfiniteEnv extends Environment implements IInfiniteEnv {
    private Direction dir;

    public InfiniteEnv(Game game) {
        super(game);
        boolean isEmpty = (game.randomGen.nextDouble() < 0.2);
        lanes.add(new Lane(this.game, game.height - 1, isEmpty));
        this.dir = Direction.up;
    }

    /**
     * Baisse toutes les lignes de l'environnement
     */
    public void downEnvironment(){
        boolean isEmptyLane;
        isEmptyLane = (game.randomGen.nextDouble() < 0.2);
        lanes.add(new Lane(this.game, game.height, isEmptyLane));
        lanes.remove(0);
        for (Lane lane : lanes) {
            lane.Down();
        }
        for (int i = 0; i< birds.size(); i++){
            birds.get(i).coor();
        }
    }

    /**
     * Met a jour l'environnement
     * @param frogs toutes les grenouilles
     */
    public void update(ArrayList<IFrog> frogs) {
        super.update(frogs);
        if(InfiniteFrog.MoveD){
            InfiniteFrog.MoveD = false;
            downEnvironment();
        }
    }
}
