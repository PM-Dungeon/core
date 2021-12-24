package swingdungeon;

import java.awt.*;
import java.util.List;

public class MyCanvas extends Canvas {
    private final MyRegistry registry;
    private final Point view;

    public MyCanvas(MyRegistry registry) {
        this.registry = registry;
        this.view = new Point();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        Rectangle viewRect = new Rectangle(view.x, view.y, w, h);
        g2d.clearRect(0, 0, w, h);
        List<DrawableElement> elements = registry.getElementsToDraw();
        elements.forEach(e -> e.drawElement(g2d, viewRect));
    }

    public void translateView(int x, int y) {
        view.x += x;
        view.y += y;
    }
}
