package graphic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AnimationTest {
    @BeforeEach
    void setUp() {}

    @Test
    @SuppressWarnings("all")
    void constructor() {
        assertThrows(NullPointerException.class, () -> new Animation(null, 10));
        assertThrows(IllegalArgumentException.class, () -> new Animation(new ArrayList<>(), 10));
        assertThrows(IllegalArgumentException.class, () -> new Animation(List.of("hallo"), -10));
    }

    @Test
    void getNextAnimationTexture() {
        Animation animation = new Animation(List.of("1", "2", "3"), 10);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 11; j++) {
                assertEquals(String.valueOf(i % 3 + 1), animation.getNextAnimationTexture());
            }
        }
        // why 11-times the same value, when the frame time is 10?
    }
}
