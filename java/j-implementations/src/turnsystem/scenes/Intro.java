package turnsystem.scenes;

import turnsystem.Game;

public class Intro extends Scene {
    public Intro(Game gameWorld) {
        super(gameWorld);
    }

    @Override
    public void process() {

    }

    @Override
    public void handleInput(String input) {
        switch (input){
            case "START","start":
                System.out.println("Initializing battle....");
                world.setCurrentScene(new Battle(world));
                break;
            case "test":
                break;
        }
    }
}
