package ecs;

import ecs.component.AgeComponent;
import ecs.component.NameComponent;
import ecs.component.NoiseComponent;
import ecs.component.PettableComponent;
import ecs.component.requests.BirthdayRequest;
import ecs.component.requests.PetRequest;
import ecs.system.AgeSystem;
import ecs.system.PettingZooSystem;
import ecs.system.SoundSystem;
import ecs.system.World;

public class Main {
    public static void main(String[] args){
        //Main 'game' loop

        World world = new World();
        world.addSystem(new AgeSystem());
        world.addSystem(new SoundSystem());
        world.addSystem(new PettingZooSystem());

        int bengieId = world.createEntity(
                new NameComponent("Bengie"),
                new AgeComponent(11),
                new NoiseComponent("Woof"),
                new PettableComponent("had their belly rubbed!")
        );
        int rataoId = world.createEntity(
                new NameComponent("Ratão"),
                new AgeComponent(999),
                new NoiseComponent("I I I"),
                new PettableComponent("danced after being pet")
        );

        world.process();

        world.edit(bengieId).add(new PetRequest());
        world.edit(bengieId).add(new BirthdayRequest());

        world.process();
    }
}
