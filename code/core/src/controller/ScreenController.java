package controller;

import basiselements.ScreenElement;

public class ScreenController extends AbstractController<ScreenElement> {
    @Override
    public void process(ScreenElement e) {
        e.update();
    }
}
