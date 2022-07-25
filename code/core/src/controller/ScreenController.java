package controller;

import basiselements.ScreenElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import java.util.Iterator;
import tools.Constants;

public class ScreenController extends AbstractController<ScreenElement> {
    protected Stage stage;

    /**
     * Creates a Screencontroller with a ScalingViewport which stretches the ScreenElements on resize
     *
     * @param batch the batch which should be used to draw with
     */
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
    public boolean add(ScreenElement screenElement, ControllerLayer layer) {
        boolean add = super.add(screenElement, layer);
        if (!add) {
            return false;
        }
        stage.addActor(screenElement.getActor());
        reorder();

        return add;
    }

    /** reorders The Elements for the Stage */
    private void reorder() {
        Iterator<ScreenElement> it = this.iterator();
        int index = 0;
        while (it.hasNext()) {
            it.next().getActor().setZIndex(index++);
        }
    }

    @Override
    public boolean remove(ScreenElement t) {
        t.remove();
        return super.remove(t);
    }
}
