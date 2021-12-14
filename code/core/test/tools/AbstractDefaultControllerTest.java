package tools;

import interfaces.IEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AbstractDefaultControllerTest {
    AbstractDefaultController<IEntity> controller;
    @Mock IEntity entity1;
    @Mock IEntity entity2;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        controller = Mockito.spy(AbstractDefaultController.class);
    }

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
        assertThrows(NullPointerException.class, () -> controller.add(null));
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
        assertThrows(NullPointerException.class, () -> controller.remove(null));
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
        assertThrows(NullPointerException.class, () -> controller.containsAll(null));
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
        assertThrows(NullPointerException.class, () -> controller.contains(null));
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
