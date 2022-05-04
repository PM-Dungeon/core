package controller;

public enum ControllerLayer {
    LOW(10),
    DEFAULT(20),
    HIGH(30);
    public final int priority;

    ControllerLayer(int priority) {
        assert (priority >= 1 && priority <= 40);
        this.priority = priority;
    }
}
