package turnsystem.components.quirks;

import lombok.Getter;
import turnsystem.components.Conditional;
import turnsystem.data.Stat;

import java.util.List;

@Getter
public class Quirk { /*
    All quirk conditions should be bound to a state that can be checked at an arbitrary moment, based on the world state.
    They shouldnt be defined having a event in mind.
        -> Torch transitions and possession are 'events' in the sense that they trigger a generic recompilation. But they dont signal specific actors to
        do a trigger a specific routine. the lightLevelChange() will trigger a Expedition recompilation for the Hero party and then the new torch state
        can be reflected on the newly updated StatPool

    */
    private final Conditional quirkCondition;
    private final List<Stat> affectedStats;


    public Quirk(Conditional condition, List<Stat> quirkStats){
        this.quirkCondition = condition;
        this.affectedStats = quirkStats;
    }
}
