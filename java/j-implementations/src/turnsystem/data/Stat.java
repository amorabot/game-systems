package turnsystem.data;

import lombok.Getter;

@Getter
public class Stat {
    private final String id;
    private int current;
    private int max;

    public Stat(String id, int value){
        this.id = id;
        this.current = value;
        this.max = value;
    }
    public void setCurrent(int value){
        this.current = Math.clamp(value,0,max);
    }
    public void setMax(int maxValue){
        this.max = maxValue;
        this.current = Math.clamp(current,0,max);//Clamping the updated value
    }
}
