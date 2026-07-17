package ecs.system;

import ecs.component.NameComponent;
import ecs.component.PettableComponent;
import ecs.component.requests.PetRequest;
import ecs.entity.Entity;

public class PettingZooSystem implements ECSSystem {
    @Override
    public void process(World world) {
        for (Entity entity : world.query(PettableComponent.class, PetRequest.class)){
            PettableComponent pettableComponent = entity.getComponent(PettableComponent.class);

            NameComponent nameComponent = entity.getComponent(NameComponent.class);
            String name = (nameComponent!=null) ? nameComponent.name : "Unkown";

            System.out.println(name + " " + pettableComponent.petAction);

            entity.remove(PetRequest.class);
        }
    }
}
