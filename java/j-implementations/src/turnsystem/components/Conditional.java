package turnsystem.components;

import turnsystem.Game;
import turnsystem.entities.Actor;

public interface Conditional {
    boolean check(Actor owner, Game gameWorld);
}
