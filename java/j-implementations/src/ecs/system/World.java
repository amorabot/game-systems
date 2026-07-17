package ecs.system;

import ecs.component.Component;
import ecs.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Entity> entities = new ArrayList<>();
    private final List<ECSSystem> systems = new ArrayList<>();
    private int nextId = 0;

    public void addSystem(ECSSystem system){
        this.systems.add(system);
    }

    public int createEntity(Component... components){
        int currentId = nextId;
        Entity e = new Entity(currentId);
        for (Component c : components){e.add(c);}
        entities.add(e);
        nextId++;
        return currentId;
    }
    public Entity edit(int id){
        return entities.get(id);
        /*
        Limited implementation, wouldnt work well with entity removal since the IDs would mismatch
        For that, a Map<Integer,List<Component>> would be better, since it would generally be O(1) access
        and all the entity's data would be there aswell, but less structured in general. Another benefit would be
        not dealing with garbage collection while interacting w/ classes
        */
    }
    public List<Entity> query(Class<? extends Component>... types){
        List<Entity> result = new ArrayList<>();
        for (Entity e: entities){
            boolean match = true;
            for (Class<? extends Component> type : types){
                if (e.getComponent(type) == null) {
                    match = false;
                    break;
                }
            }
            if (match){
                result.add(e);
            }
        }
        return result;
    }

    public void process(){
        for (ECSSystem s : systems){
            s.process(this);
        }
    }
}
