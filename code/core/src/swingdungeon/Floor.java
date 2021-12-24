package swingdungeon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Floor implements DrawableElement {
    private final BufferedImage img;
    private final AffineTransform affineTransform;

    public Floor(File path, int gridX, int gridY) throws IOException {
        img = ImageIO.read(path);
        int width = img.getWidth();
        int height = img.getHeight();
        double vw = (double) MyConstants.WINDOW_WIDTH / MyConstants.HORIZONTAL_GRIDS;
        double vh = (double) MyConstants.WINDOW_HEIGHT / MyConstants.VERTICAL_GRIDS;
        double sx = vw / width;
        double sy = vh / height;
        double tx = gridX * vw;
        double ty = gridY * vh;
        affineTransform = new AffineTransform();
        affineTransform.translate(tx, ty);
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
        return true;
    }
}
