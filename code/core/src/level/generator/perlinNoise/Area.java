package level.generator.perlinNoise;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/** representation of areas */
public class Area {
    int width;
    int height;
    int size;
    boolean[][] area;

    /**
     * @param contains two dimensional boolean array containing true for every field in the area
     */
    public Area(final boolean[][] contains) {
        width = contains.length;
        height = contains[0].length;
        area = contains;

        int i = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (area[x][y]) i++;
            }
        }
        size = i;
    }

    /**
     * checks whether the given coordinates are in the area
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @return whether the given coordinates are in the area
     */
    public boolean contains(final int x, final int y) {
        return area[x][y];
    }

    /**
     * zoom the area
     *
     * @param zoom zoom factor
     */
    public void zoom(final double zoom) {
        width = (int) (width / zoom);
        height = (int) (height / zoom);
        size = (int) (getSize() / zoom);
        final boolean[][] newArea = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newArea[i][j] = area[(int) (i * zoom)][(int) (j * zoom)];
            }
        }
        area = newArea;
    }

    /**
     * returns the size of the area
     *
     * @return the size of the area
     */
    public int getSize() {
        return size;
    }

    /**
     * returns the width of the area
     *
     * @return the width of the area
     */
    public int getWidth() {
        return width;
    }

    /**
     * returns the height of the area
     *
     * @return the height of the area
     */
    public int getHeight() {
        return height;
    }

    /**
     * returns a buffered image representing the area
     *
     * <p>a white pixel represents a accessible area and a black one a wall
     *
     * @return a buffered image representing the area
     */
    public BufferedImage getImage() {
        final BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = res.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width - 1, height - 1);

        g.setColor(Color.WHITE);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (area[i][j]) {
                    g.fillRect(i, j, 1, 1);
                }
            }
        }
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);
        g.dispose();
        return res;
    }
}
