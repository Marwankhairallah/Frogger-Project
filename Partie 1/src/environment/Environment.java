package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;
import environment.Bird;

public class Environment implements IEnvironment {
    private Game game;
    private ArrayList<Lane> lanes;
    private ArrayList<Bird> birds;

    public Environment(Game game) {
        this.game = game;
        lanes = new ArrayList<Lane>();
        birds = new ArrayList<Bird>();
        for (int i = 0; i < game.height; i++) {
            boolean isEmpty;
            isEmpty = (game.randomGen.nextDouble() < 0.2) || i <= 2 || i == game.height - 1;
            lanes.add(new Lane(this.game, i, isEmpty));
        }

    }

    /**
     * @param c la case a vérifiée
     * @return True si la grenouille peut continuée, false sinon
     */
    public boolean isSafe(Case c) {
        for (Bird b : birds) {
            if (b.getPosition().absc == c.absc && b.getPosition().ord == c.ord) {
                return false;
            }
        }
        return lanes.get(c.ord).isSafe(c);
    }

    /**
     * @param c la case a vérifiée
     * @return True si c'est la case de fin
     */
    public boolean isWinningPosition(Case c) {

        return (c.ord == game.height - 1);
    }

    /**
     * Update de l'environnement
     *
     * @param frogs les grenouilles
     */
    public void update(ArrayList<IFrog> frogs) {
        for (Lane lane : lanes) {
            for (IFrog frog : frogs) {
                for (int s : lane.sAbs) {
                    if (s == frog.getPosition().absc && lane.ord == frog.getPosition().ord)
                        frog.move(frog.getDirection());
                }
                if (frog.getPosition().ord == lane.ord && lane.isRiver) {
                    lane.moveF(frog);
                }
            }
            lane.update();
        }
        for (IFrog frog : frogs) {
            if (game.randomGen.nextDouble() < 0.015 && frog.getPosition().ord > 1) {
                birds.add(new Bird(game, frog));
            }
        }
        for (int i = 0; i < birds.size(); i++) {
            if (!birds.get(i).update()) {
                birds.remove(birds.get(i));
            }
        }
    }
}
