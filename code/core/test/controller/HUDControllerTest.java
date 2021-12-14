package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import graphic.HUDCamera;
import interfaces.IHUDElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
    void add() {
        Assertions.assertNotNull(controller);

        Assertions.assertFalse(controller.contains(element1));
        controller.add(element1);
        Assertions.assertTrue(controller.contains(element1));
    }

    @Test
    void contains() {
        Assertions.assertNotNull(controller);

        Assertions.assertFalse(controller.contains(element1));
        controller.add(element1);
        Assertions.assertTrue(controller.contains(element1));

        controller.remove(element1);
        Assertions.assertFalse(controller.contains(element1));
    }

    @Test
    void remove() {
        Assertions.assertNotNull(controller);

        Assertions.assertFalse(controller.contains(element1));
        controller.add(element1);
        Assertions.assertTrue(controller.contains(element1));

        controller.remove(element1);
        Assertions.assertFalse(controller.contains(element1));
    }

    @Test
    void removeAll() {
        Assertions.assertNotNull(controller);

        Assertions.assertTrue(controller.getList().isEmpty());
        controller.add(element1);
        controller.add(element2);
        Assertions.assertFalse(controller.getList().isEmpty());

        controller.removeAll();
        Assertions.assertTrue(controller.getList().isEmpty());
    }

    @Test
    void getSet() {
        Assertions.assertNotNull(controller);

        Assertions.assertTrue(controller.getSet().isEmpty());
        controller.add(element1);
        controller.add(element2);
        Assertions.assertFalse(controller.getSet().isEmpty());
        Assertions.assertTrue(controller.getSet().containsAll(List.of(element1, element2)));

        controller.removeAll();
        Assertions.assertTrue(controller.getSet().isEmpty());
        Assertions.assertFalse(controller.getSet().contains(element1));
        Assertions.assertFalse(controller.getSet().contains(element2));
    }

    @Test
    void getList() {
        Assertions.assertNotNull(controller);

        Assertions.assertTrue(controller.getList().isEmpty());
        controller.add(element1);
        controller.add(element2);
        Assertions.assertFalse(controller.getList().isEmpty());
        Assertions.assertTrue(controller.getList().containsAll(List.of(element1, element2)));
        Assertions.assertSame(controller.getList().get(0), element1);
        Assertions.assertSame(controller.getList().get(1), element2);

        controller.removeAll();
        Assertions.assertTrue(controller.getList().isEmpty());
        Assertions.assertFalse(controller.getList().contains(element1));
        Assertions.assertFalse(controller.getList().contains(element2));
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
    }
}
