package level.roomg;

import level.DesignLabel;
import level.LevelElement;

import java.util.Arrays;

/**
 * can be used to replace placeholder in a room layout
 *
 * @author Andre Matutat
 */
public class Replacement {

    private LevelElement[][] layout;
    private DesignLabel label;
    private boolean rotate;

    /**
     * @param layout of the replacer, use placeholder in fields that should not be replaced
     * @param rotate can the layout be rotated?
     * @param label DesignLabel of the replacer
     */
    public Replacement(LevelElement[][] layout, boolean rotate, DesignLabel label) {
        setLayout(layout);
        this.rotate = rotate;
        this.label = label;
    }

    public LevelElement[][] getLayout() {
        return Arrays.stream(layout).map(LevelElement[]::clone).toArray(LevelElement[][]::new);
    }

    public void setLayout(LevelElement[][] layout) {
        this.layout =
                Arrays.stream(layout).map(LevelElement[]::clone).toArray(LevelElement[][]::new);
    }

    public DesignLabel getDesign() {
        return label;
    }

    public boolean canRotate() {
        return rotate;
    }
}
