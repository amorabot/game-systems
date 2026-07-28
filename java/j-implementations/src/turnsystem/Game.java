package turnsystem;

import lombok.Getter;
import lombok.Setter;
import turnsystem.entities.Hero;
import turnsystem.scenes.Intro;
import turnsystem.scenes.Scene;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Game {

    public static final Game gameInstance = new Game();
    private List<Hero> heroRoster = new ArrayList<>();
    private Scene currentScene;

    private Game(){
        currentScene = new Intro(this);
    }
    public void addHero(Hero hero){
        hero.setGame(this);
        heroRoster.add(hero);
    }
}
