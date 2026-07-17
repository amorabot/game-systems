package ecs.system;

import ecs.component.AgeComponent;
import ecs.component.requests.BirthdayRequest;
import ecs.entity.Entity;

public class AgeSystem implements ECSSystem {
    @Override
    public void process(World world) {
        for (Entity entity : world.query(AgeComponent.class, BirthdayRequest.class)){
            entity.getComponent(AgeComponent.class).age++;
            entity.remove(BirthdayRequest.class);//Serves as a filter that triggers functionality
            //Then its removed
            System.out.println("Entity with id " + entity.getId() + " got older! Curr. Age: " +
                    entity.getComponent(AgeComponent.class).age);
        }
    }
}
