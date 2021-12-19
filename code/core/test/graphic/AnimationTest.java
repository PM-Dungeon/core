package graphic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Animation.class})
class AnimationTest {

    public AnimationTest() {}

    @Before
    public void setUp() {}

    @SuppressWarnings("all")
    @Test
    public void test_constructor() {
        try {
            new Animation(null, 10);
        } catch (NullPointerException excepted) {
        }
        try {
            new Animation(new ArrayList<>(), 10);
        } catch (IllegalArgumentException excepted) {
        }
        try {
            new Animation(List.of("hallo"), -10);
        } catch (IllegalArgumentException excepted) {
        }
    }

    @Test
    public void test_getNextAnimationTexture() {
        // Idea: An animation with 3 textures and a frame time of 10 should return 10 (or 11) times
        // the current texture.

        Animation animation = new Animation(List.of("1", "2", "3"), 10);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 11; j++) {
                assertEquals(String.valueOf(i % 3 + 1), animation.getNextAnimationTexture());
            }
        }

        // Why 11 times the same value, when the frame time is 10?
    }
}
