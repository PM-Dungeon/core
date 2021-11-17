package controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Frustum;
import graphic.DungeonCamera;
import interfaces.IDrawable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.Point;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GraphicControllerTest {
/*
    GraphicController gc;
    DungeonCamera camera;
    IDrawable drawable;
    Texture texture;
    Point p;
    SpriteBatch batch;
    Frustum frustum;

    @BeforeEach
    public void init() {
        System.out.println("WW");
        camera = mock(DungeonCamera.class);
        gc = new GraphicController(camera);
        drawable = mock(IDrawable.class);
        texture = mock(Texture.class);
        p = new Point(3, 3);
        batch = mock(SpriteBatch.class);
        frustum=mock(Frustum.class);
    }

    @Test
    public void draw_PointInFrustum_True(){
        when(camera.getFrustum()).thenReturn(frustum);
        when(frustum.pointInFrustum(anyFloat(),anyFloat(),0)).thenReturn(true);
        when(texture.getHeight()).thenReturn(1);
        when(texture.getWidth()).thenReturn(1);
        gc.draw(texture,p,batch);
    }

    */
}
