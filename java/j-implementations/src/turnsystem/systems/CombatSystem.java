package turnsystem.systems;

import turnsystem.Game;

public class CombatSystem implements GameSystem{

    private final boolean heroDeath = false;
    private final TurnSystem turnSystem;

    public CombatSystem(TurnSystem turnSystem){
        this.turnSystem = turnSystem;
    }

    @Override
    public void process(Game gameWorld) {

    }
}
