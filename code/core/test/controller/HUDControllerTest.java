package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import graphic.HUDCamera;
import interfaces.IHUDElement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HUDController.class})
public class HUDControllerTest {
    private SpriteBatch batch;
    private HUDCamera camera;
    private IHUDElement element1;
    private IHUDElement element2;
    private HUDController controller;

    @Before
    public void setUp() {
        batch = Mockito.mock(SpriteBatch.class);
        camera = Mockito.mock(HUDCamera.class);
        element1 = Mockito.mock(IHUDElement.class);
        element2 = Mockito.mock(IHUDElement.class);

        when(camera.getPosition()).thenReturn(new Vector3());

        controller = new HUDController(batch, camera);
    }

    @Test
    public void test_update() {
        assumeTrue(controller.add(element1));
        verify(camera).update();
        assumeTrue(controller.add(element2));
        verify(camera).update();

        controller.update();
        verify(camera, times(2)).update();
        verify(camera).getPosition();
        verify(batch).setProjectionMatrix(camera.combined);
        verify(element1).draw(batch);
        verify(element2).draw(batch);
        verifyNoMoreInteractions(camera, batch, element1, element2);
    }

    @Test
    public void test_update_empty() {
        assumeTrue(controller.isEmpty());

        controller.update();
        verify(camera, times(2)).update();
        verify(camera).getPosition();
        verify(batch).setProjectionMatrix(camera.combined);
        verifyNoMoreInteractions(camera, batch, element1, element2);
    }
}
