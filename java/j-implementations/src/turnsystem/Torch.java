package turnsystem;

import lombok.Getter;
import lombok.Setter;
import turnsystem.data.TorchStates;

@Getter
@Setter
public class Torch {
    private static final int turnDecay = 5;

    private int life = 100;
    private TorchStates state = TorchStates.HIGH;

    public void tick(){
        tick(turnDecay);
    }
    public void tick(int value){
        this.life = Math.clamp(life-value,0,100);
        if (life==0){
            state = TorchStates.OFF;
            return;
        }
        if (life<=25){
            state = TorchStates.LOW;
        } else if (life<=75) {
            state = TorchStates.MID;
        } else {
            state = TorchStates.HIGH;
        }
    }
}
