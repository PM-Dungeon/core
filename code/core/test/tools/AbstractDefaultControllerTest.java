package tools;

import interfaces.IEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AbstractDefaultController.class)
class AbstractDefaultControllerTest {
    @Spy AbstractDefaultController<IEntity> controller;
    @Mock IEntity entity1;
    @Mock IEntity entity2;

    @Before
    public void init() {}

    @Test
    void add() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        assertFalse(controller.isEmpty());
        controller.add(entity2);
        assertFalse(controller.isEmpty());
    }

    @Test
    void add_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.add(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    @Test
    void remove() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        assertFalse(controller.isEmpty());
        controller.remove(entity2);
        assertFalse(controller.isEmpty());
        controller.remove(entity1);
        assertTrue(controller.isEmpty());
    }

    @Test
    void remove_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.remove(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    @Test
    void removeAll() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        controller.removeAll();
        assertTrue(controller.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, controller.size());
        controller.add(entity1);
        controller.add(entity2);
        assertEquals(2, controller.size());
        controller.remove(entity1);
        assertEquals(1, controller.size());
    }

    @Test
    void isEmpty() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        assertFalse(controller.isEmpty());
        controller.remove(entity1);
        assertTrue(controller.isEmpty());
    }

    @Test
    void containsAll() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertTrue(controller.containsAll(List.of(entity2, entity1)));
        controller.removeAll();
        assertFalse(controller.containsAll(List.of(entity2, entity1)));
    }

    @Test
    void containsAll_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.containsAll(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    @Test
    void contains() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertTrue(controller.contains(entity1));
        assertTrue(controller.contains(entity2));
    }

    @Test
    void contains_null() {
        assertTrue(controller.isEmpty());
        try {
            controller.contains(null);
        } catch (NullPointerException expected) {
        }
        assertTrue(controller.isEmpty());
    }

    @Test
    void getSet() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertEquals(entity1, new ArrayList<>(controller.getSet()).get(0));
        assertEquals(entity2, new ArrayList<>(controller.getSet()).get(1));
    }

    @Test
    void getList() {
        assertTrue(controller.isEmpty());
        controller.add(entity1);
        controller.add(entity2);
        assertFalse(controller.isEmpty());
        assertEquals(entity1, controller.getList().get(0));
        assertEquals(entity2, controller.getList().get(1));
    }
}
