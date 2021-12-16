package graphic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Vector3;
import interfaces.IEntity;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import tools.Point;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DungeonCamera.class, OrthographicCamera.class})
public class DungeonCameraTest extends TestCase {
    DungeonCamera cam;
    IEntity entity;
    Vector3 pos;
    Frustum frustum;

    public void setUp() {
        entity = Mockito.mock(IEntity.class);
        pos = Mockito.mock(Vector3.class);
        frustum = Mockito.mock(Frustum.class);
        Mockito.when(entity.getPosition()).thenReturn(new Point(1, 1));
        // evil parent:
        PowerMockito.suppress(
                MemberMatcher.constructor(OrthographicCamera.class, float.class, float.class));
        PowerMockito.suppress(MemberMatcher.method(OrthographicCamera.class, "update"));
        cam = PowerMockito.spy(new DungeonCamera(entity, 10, 10));
        // set some thins, that are indispensable:
        Whitebox.setInternalState(cam, "position", pos);
        Whitebox.setInternalState(cam, "frustum", frustum);
    }

    public void testUpdate() {
        cam.update();
        Mockito.verify(cam).update();
        Mockito.verify(cam).getFollowedObject();
        Mockito.verify(entity).getPosition();
        Mockito.verify(cam.position).set(anyFloat(), anyFloat(), anyFloat());
        Mockito.verifyNoMoreInteractions(cam, pos, frustum, entity);
    }

    public void testFollow() {
        IEntity e2 = Mockito.mock(IEntity.class);
        cam.follow(e2);
        assertEquals(e2, cam.getFollowedObject());

        Mockito.verify(cam).follow(e2);
        Mockito.verify(cam).getFollowedObject();
        Mockito.verifyNoMoreInteractions(cam, pos, frustum, entity, e2);
    }

    public void testGetFollowedObject() {
        assertEquals(entity, cam.getFollowedObject());

        Mockito.verify(cam).getFollowedObject();
        Mockito.verifyNoMoreInteractions(cam, pos, frustum, entity);
    }

    public void testSetFocusPoint() {
        cam.setFocusPoint(new Point(2, 2));
        assertNull(cam.getFollowedObject());

        Mockito.verify(cam).setFocusPoint(any());
        Mockito.verify(cam).getFollowedObject();
        Mockito.verifyNoMoreInteractions(cam, pos, frustum, entity);
    }

    public void testIsPointInFrustum() {
        cam.isPointInFrustum(2, 2);

        Mockito.verify(cam).isPointInFrustum(2, 2);
        Mockito.verify(frustum).boundsInFrustum(any());
        Mockito.verifyNoMoreInteractions(cam, pos, frustum, entity);
    }

    public void testGetFrustum() {
        assertEquals(frustum, cam.getFrustum());

        Mockito.verify(cam).getFrustum();
        Mockito.verifyNoMoreInteractions(cam, pos, frustum, entity);
    }
}
