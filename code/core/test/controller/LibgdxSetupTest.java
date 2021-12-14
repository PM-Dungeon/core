package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class LibgdxSetupTest {
    @Mock MainController controller;
    LibgdxSetup libgdxSetup;

    @BeforeEach
    void setUp() {
        libgdxSetup = Mockito.spy(new LibgdxSetup(controller));
    }

    @Test
    void constructor() {
        LibgdxSetup lgs = Mockito.spy(new LibgdxSetup(null));
        Mockito.doNothing().when(lgs).initSpriteBatch();
        assertThrows(NullPointerException.class, lgs::create);
    }

    @Test
    void create() {
        Mockito.doNothing().when(libgdxSetup).initSpriteBatch();
        assertThrows(NullPointerException.class, libgdxSetup::create);
    }

    @Test
    void dispose() {
        assertThrows(NullPointerException.class, libgdxSetup::dispose);
    }
}
