package demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.MainController;
import graphic.Animation;
import graphic.Painter;
import graphic.TextureHandler;
import graphic.TextureType;
import interfaces.IAnimatable;
import tools.Point;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MyController extends MainController {
    Point p = new Point(3, 3);

    @Override
    protected void setup() {
        camera.setFocusPoint(p);
        levelAPI.loadLevel();

        Hero hero = new Hero(this.batch, this.painter);
        this.entityController.add(hero);
    }

    @Override
    protected void beginFrame() {
        Point newPosition = new Point(p);
        float movementSpeed = 1f;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) newPosition.y += movementSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) newPosition.y -= movementSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) newPosition.x += movementSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) newPosition.x -= movementSpeed;
        p = newPosition;
    }

    @Override
    protected void endFrame() {
        camera.setFocusPoint(p);
    }

    public static class Hero implements IAnimatable {
        private final SpriteBatch batch;
        private final Painter painter;
        private Animation animation1 = null;

        public Hero(SpriteBatch batch, Painter painter) {
            this.batch = batch;
            this.painter = painter;
        }

        private String searchPrefix() {
            String[] prefixes = TextureHandler.HANDLER.getAvailablePrefixes();
            for (String s : prefixes) {
                if (s.contains("knight")) {
                    return s;
                }
            }
            return null;
        }

        @Override
        public Animation getActiveAnimation() {
            if (animation1 == null) {
                String prefix = searchPrefix();
                if (prefix != null) {
                    animation1 =
                            new Animation(
                                    Arrays.stream(
                                                    TextureHandler.HANDLER.getTextureTypesForPrefix(
                                                            prefix))
                                            .map(TextureType::getPath)
                                            .collect(Collectors.toList()),
                                    10);
                }
            }
            return animation1;
        }

        @Override
        public void update() {
            this.draw();
        }

        @Override
        public boolean removable() {
            return false;
        }

        @Override
        public SpriteBatch getBatch() {
            return batch;
        }

        @Override
        public Point getPosition() {
            return new Point(5, 5);
        }

        @Override
        public Painter getGraphicController() {
            return painter;
        }
    }
}
