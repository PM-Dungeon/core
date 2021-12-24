package swingdungeon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Dungeon {
    private final JFrame frame = new JFrame("pmdungeon-swing");
    private final MyRegistry registry = new MyRegistry();
    private final MyCanvas canvas = new MyCanvas(registry);

    public Dungeon() {}

    public void repaint() {
        //        SwingUtilities.invokeLater(()-> {
        canvas.invalidate();
        canvas.repaint();
        //        });
    }

    public MyRegistry getRegistry() {
        return registry;
    }

    public void startMainLoop(final int sleepTime) {
        frame.add(canvas);
        canvas.setPreferredSize(new Dimension(MyConstants.WINDOW_WIDTH, MyConstants.WINDOW_HEIGHT));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocusInWindow();

        try {
            for (int i = 0; i < 10000; i++) {
                repaint();
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException ignore) {
        }
    }

    public void addKeyListener(KeyListener keyListener) {
        frame.addKeyListener(keyListener);
    }

    public void translateView(int x, int y) {
        canvas.translateView(x, y);
    }

    public static void main(String[] args) throws IOException {
        Dungeon d = new Dungeon();
        File floorFile = new File("core/assets/textures/dungeon/default/floor/floor_1.png");
        d.getRegistry().registerFloorElement(new Floor(floorFile, 1, 0));
        d.getRegistry().registerFloorElement(new Floor(floorFile, 1, 1));
        d.getRegistry().registerFloorElement(new Floor(floorFile, 1, 2));
        d.getRegistry().registerFloorElement(new Floor(floorFile, 1, 3));
        d.getRegistry().registerFloorElement(new Floor(floorFile, 1, 4));
        d.getRegistry().registerFloorElement(new Floor(floorFile, 2, 0));
        d.getRegistry().registerFloorElement(new Floor(floorFile, 2, 1));
        d.getRegistry().registerFloorElement(new Floor(floorFile, 2, 2));
        d.getRegistry().registerFloorElement(new Floor(floorFile, 2, 3));
        d.getRegistry().registerFloorElement(new Floor(floorFile, 2, 4));
        d.getRegistry().registerFloorElement(new Floor(floorFile, 3, 2));
        MyEntity hero =
                new MyEntity(
                        new File("core/assets/character/knight/knight_m_idle_anim_f0.png"),
                        new Point(100, 100),
                        4,
                        4);
        d.getRegistry().registerEntity(hero);
        d.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        int kc = e.getKeyCode();
                        if (kc == KeyEvent.VK_W) {
                            hero.translatePosition(0, -10);
                        }
                        if (kc == KeyEvent.VK_A) {
                            hero.translatePosition(-10, 0);
                        }
                        if (kc == KeyEvent.VK_S) {
                            hero.translatePosition(0, 10);
                        }
                        if (kc == KeyEvent.VK_D) {
                            hero.translatePosition(10, 0);
                        }
                        if (kc == KeyEvent.VK_UP) {
                            d.translateView(0, 10);
                        }
                        if (kc == KeyEvent.VK_LEFT) {
                            d.translateView(10, 0);
                        }
                        if (kc == KeyEvent.VK_DOWN) {
                            d.translateView(0, -10);
                        }
                        if (kc == KeyEvent.VK_RIGHT) {
                            d.translateView(-10, 0);
                        }
                    }
                });
        d.startMainLoop(MyConstants.LOW_SLEEP_TIME);
    }
}
