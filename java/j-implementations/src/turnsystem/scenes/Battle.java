package turnsystem.scenes;

import turnsystem.Game;
import turnsystem.Torch;
import turnsystem.data.Environments;
import turnsystem.entities.Actor;
import turnsystem.systems.CombatSystem;
import turnsystem.systems.TurnSystem;

public class Battle extends Scene {

    private Actor[] party;
    private Actor[] foes;

    private Environments battleEnvironment;
    private final Torch torch = new Torch();

    private final TurnSystem turnSystem;
    private final CombatSystem combatSystem;

    public Battle(Game gameWorld) {
        super(gameWorld);
        //Fetch the party and foes for this encounter
        party = new Actor[3]; //Placeholders for now
        foes = new Actor[3];
        //Initialize turn system
        this.turnSystem = new TurnSystem(party,foes);
        //Initialize the combat, after the turn structure has been decided
        this.combatSystem = new CombatSystem(turnSystem);
    }

    @Override
    public void process() {
        //Advance/Pause combat given the system states

        //[Check blog] TODO: add turn action handling, compilation, quirks
    }

    @Override
    public void handleInput(String input) {
        System.out.println("Handled >> " + input);
        switch (input){
            case "Begin battle":
                System.out.println("RAHHH");
                break;
            case "Finish turn":
                System.out.println("Executing turn");
                break;
            case "Exit":
                world.setCurrentScene(new Intro(world));
                break;
        }
    }
}
