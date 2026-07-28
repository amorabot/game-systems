package turnsystem.scenes;

import turnsystem.Game;

public abstract class Scene {

    protected final Game world;

    public Scene(Game gameWorld){
        this.world = gameWorld;
    }

    public abstract void process();
    public abstract void handleInput(String input);
}
