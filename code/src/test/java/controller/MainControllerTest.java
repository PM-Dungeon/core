package controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainControllerTest {

    @Test
    public void goodTest(){
        assertEquals(3,3);
    }
    @Test
    public void badTest(){
        assertEquals(5,3);
    }
}
