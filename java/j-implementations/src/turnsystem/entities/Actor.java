package turnsystem.entities;

import lombok.Getter;
import lombok.Setter;
import turnsystem.Game;
import turnsystem.data.BaseStats;
import turnsystem.data.StatPool;

@Getter
@Setter
public abstract class Actor {

    private Game game;
    private final String name;
    private final StatPool baseStats;
    //Add equipment, trinkets(useful for players, possible drops from monsters)

    public Actor(BaseStats actorData){
        this.name = actorData.name();
        this.baseStats = new StatPool(actorData.baseStats);
    }


}
