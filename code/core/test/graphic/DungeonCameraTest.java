package graphic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
