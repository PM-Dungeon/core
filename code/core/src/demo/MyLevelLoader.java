package demo;

import controller.OnLevelLoader;

public class MyLevelLoader extends OnLevelLoader {

    @Override
    public void onLevelLoad() {
        System.out.println("OnLevelLoad");
    }
}
