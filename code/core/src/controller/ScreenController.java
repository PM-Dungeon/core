package controller;

import basiselements.ScreenElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import tools.Constants;

public class ScreenController extends AbstractController<ScreenElement> {
    protected Stage stage;

    public ScreenController(SpriteBatch batch) {
        super(batch);
        stage =
                new Stage(
                        new ScalingViewport(
                                Scaling.stretch, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT),
                        this.batch);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update() {
        super.update();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void process(ScreenElement e) {}

    @Override
    public boolean add(ScreenElement screenElement) {
        boolean add = super.add(screenElement);
        if (!add) {
            return false;
        }
        stage.addActor(screenElement.getActor());
        return add;
    }

    @Override
    public boolean remove(ScreenElement t) {
        t.remove();
        return super.remove(t);
    }
}
