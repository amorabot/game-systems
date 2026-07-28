package turnsystem.components.quirks.conditions;

import turnsystem.Game;
import turnsystem.components.Conditional;
import turnsystem.entities.Actor;

public class FirstTurnCondition implements Conditional {
    @Override
    public boolean check(Actor owner, Game gameWorld) {
        return false;
    }
}
