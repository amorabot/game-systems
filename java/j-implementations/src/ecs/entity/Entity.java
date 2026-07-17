package ecs.entity;

import ecs.component.Component;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Entity {
    private final int id;
    private final List<Component> components = new ArrayList<>();

    public Entity(int id){
        this.id = id;
    }

    public void add(Component component){
        this.components.add(component);
    }

    public <C extends Component> void remove(Class<C> type){
        components.removeIf(type::isInstance);
    }

    public <C extends Component> C getComponent(Class<C> type){
        return components.stream().filter(type::isInstance).map(type::cast).findFirst().orElse(null);
    }
}
