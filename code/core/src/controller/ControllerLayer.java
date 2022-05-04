package controller;

public class ControllerLayer implements Comparable<ControllerLayer> {
    public static final ControllerLayer LOW = new ControllerLayer(DefaultLayer.LOW);
    public static final ControllerLayer DEFAULT = new ControllerLayer(DefaultLayer.DEFAULT);
    public static final ControllerLayer HIGH = new ControllerLayer(DefaultLayer.HIGH);

    public final int priority;

    public ControllerLayer() {
        this.priority = DefaultLayer.DEFAULT.value;
    }

    public ControllerLayer(DefaultLayer layer) {
        this.priority = layer.value;
    }

    public ControllerLayer(int priority) {
        assert (priority >= 1 && priority <= 40);
        this.priority = priority;
    }

    @Override
    public int compareTo(ControllerLayer o) {
        return Integer.compare(priority, o.priority);
    }
}
