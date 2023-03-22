package infinite;

import gameCommons.IEnvironment;
import gameCommons.IFrog;

import java.util.ArrayList;

public interface IInfiniteEnv extends IEnvironment {

    /**
     * supprime la lane du bas, reorganise les donnees de l'instance d'environment,
     * appel des fonctions pour baisser tous les ordonnées des elements de l'environment et ajoute une ligne en haut
     */
    void downEnvironment();

    /**
     *update pour que l'instance puisse utiliser downEnvironment en fonction de
     *la position de la Frog et de la direction
     * @param frogs toutes les grenouilles
     */
    void update(ArrayList<IFrog> frogs);

}
