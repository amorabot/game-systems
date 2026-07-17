package turnsystem.entities;

import lombok.Getter;
import lombok.Setter;
import turnsystem.StatSources;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Entity {

    private final String name;
    private final int id;
    private int baseSpeed;
    private Map<StatSources, List<Integer>> spdMods;

    public Entity(String name, int id, int speed){
        this.name = name;
        this.id = id;
        this.baseSpeed = speed;
    }


}
