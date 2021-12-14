package controller;

import interfaces.IEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntityControllerTest {

    private EntityController controller;
    @Mock IEntity entity1;
    @Mock IEntity entity2;

    @BeforeEach
    public void init() {
        controller = new EntityController();
    }

    @Test
    void update_with_remove() {
        // should be removed
        when(entity1.removable()).thenReturn(true);
        controller.add(entity1);
        controller.update();
        verifyNoMoreInteractions(entity1);
        assertFalse(controller.contains(entity1));
        assertTrue(controller.isEmpty());
    }

    @Test
    void update_without_remove() {
        // should not be removed
        when(entity1.removable()).thenReturn(false);
        controller.add(entity1);
        controller.update();
        verify(entity1).update();
        verify(entity1).draw();
        assertTrue(controller.contains(entity1));
        assertFalse(controller.isEmpty());
    }
}
