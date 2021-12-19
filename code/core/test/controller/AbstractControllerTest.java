package controller;

import interfaces.IEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AbstractController.class})
public class AbstractControllerTest {
    AbstractController<IEntity> controller;
    IEntity entity1;
    IEntity entity2;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        controller = Mockito.spy(AbstractController.class);
        entity1 = Mockito.mock(IEntity.class);
        entity2 = Mockito.mock(IEntity.class);
    }

    @Test
    public void test_contains() {
        assumeTrue(controller.isEmpty());

        assertTrue(controller.add(entity2));
        assertFalse(controller.contains(entity1));
        assertTrue(controller.contains(entity2));
    }

    @Test
    public void test_add() {
        assumeTrue(controller.isEmpty());

        assertTrue(controller.add(entity2));
        assertTrue(controller.add(entity1));
        assertTrue(controller.containsAll(List.of(entity1, entity2)));
    }

    @Test
    public void test_remove() {
        assumeTrue(controller.isEmpty());
        assumeTrue(controller.add(entity1));
        assumeTrue(controller.add(entity2));

        assertTrue(controller.remove(entity1));
        assertFalse(controller.contains(entity1));
        assertTrue(controller.contains(entity2));
    }

    @Test
    public void test_contains_null() {
        assumeTrue(controller.add(entity1));
        assumeFalse(controller.isEmpty());

        try {
            boolean r = controller.contains(null);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    @Test
    public void test_add_null() {
        assumeTrue(controller.isEmpty());

        try {
            controller.add(null);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    @Test
    public void test_remove_null() {
        assumeTrue(controller.add(entity1));
        assumeFalse(controller.isEmpty());

        try {
            controller.remove(null);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }
}
