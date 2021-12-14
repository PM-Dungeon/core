package controller;

import interfaces.IEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntityControllerTest {

    private EntityController controller;

    @Mock IEntity entityMock;

    @Mock IEntity entityMock2;

    @BeforeEach
    public void init() {
        controller = new EntityController();
    }

    @Test
    void update() {
        Assertions.assertNotNull(controller);
        Assertions.assertNotNull(entityMock);

        // should be removed
        when(entityMock.removable()).thenReturn(true);
        controller.add(entityMock);
        controller.update();
        verifyNoMoreInteractions(entityMock);
        Assertions.assertFalse(controller.contains(entityMock));
        Assertions.assertTrue(controller.getList().isEmpty());
        Assertions.assertTrue(controller.getSet().isEmpty());

        // should not be removed
        when(entityMock.removable()).thenReturn(false);
        controller.add(entityMock);
        controller.update();
        verify(entityMock).update();
        verify(entityMock).draw();
        Assertions.assertTrue(controller.contains(entityMock));
        Assertions.assertFalse(controller.getList().isEmpty());
        Assertions.assertFalse(controller.getSet().isEmpty());
    }

    @Test
    void add() {
        Assertions.assertNotNull(controller);
        Assertions.assertNotNull(entityMock);

        Assertions.assertFalse(controller.contains(entityMock));
        controller.add(entityMock);
        Assertions.assertTrue(controller.contains(entityMock));
    }

    @Test
    void remove() {
        Assertions.assertNotNull(controller);
        Assertions.assertNotNull(entityMock);

        Assertions.assertFalse(controller.contains(entityMock));
        controller.add(entityMock);
        Assertions.assertTrue(controller.contains(entityMock));

        controller.remove(entityMock);
        Assertions.assertFalse(controller.contains(entityMock));
    }

    @Test
    void contains() {
        Assertions.assertNotNull(controller);
        Assertions.assertNotNull(entityMock);

        Assertions.assertFalse(controller.contains(entityMock));
        controller.add(entityMock);
        Assertions.assertTrue(controller.contains(entityMock));

        controller.remove(entityMock);
        Assertions.assertFalse(controller.contains(entityMock));
    }

    @Test
    void removeAll() {
        Assertions.assertNotNull(controller);
        Assertions.assertNotNull(entityMock);

        Assertions.assertTrue(controller.getList().isEmpty());
        controller.add(entityMock);
        controller.add(entityMock2);
        Assertions.assertFalse(controller.getList().isEmpty());

        controller.removeAll();
        Assertions.assertTrue(controller.getList().isEmpty());
    }

    @Test
    void getSet() {
        Assertions.assertNotNull(controller);
        Assertions.assertNotNull(entityMock);

        Assertions.assertTrue(controller.getSet().isEmpty());
        controller.add(entityMock);
        controller.add(entityMock2);
        Assertions.assertFalse(controller.getSet().isEmpty());
        Assertions.assertTrue(controller.getSet().containsAll(List.of(entityMock, entityMock2)));

        controller.removeAll();
        Assertions.assertTrue(controller.getSet().isEmpty());
        Assertions.assertFalse(controller.getSet().contains(entityMock));
        Assertions.assertFalse(controller.getSet().contains(entityMock2));
    }

    @Test
    void getList() {
        Assertions.assertNotNull(controller);
        Assertions.assertNotNull(entityMock);

        Assertions.assertTrue(controller.getList().isEmpty());
        controller.add(entityMock);
        controller.add(entityMock2);
        Assertions.assertFalse(controller.getList().isEmpty());
        Assertions.assertTrue(controller.getList().containsAll(List.of(entityMock, entityMock2)));
        Assertions.assertSame(controller.getList().get(0), entityMock);
        Assertions.assertSame(controller.getList().get(1), entityMock2);

        controller.removeAll();
        Assertions.assertTrue(controller.getList().isEmpty());
        Assertions.assertFalse(controller.getList().contains(entityMock));
        Assertions.assertFalse(controller.getList().contains(entityMock2));
    }
}
