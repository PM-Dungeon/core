package controller;

public class ControllerLayer implements Comparable<ControllerLayer> {
    public static final ControllerLayer BOTTOM = new ControllerLayer(DefaultLayer.BOTTOM);
    public static final ControllerLayer DEFAULT = new ControllerLayer(DefaultLayer.DEFAULT);
    public static final ControllerLayer TOP = new ControllerLayer(DefaultLayer.TOP);

    public final int value;

    public ControllerLayer() {
        this.value = DefaultLayer.DEFAULT.value;
    }

    public ControllerLayer(DefaultLayer layer) {
        this.value = layer.value;
    }

    public ControllerLayer(int value) {
        assert (value >= 1 && value <= 40);
        this.value = value;
    }

    @Override
    public int compareTo(ControllerLayer o) {
        return Integer.compare(value, o.value);
    }
}
