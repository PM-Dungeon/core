package interfaces;

import com.badlogic.gdx.graphics.Texture;
import graphic.Animation;

public interface IAnimatable extends IDrawable {

    /** @return the current active animation (example idle or run) */
    Animation getActiveAnimation();

    @Override
    default Texture getTexture() {
        return this.getActiveAnimation().getNextAnimationTexture();
    }
}
