package controller;

import interfaces.IEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class EntityControllerTest {

    EntityController ec;

    @BeforeEach
    public void init() {
        ec = new EntityController();
    }

    @Test
    public void addElement_addNonAddedElements_True() {
        IEntity e1 = mock(IEntity.class);
        IEntity e2 = mock(IEntity.class);
        ec.addEntity(e1);
        ec.addEntity(e2);
        assertEquals(ec.getList().get(0), e1);
        assertEquals(ec.getList().get(1), e2);
    }

    @Test
    public void addElement_addAddedElements_False() {
        IEntity e1 = mock(IEntity.class);
        ec.addEntity(e1);
        ec.addEntity(e1);
        assertEquals(ec.getList().size(), 1);
    }

    @Test
    public void removeElement_removeExistingElements_True() {
        IEntity e1 = mock(IEntity.class);
        IEntity e2 = mock(IEntity.class);
        ec.addEntity(e1);
        ec.addEntity(e2);
        ec.removeEntity(e1);
        assertEquals(ec.getList().size(), 1);
    }

    @Test
    public void removeElement_removeNonExistingElement_NoException() {
        IEntity e1 = mock(IEntity.class);
        ec.removeEntity(e1);
    }

    @Test
    public void removeAll_True() {
        IEntity e1 = mock(IEntity.class);
        IEntity e2 = mock(IEntity.class);
        ec.addEntity(e1);
        ec.addEntity(e2);
        ec.removeAll();
        assertTrue(ec.getList().isEmpty());
    }

    @Test
    public void update_FilledListWithOneRemove_True() {
        IEntity e1 = mock(IEntity.class);
        IEntity e2 = mock(IEntity.class);
        ec.addEntity(e1);
        ec.addEntity(e2);
        ec.removeEntity(e2);
        ec.update();
        verify(e1).update();
        verify(e2, never()).update();
    }

    @Test
    public void update_FilledList_verify() {
        IEntity e1 = mock(IEntity.class);
        IEntity e2 = mock(IEntity.class);
        ec.addEntity(e1);
        ec.addEntity(e2);
        ec.update();
        verify(e1).update();
        verify(e2).update();
    }

    @Test
    public void update_EmptyList_NoException() {
        ec.update();
    }
}
