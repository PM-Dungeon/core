package controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainControllerTest {

    @Test
    public void testConstructor(){
        MainController mc = new MainController();
        assertNotNull(mc);
    }
}
