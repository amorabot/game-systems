package turnsystem.components.actions;

import lombok.Getter;

public class ActionResult {
    @Getter
    private final boolean succeeded;
    private final Action alternativeAction;

    public ActionResult(boolean successful){
        this.succeeded = successful;
        this.alternativeAction = null;
    }
    public ActionResult(Action altAction){
        this.succeeded = false;
        this.alternativeAction = altAction;
    }
    public Action getAltAction(){
        if (succeeded){return null;}
        return alternativeAction;
    }
}
