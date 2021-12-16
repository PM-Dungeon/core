package graphic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import junit.framework.TestCase;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.reflect.Whitebox;

public class HUDCameraTest extends TestCase {
    HUDCamera cam;
    Vector3 vector3;

    public void setUp() {
        vector3 = Mockito.mock(Vector3.class);
        // evil parent:
        PowerMockito.suppress(MemberMatcher.defaultConstructorIn(OrthographicCamera.class));
        cam = PowerMockito.spy(new HUDCamera());
        // set some things, that are indispensable:
        Whitebox.setInternalState(cam, "position", vector3);
    }

    public void testGetPosition() {
        assertEquals(vector3, cam.getPosition());
        Mockito.verify(cam).getPosition();
        Mockito.verifyNoMoreInteractions(cam, vector3);
    }
}
