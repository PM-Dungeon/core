package controller;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.DungeonCamera;
import graphic.Painter;
import level.LevelAPI;
import level.generator.randomwalk.RandomWalkGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import tools.Constants;

@RunWith(PowerMockRunner.class)
@PrepareForTest({MainController.class, Gdx.class, Constants.class})
class MainControllerTest {
    MainController controller;
    SpriteBatch batch;
    int someArbitraryValueGreater0forDelta = 7;

    // Because of use of PowerMockRunner we need an empty constructor here
    public MainControllerTest() {}

    @Before
    public void setUp() throws Exception {
        controller = Mockito.spy(MainController.class);
        batch = Mockito.mock(SpriteBatch.class);

        Whitebox.setInternalState(Gdx.class, "gl", Mockito.mock(GL20.class));

        PowerMockito.whenNew(EntityDungeonElementController.class)
                .withNoArguments()
                .thenReturn(Mockito.mock(EntityDungeonElementController.class));
        PowerMockito.whenNew(Painter.class)
                .withAnyArguments()
                .thenReturn(Mockito.mock(Painter.class));
        PowerMockito.whenNew(SpriteBatch.class)
                .withAnyArguments()
                .thenReturn(Mockito.mock(SpriteBatch.class));
        PowerMockito.whenNew(HUDDungeonElementController.class)
                .withAnyArguments()
                .thenReturn(Mockito.mock(HUDDungeonElementController.class));
        PowerMockito.whenNew(LevelAPI.class)
                .withAnyArguments()
                .thenReturn(Mockito.mock(LevelAPI.class));
        PowerMockito.whenNew(DungeonCamera.class)
                .withAnyArguments()
                .thenReturn(Mockito.mock(DungeonCamera.class));
        PowerMockito.whenNew(RandomWalkGenerator.class)
                .withAnyArguments()
                .thenReturn(Mockito.mock(RandomWalkGenerator.class));

        PowerMockito.mockStatic(Constants.class, invocation -> "abc");
    }

    @Test
    public void test_render() {
        controller.setSpriteBatch(batch);

        Mockito.verify(controller).setSpriteBatch(batch);
        Mockito.verifyNoMoreInteractions(controller, batch);

        controller.render(someArbitraryValueGreater0forDelta);
        Mockito.verify(controller).render(someArbitraryValueGreater0forDelta);
        Mockito.verify(controller).setup();
        Mockito.verify(controller).frame();
        Mockito.verify(controller, Mockito.times(4)).runLoop();
        Mockito.verifyNoMoreInteractions(controller);
    }

    @Test
    public void test_render_paused() {
        controller.setSpriteBatch(batch);
        when(controller.runLoop()).thenReturn(false);
        Mockito.verify(controller).setSpriteBatch(batch);
        Mockito.verifyNoMoreInteractions(controller, batch);

        controller.render(someArbitraryValueGreater0forDelta);
        Mockito.verify(controller).render(someArbitraryValueGreater0forDelta);
        Mockito.verify(controller).setup();
        Mockito.verify(controller, never()).frame();
        when(controller.runLoop()).thenReturn(true);
        Mockito.verify(controller, Mockito.times(1)).runLoop();
        Mockito.verifyNoMoreInteractions(controller);
    }
}
