package graphic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class HUDCamera extends AbstractCamera {

    public HUDCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth,viewportHeight);
    }

    public Vector3 getPosition() {
        return position;
    }
}
