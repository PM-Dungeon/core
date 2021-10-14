package core.graphic;


import core.graphic.api.GraphicLib;
import core.interfaces.IDrawable;
import core.tools.Point;

public class GraphicController {
    private GraphicLib gl;

    public GraphicController(GraphicLib gl){
        this.gl=gl;
    }
    public void draw (IDrawable obj, Point p){
        gl.draw(obj,p);
    }
}
