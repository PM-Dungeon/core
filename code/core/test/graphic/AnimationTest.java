package graphic;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Animation.class)
class AnimationTest extends TestCase {

    public AnimationTest() {}

    public void setUp() {}

    @SuppressWarnings("all")
    public void testConstructor() {
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

    public void testGetNextAnimationTexture() {
        Animation animation = new Animation(List.of("1", "2", "3"), 10);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 11; j++) {
                assertEquals(String.valueOf(i % 3 + 1), animation.getNextAnimationTexture());
            }
        }
        // Why 11 times the same value, when the frame time is 10?
    }
}
