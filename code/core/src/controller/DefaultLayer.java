package controller;

public enum DefaultLayer {
    LOW(10),
    DEFAULT(20),
    HIGH(30);
    public final int value;

    DefaultLayer(int value) {
        this.value = value;
    }
}
