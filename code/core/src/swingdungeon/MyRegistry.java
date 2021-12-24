package swingdungeon;

import java.util.ArrayList;
import java.util.List;

public class MyRegistry {
    private final List<DrawableElement> floors = new ArrayList<>();
    private final List<MoveableElement> entities = new ArrayList<>();
    private final List<DrawableElement> allElements = new ArrayList<>();

    public void registerFloorElement(Floor floor) {
        floors.add(floor);
        allElements.add(floor);
    }

    public void registerEntity(MyEntity entity) {
        entities.add(entity);
        allElements.add(entity);
    }

    public List<DrawableElement> getElementsToDraw() {
        return allElements;
    }
}
