package controller;

/**
 * An enumeration class for pre-define values for ControllerLayer.
 *
 * <p>BOTTOM: 100 (you can de- or increase this value if you need to)<br>
 * DEFAULT: 200 (you can de- or increase this value if you need to)<br>
 * TOP: 300 (you can de- or increase this value if you need to)
 *
 * <p>ControllerLayers with a higher value are drawn last (i.e. over other entities).
 */
public enum DefaultLayer {
    BOTTOM(100),
    DEFAULT(200),
    TOP(300);
    public final int value;

    DefaultLayer(int value) {
        this.value = value;
    }
}
