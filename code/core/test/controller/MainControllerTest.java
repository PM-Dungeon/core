package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MainController.class)
class MainControllerTest {
    MainController controller;

    public MainControllerTest() {}

    @Before
    public void init() {
        controller = Mockito.spy(MainController.class);
    }

    @Test
    public void render() {}
}
