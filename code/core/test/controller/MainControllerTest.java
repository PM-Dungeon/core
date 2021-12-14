package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {
    @Spy MainController controller;

    @BeforeEach
    void setUp() {}

    @Test
    void render() {

        assertThrows(UnsatisfiedLinkError.class, () -> controller.render(10));
    }
}
