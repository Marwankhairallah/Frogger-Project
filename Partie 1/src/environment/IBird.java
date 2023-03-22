package environment;

import gameCommons.Case;
import gameCommons.Game;

public interface IBird {
    public Case getPosition();
    public void down();
    public boolean update();
}
