package tools;

import interfaces.IEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AbstractDefaultController.class)
class AbstractDefaultControllerTest {
    AbstractDefaultController<Object> controller;

    IEntity entity1;
    IEntity entity2;

    public AbstractDefaultControllerTest() {}

    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        controller = Mockito.spy(AbstractDefaultController.class);
        entity1 = Mockito.mock(IEntity.class);
        entity2 = Mockito.mock(IEntity.class);
    }

    @Test
    public void add() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        assertFalse(controller.isEmpty());
        controller.add(entity2);
        assertFalse(controller.isEmpty());
    }

    @Test
    public void add_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.add(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    @Test
    public void remove() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        assertFalse(controller.isEmpty());
        controller.remove(entity2);
        assertFalse(controller.isEmpty());
        controller.remove(entity1);
        assertTrue(controller.isEmpty());
    }

    @Test
    public void remove_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.remove(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    @Test
    public void removeAll() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        controller.removeAll();
        assertTrue(controller.isEmpty());
    }

    @Test
    public void size() {
        assertEquals(0, controller.size());
        controller.add(entity1);
        controller.add(entity2);
        assertEquals(2, controller.size());
        controller.remove(entity1);
        assertEquals(1, controller.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        assertFalse(controller.isEmpty());
        controller.remove(entity1);
        assertTrue(controller.isEmpty());
    }

    @Test
    public void containsAll() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertTrue(controller.containsAll(List.of(entity2, entity1)));
        controller.removeAll();
        assertFalse(controller.containsAll(List.of(entity2, entity1)));
    }

    @Test
    public void containsAll_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.containsAll(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    @Test
    public void contains() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertTrue(controller.contains(entity1));
        assertTrue(controller.contains(entity2));
    }

    @Test
    public void contains_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.contains(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    @Test
    public void getSet() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertEquals(entity1, new ArrayList<>(controller.getSet()).get(0));
        assertEquals(entity2, new ArrayList<>(controller.getSet()).get(1));
    }

    @Test
    public void getList() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertEquals(entity1, controller.getList().get(0));
        assertEquals(entity2, controller.getList().get(1));
    }
}
