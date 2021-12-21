package controller;

import interfaces.IEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
        assumeTrue(controller.add(entity2));
        assertFalse(controller.contains(entity1));
        assertTrue(controller.contains(entity2));
    }

    @Test
    public void test_add() {
        assertTrue(controller.add(entity2));
        assertTrue(controller.add(entity1));
        assertTrue(controller.containsAll(List.of(entity1, entity2)));
    }

    @Test
    public void test_remove() {
        assumeTrue(controller.add(entity1));
        assumeTrue(controller.add(entity2));

        assertTrue(controller.remove(entity1));
        assertFalse(controller.contains(entity1));
        assertTrue(controller.contains(entity2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_contains_null() {
        assumeTrue(controller.add(entity1));

        controller.contains(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add_null_empty() {
        controller.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_remove_null() {
        assumeTrue(controller.add(entity1));

        controller.remove(null);
    }
}
