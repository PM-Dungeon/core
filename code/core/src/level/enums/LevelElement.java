package level.enums;

/**
 * Each type of field in a level can be represented by an integer value.
 *
 * @author Andre Matutat
 */
public enum LevelElement {
    /** This field can be replaced with another one */
    PLACEHOLDER(-1),
    /** This field is a floor-field */
    FLOOR(0),
    /** This field is a wall-field */
    WALL(1),
    /** This field is a trap-field */
    TRAP(2),
    /** This field is the exit-field to the next level */
    EXIT(3);

    private int value;

    LevelElement(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
