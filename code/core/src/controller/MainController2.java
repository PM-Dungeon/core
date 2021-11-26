package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.TextureFactory;
import interfaces.IAnimatable;
import interfaces.IEntity;
import tools.Point;

import java.util.ArrayList;

public class MainController2 extends MainController {
    public static class Hero implements IAnimatable, IEntity {
        private final SpriteBatch batch;
        private final GraphicController graphicController;
        private final Animation animation;
        private Point pos;

        public Hero(SpriteBatch batch, GraphicController graphicController) {
            this.batch = batch;
            this.graphicController = graphicController;
            ArrayList<Texture> list = new ArrayList<>();
            list.add(TextureFactory.getTexture("character/knight/knight_m_idle_anim_f0.png"));
            list.add(TextureFactory.getTexture("character/knight/knight_m_idle_anim_f1.png"));
            list.add(TextureFactory.getTexture("character/knight/knight_m_idle_anim_f2.png"));
            list.add(TextureFactory.getTexture("character/knight/knight_m_idle_anim_f3.png"));
            animation = new Animation(list, 10);
            pos = new Point(0, 0);
        }

        @Override
        public Animation getActiveAnimation() {
            return animation;
        }

        @Override
        public SpriteBatch getBatch() {
            return batch;
        }

        @Override
        public Point getPosition() {
            return pos;
        }

        @Override
        public GraphicController getGraphicController() {
            return graphicController;
        }

        @Override
        public void update() {
            // Hero moves 1/4 fields forwards:

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                pos = new Point(pos.x, pos.y + 0.25f);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                pos = new Point(pos.x, pos.y - 0.25f);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                pos = new Point(pos.x - 0.25f, pos.y);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                pos = new Point(pos.x + 0.25f, pos.y);
            }

            draw();
        }

        @Override
        public boolean removable() {
            return false;
        }
    }

    private Hero h;

    @Override
    protected void setup() {
        h = new Hero(this.batch, this.graphicController);
        entityController.addEntity(h);
    }

    @Override
    protected void beginFrame() {
        // The camera follows the hero if he moves outside the viewport:
        Point pos = h.getPosition();
        if (!camera.isPointInFrustum(pos.x, pos.y)) {
            camera.setFocusPoint(pos);
        }
    }

    @Override
    protected void endFrame() {}

    @Override
    public void onLevelLoad() {}
}
