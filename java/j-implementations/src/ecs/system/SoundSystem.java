package ecs.system;

import ecs.component.NameComponent;
import ecs.component.NoiseComponent;
import ecs.entity.Entity;

public class SoundSystem implements ECSSystem{
    @Override
    public void process(World world) {
        for (Entity entity : world.query(NoiseComponent.class)){
            NoiseComponent noise = entity.getComponent(NoiseComponent.class);

            NameComponent nameComponent = entity.getComponent(NameComponent.class);
            String name = (nameComponent!=null) ? nameComponent.name : "Unkown";

            System.out.println(name + " " + noise.sound + "'d");
        }
    }
}
