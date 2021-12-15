package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LibgdxSetup.class)
class LibgdxSetupTest {
    @Test
    void constructor() throws Exception {
        MainController controller = Mockito.mock(MainController.class);
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        PowerMockito.whenNew(SpriteBatch.class).withNoArguments().thenReturn(batch);
        LibgdxSetup lgs = Mockito.spy(new LibgdxSetup(controller));
        lgs.create();
        lgs.dispose();
    }

    @Test
    void create() {}

    @Test
    void dispose() {}
}
