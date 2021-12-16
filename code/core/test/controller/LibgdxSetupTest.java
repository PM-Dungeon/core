package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LibgdxSetup.class)
class LibgdxSetupTest extends TestCase {
    MainController controller;
    SpriteBatch batch;
    LibgdxSetup setup;

    public LibgdxSetupTest() {}

    public void setUp() throws Exception {
        controller = Mockito.mock(MainController.class);
        batch = Mockito.mock(SpriteBatch.class);
        PowerMockito.whenNew(SpriteBatch.class).withNoArguments().thenReturn(batch);
        setup = Mockito.spy(new LibgdxSetup(controller));
        PowerMockito.doNothing().when(setup, "setScreen", controller);
        PowerMockito.doNothing().when(batch).dispose();
    }

    public void testConstructor_null() {
        LibgdxSetup l = Mockito.spy(new LibgdxSetup(null));
        try {
            l.create();
            throw new IllegalStateException();
        } catch (NullPointerException expected) {
        }
    }

    public void testCreate() {
        setup.create();
        Mockito.verify(setup).create();
        Mockito.verify(controller).setSpriteBatch(batch);
        Mockito.verify(setup).setScreen(controller);
        Mockito.verifyNoMoreInteractions(setup, batch, controller);
    }

    public void testDispose() {
        setup.create();
        Mockito.verify(setup).create();
        Mockito.verify(controller).setSpriteBatch(batch);
        Mockito.verify(setup).setScreen(controller);
        Mockito.verifyNoMoreInteractions(setup, batch, controller);

        setup.dispose();
        Mockito.verify(setup).dispose();
        Mockito.verify(batch).dispose();
        Mockito.verifyNoMoreInteractions(setup, batch, controller);
    }
}
