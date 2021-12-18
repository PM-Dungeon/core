package controller;

import interfaces.IEntity;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AbstractController.class})
public class AbstractControllerTest extends TestCase {
    AbstractController<IEntity> controller;
    IEntity entity1;
    IEntity entity2;

    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        controller = Mockito.spy(AbstractController.class);
        entity1 = Mockito.mock(IEntity.class);
        entity2 = Mockito.mock(IEntity.class);
    }

    public void testContains() {
        assumeTrue(controller.isEmpty());

        assertTrue(controller.add(entity2));
        assertFalse(controller.contains(entity1));
        assertTrue(controller.contains(entity2));
    }

    public void testAdd() {
        assumeTrue(controller.isEmpty());

        assertTrue(controller.add(entity2));
        assertTrue(controller.add(entity1));
        assertTrue(controller.containsAll(List.of(entity1, entity2)));
    }

    public void testRemove() {
        assumeTrue(controller.isEmpty());
        assumeTrue(controller.add(entity1));
        assumeTrue(controller.add(entity2));

        assertTrue(controller.remove(entity1));
        assertFalse(controller.contains(entity1));
        assertTrue(controller.contains(entity2));
    }

    public void testContains_null() {
        assumeTrue(controller.add(entity1));
        assumeFalse(controller.isEmpty());

        try {
            boolean r = controller.contains(null);
            throw new IllegalStateException();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testAdd_null() {
        assumeTrue(controller.isEmpty());

        try {
            controller.add(null);
            throw new IllegalStateException();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testRemove_null() {
        assumeTrue(controller.add(entity1));
        assumeFalse(controller.isEmpty());

        try {
            controller.remove(null);
            throw new IllegalStateException();
        } catch (IllegalArgumentException expected) {
        }
    }
}
