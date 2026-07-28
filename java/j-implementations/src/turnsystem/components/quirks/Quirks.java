package turnsystem.components.quirks;

import turnsystem.components.Conditional;
import turnsystem.components.quirks.conditions.FirstTurnCondition;
import turnsystem.data.Stat;

import java.util.ArrayList;
import java.util.List;

public enum Quirks {
    QUICKDRAW(new ArrayList<>(),new FirstTurnCondition(),List.of(new Stat("SPEED",5))),
    SLOW_STARTER(new ArrayList<>(),new FirstTurnCondition(),List.of(new Stat("SPEED",-5)));

    private final List<QuirkTags> tags;
    private final Quirk quirkData;
    Quirks(List<QuirkTags> quirkTags, Conditional condition, List<Stat> quirkStats){
        this.tags = quirkTags;
        this.quirkData = new Quirk(condition,quirkStats);
    }
}


/*
Quirk conditions:
Life conditions ----
    tookDamage() - more than 99% HP
    halfLife() - less than 50%
    lowLife() - Less than 25%
Torch conditions --------
{level}LightLevel() - Checks for each light level state...
    highLightLevel()...
Enemy type conditions ---
{type}Enemy
    EldrichEnemy()...
Zone conditions ------
{zone}Zone
    CaveZone()
FirstTurn ------- (A flag on TurnSystem)
AllyDeath ------- (Flag on the Expedition object (Battle in this case, since the system is not fully fleshed out))

...

Quirks could also be paired and have a specific syntax for declaring that: that would negate repetitive work and simplify things
token-wise for the PICO8 implementation
*/

