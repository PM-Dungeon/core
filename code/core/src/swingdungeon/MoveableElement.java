package swingdungeon;

public interface MoveableElement extends DrawableElement {
    void translatePosition(int x, int y);
}
