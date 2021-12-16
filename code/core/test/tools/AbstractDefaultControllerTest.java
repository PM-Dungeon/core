package tools;

import interfaces.IEntity;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AbstractDefaultController.class)
class AbstractDefaultControllerTest extends TestCase {
    AbstractDefaultController<Object> controller;

    IEntity entity1;
    IEntity entity2;

    public AbstractDefaultControllerTest() {}

    @SuppressWarnings("unchecked")
    public void setUp() {
        controller = Mockito.spy(AbstractDefaultController.class);
        entity1 = Mockito.mock(IEntity.class);
        entity2 = Mockito.mock(IEntity.class);
    }

    public void testAdd() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        assertFalse(controller.isEmpty());
        controller.add(entity2);
        assertFalse(controller.isEmpty());
    }

    public void testAdd_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.add(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    public void testRemove() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        assertFalse(controller.isEmpty());
        controller.remove(entity2);
        assertFalse(controller.isEmpty());
        controller.remove(entity1);
        assertTrue(controller.isEmpty());
    }

    public void testRemove_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.remove(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    public void testRemoveAll() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        controller.removeAll();
        assertTrue(controller.isEmpty());
    }

    public void testSize() {
        assertEquals(0, controller.size());
        controller.add(entity1);
        controller.add(entity2);
        assertEquals(2, controller.size());
        controller.remove(entity1);
        assertEquals(1, controller.size());
    }

    public void testIsEmpty() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        assertFalse(controller.isEmpty());
        controller.remove(entity1);
        assertTrue(controller.isEmpty());
    }

    public void testContainsAll() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertTrue(controller.containsAll(List.of(entity2, entity1)));
        controller.removeAll();
        assertFalse(controller.containsAll(List.of(entity2, entity1)));
    }

    public void testContainsAll_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.containsAll(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    public void testContains() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertTrue(controller.contains(entity1));
        assertTrue(controller.contains(entity2));
    }

    public void testContains_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.contains(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    public void testGetSet() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertEquals(entity1, new ArrayList<>(controller.getSet()).get(0));
        assertEquals(entity2, new ArrayList<>(controller.getSet()).get(1));
    }

    public void testGetList() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertEquals(entity1, controller.getList().get(0));
        assertEquals(entity2, controller.getList().get(1));
    }
}
