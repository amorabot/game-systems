package turnsystem.components.quirks;

public enum QuirkTags { //Tags should be contextual filters for stat compilations (Combat Quirks, Town quirks, Adventure Quirks).
    //If they're in town, filter for TOWN Quirks, If they're attacking, filter for ACTIVE quirks, if they're defending, PASSIVE quirks

    UNCONDITIONAL, // Signals the evaluator to ignore the check() result, on whatever context its being evaluated

    BASE,
    /*
        Signals a stat that is evaluated at the start of a dungeon. Composes the base stats that will be cached when first compiling
            -> -5 ACC: UNCONDITIONAL BASE
            -> +2 SPD in Caves: BASE, Condition: CaveEnvironmentCondition
    */
    ACTIVE, //Signals a Quirk that affects a attack against a foe
    PASSIVE, //Signals a Quirk that affects a
    TOWN,
}
