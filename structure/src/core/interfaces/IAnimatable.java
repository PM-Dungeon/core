package core.interfaces;

import core.graphic.DungeonAnimation;
import core.graphic.DungeonTexture;

public interface IAnimatable extends IDrawable{

    /** @return the current active animation (example idle or run) */
    DungeonAnimation getActiveAnimation();

    @Override
    default DungeonTexture getTexture() {
        return this.getActiveAnimation().getNextAnimationTexture();
    }

}
