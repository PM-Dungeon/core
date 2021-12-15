package controller;

import interfaces.IEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EntityController.class)
public class EntityControllerTest {
    @Mock IEntity entity1;
    @Mock IEntity entity2;
    private EntityController controller;

    @Before
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
