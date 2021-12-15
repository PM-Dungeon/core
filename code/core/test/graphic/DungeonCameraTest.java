package graphic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DungeonCamera.class)
public class DungeonCameraTest {
    @Test
    public void constructor_followNull_True() {}

    @Test
    public void constructor_followObject_True() {}

    @Test
    public void update_followNull_True() {}

    @Test
    public void update_followObject_True() {}

    @Test
    public void isPointInFrustum_inFrustum_True() {}

    @Test
    public void isPointInFrustum_notInFrustum_False() {}
}
