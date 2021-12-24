package swingdungeon;

import java.awt.*;

public interface DrawableElement {
    void drawElement(Graphics2D g2d, Rectangle viewRect);

    boolean isInViewRect(Rectangle viewRect);
}
