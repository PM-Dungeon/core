package graphic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Frustum;

/** An abstract Camera for usage in Printer class. */
public abstract class AbstractCamera extends OrthographicCamera {

    public AbstractCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
    }

    /** @return the camara frustum */
    public Frustum getFrustum() {
        return frustum;
    }

    /**
     * Checks if the point (x,y) is probably been seen on the screen. Need to be implemented in the
     * concrete Camera.
     *
     * @return always true
     */
    public boolean isPointInFrustum(float x, float y) {
        return true;
    }
}
