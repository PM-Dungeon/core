package graphic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import interfaces.IDrawable;
import tools.Point;

public class DungeonCamera extends OrthographicCamera {
    private IDrawable follows;
    private Point focusPoint;

    /**
     * Creates a new camera.
     *
     * @param follows the entity the camera should follow, null for default coordinates.
     * @param vw virtual Width
     * @param vh virtual Height
     */
    public DungeonCamera(IDrawable follows, float vw, float vh) {
        super(vw, vh);
        if (follows != null) this.follows = follows;
    }

    /** update camera position */
    public void update() {
        if (follows != null)
            this.position.set(
                    this.getFollowedObject().getPosition().x,
                    this.getFollowedObject().getPosition().y,
                    0);
        else {
            if (this.focusPoint == null) this.focusPoint = new Point(0, 0);
            this.position.set(this.focusPoint.x, this.focusPoint.y, 0);
        }
        super.update();
    }

    /**
     * set the entity to follow
     *
     * @param follows entity to follow
     */
    public void follow(IDrawable follows) {
        this.follows = follows;
    }

    /** @return the entity the camera currently follows */
    public IDrawable getFollowedObject() {
        return this.follows;
    }

    /**
     * Stops following and set the camera on a fix position
     *
     * @param focusPoint Point to set the camera on
     */
    public void setFocusPoint(Point focusPoint) {
        this.follows = null;
        this.focusPoint = focusPoint;
    }
}
