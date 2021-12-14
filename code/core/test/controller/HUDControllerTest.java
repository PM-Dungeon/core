package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import graphic.HUDCamera;
import interfaces.IHUDElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HUDControllerTest {

    private HUDController controller;
    @Mock SpriteBatch batch;
    @Mock HUDCamera camera;
    @Mock IHUDElement element1;
    @Mock IHUDElement element2;

    @BeforeEach
    public void init() {
        when(camera.getPosition()).thenReturn(new Vector3());

        controller = new HUDController(batch, camera);
    }

    @Test
    void update() {
        controller.add(element1);
        verify(camera).update();
        controller.add(element2);
        verify(camera).update();
        controller.update();
        verify(camera, times(2)).update();
        verify(batch).setProjectionMatrix(camera.combined);
        verify(element1).draw(batch);
        verify(element2).draw(batch);
        verifyNoMoreInteractions(camera);
        verifyNoMoreInteractions(batch);
        verifyNoMoreInteractions(element1);
        verifyNoMoreInteractions(element2);
    }
}
