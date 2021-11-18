package myimplementation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.GraphicController;
import graphic.Animation;
import graphic.DungeonCamera;
import graphic.TextureFactory;
import interfaces.IAnimatable;
import interfaces.IEntity;
import tools.Point;

import java.util.ArrayList;

public class Hero implements IAnimatable, IEntity {
    private final SpriteBatch batch;
    private final GraphicController gc;
    private final Animation idle;
    private final Point position;

    public Hero(SpriteBatch batch, GraphicController gc, DungeonCamera cam) {
        this.batch = batch;
        this.gc = gc;
        ArrayList<Texture> list = new ArrayList<>();
        list.add(TextureFactory.getTexture("character/knight/knight_m_idle_anim_f0.png"));
        list.add(TextureFactory.getTexture("character/knight/knight_m_idle_anim_f1.png"));
        list.add(TextureFactory.getTexture("character/knight/knight_m_idle_anim_f2.png"));
        list.add(TextureFactory.getTexture("character/knight/knight_m_idle_anim_f3.png"));
        this.idle = new Animation(list, 10);
        this.position = new Point(300, 300);
        cam.follow(this);
    }

    @Override
    public void update() {
        this.draw();
    }

    @Override
    public SpriteBatch getBatch() {
        return this.batch;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public GraphicController getGraphicController() {
        return this.gc;
    }

    @Override
    public Animation getActiveAnimation() {
        return this.idle;
    }

    @Override
    public boolean removable() {
        return false;
    }
}
