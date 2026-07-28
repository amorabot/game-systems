package turnsystem.data;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatPool {
    @Getter
    private final Map<String,Stat> stats;

    public StatPool(List<Stat> statList){
        stats = new HashMap<>();
        for (Stat s : statList){
            stats.put(s.getId(),s);
        }
    }
    public Map<String,Stat> merge(StatPool externalStats){
        Map<String, Stat> alteredStats = new HashMap<>();
        externalStats.getStats().forEach(
                (extStatKey, extStat) -> {
                    alteredStats.put(extStatKey, new Stat(extStatKey, stats.get(extStatKey).getMax()+extStat.getMax()));
                }
        );
        return alteredStats;
    }
}
