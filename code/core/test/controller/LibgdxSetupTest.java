package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.easymock.PowerMock.expectNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LibgdxSetup.class, MainController.class, SpriteBatch.class})
@PowerMockIgnore({"com.badlogic.*"})
class LibgdxSetupTest {
    @Test
    void constructor() throws Exception {
        MainController controller = Mockito.mock(MainController.class);
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);
        expectNew(SpriteBatch.class).andReturn(batch);

        LibgdxSetup lgs = new LibgdxSetup(controller);

        lgs.create();
    }

    @Test
    void create() {}

    @Test
    void dispose() {}
}
