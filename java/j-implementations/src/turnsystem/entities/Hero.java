package turnsystem.entities;

import lombok.Getter;
import turnsystem.components.quirks.Quirks;
import turnsystem.data.BaseStats;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Hero extends Actor{
    private List<Quirks> quirks = new ArrayList<>();

    public Hero(BaseStats actorData) {
        super(actorData);
    }

    public void addQuirk(Quirks quirk){
        quirks.add(quirk);
    }
}
