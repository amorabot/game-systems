package turnsystem.data;

import java.util.ArrayList;
import java.util.List;

public enum BaseStats {
    //Heroes
    TEMPLAR(new Stat("LIFE",10),new Stat("STRESS",5), new Stat("SPEED",3)),
    BARD(new Stat("LIFE",5),new Stat("STRESS",8), new Stat("SPEED",5)),
    ARCHER(new Stat("LIFE",4),new Stat("STRESS",5), new Stat("SPEED",7)),

    //Foes
    SKELETON(new Stat("LIFE",7),new Stat("STRESS",20), new Stat("SPEED",6)),
    GIANT_TOAD(new Stat("LIFE",12),new Stat("STRESS",7), new Stat("SPEED",1)),
    TREANT(new Stat("LIFE",3),new Stat("STRESS",15), new Stat("SPEED",3));

    public final List<Stat> baseStats;
//    public final int maxHP;
//    public final int maxStress;
//    public final int baseSpeed;

    BaseStats(Stat life, Stat stress, Stat speed){
        this.baseStats = new ArrayList<>();
        baseStats.add(life);
        baseStats.add(stress);
        baseStats.add(speed);
//        this.maxHP=maxLife;
//        this.maxStress = maxStress;
//        this.baseSpeed = baseSpeed;
    }
}
