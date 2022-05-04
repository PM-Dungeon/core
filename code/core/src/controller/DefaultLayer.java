package controller;

public enum DefaultLayer {
    BOTTOM(10),
    DEFAULT(20),
    TOP(30);
    public final int value;

    DefaultLayer(int value) {
        this.value = value;
    }
}
