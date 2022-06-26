package controller;

import basiselements.ScreenElement;

public class MenuScreenElementController extends AbstractController<ScreenElement> {
    @Override
    public void process(ScreenElement e) {
        e.update();
    }
}
