package swingdungeon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyEntity implements MoveableElement {
    private final BufferedImage img;
    private final Point position;
    private final double sx, sy;
    private AffineTransform affineTransform;

    public MyEntity(File path, Point position, double sx, double sy) throws IOException {
        img = ImageIO.read(path);
        this.position = position;
        this.sx = sx;
        this.sy = sy;
        affineTransform = new AffineTransform();
        affineTransform.translate(position.x, position.y);
        affineTransform.scale(sx, sy);
    }

    @Override
    public void drawElement(Graphics2D g2d, Rectangle viewRect) {
        if (isInViewRect(viewRect)) {
            AffineTransform save = g2d.getTransform();
            g2d.translate(-viewRect.x, -viewRect.y);
            g2d.drawImage(img, affineTransform, null);
            g2d.setTransform(save);
        }
    }

    @Override
    public boolean isInViewRect(Rectangle viewRect) {
        return viewRect.contains(position);
    }

    @Override
    public void translatePosition(int x, int y) {
        position.x += x;
        position.y += y;
        affineTransform = new AffineTransform();
        affineTransform.translate(position.x, position.y);
        affineTransform.scale(sx, sy);
    }
}
