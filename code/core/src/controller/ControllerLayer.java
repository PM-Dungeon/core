package controller;

/**
 * A class to represent a layer on wich elements are drawn.
 *
 * <p>BOTTOM: 100 (you can de- or increase this value if you need to)<br>
 * DEFAULT: 200 (you can de- or increase this value if you need to)<br>
 * TOP: 300 (you can de- or increase this value if you need to)
 *
 * <p>ControllerLayers with a higher value are drawn last (i.e. over other entities).
 */
public class ControllerLayer implements Comparable<ControllerLayer> {
    public static final ControllerLayer BOTTOM = new ControllerLayer(DefaultLayer.BOTTOM);
    public static final ControllerLayer DEFAULT = new ControllerLayer(DefaultLayer.DEFAULT);
    public static final ControllerLayer TOP = new ControllerLayer(DefaultLayer.TOP);

    public final int value;

    /**
     * Constructs a new ControllerLayer from a DefaultLayer.
     *
     * @param layer the DefaultLayer.
     */
    public ControllerLayer(DefaultLayer layer) {
        this(layer.value);
    }

    /**
     * Constructs a user-defined ControllerLayer. The value must be between 1 and 1000.
     *
     * @param value between 1 and 1000.
     */
    public ControllerLayer(int value) {
        assert (value >= 1 && value <= 1000);
        this.value = value;
    }

    @Override
    public int compareTo(ControllerLayer o) {
        return Integer.compare(value, o.value);
    }
}
