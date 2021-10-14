package core.graphic.api;

import core.interfaces.IDrawable;
import core.tools.Point;

/**
 * must be implemented from the real graphic lib (lile libGDX)
 */
public interface GraphicLib {

    public void draw(IDrawable obj, Point p);

}
