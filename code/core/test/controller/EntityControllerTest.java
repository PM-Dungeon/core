package controller;

import interfaces.IEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EntityController.class)
public class EntityControllerTest {
    private IEntity entity1;
    private IEntity entity2;
    private EntityController controller;

    @Before
    public void init() {
        entity1 = Mockito.mock(IEntity.class);
        entity2 = Mockito.mock(IEntity.class);
        controller = new EntityController();
    }

    @Test
    public void update_with_remove() {
        // should be removed
        when(entity1.removable()).thenReturn(true);
        controller.add(entity1);
        controller.update();
        verify(entity1).removable();
        Mockito.verifyNoMoreInteractions(entity1);
        assertFalse(controller.contains(entity1));
        assertTrue(controller.isEmpty());
    }

    @Test
    public void update_without_remove() {
        // should not be removed
        when(entity1.removable()).thenReturn(false);
        controller.add(entity1);
        controller.update();
        verify(entity1).removable();
        verify(entity1).update();
        verify(entity1).draw();
        Mockito.verifyNoMoreInteractions(entity1);
        assertTrue(controller.contains(entity1));
        assertFalse(controller.isEmpty());
    }
}
