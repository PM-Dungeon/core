package graphic;

public class DungeonCamera {

    private String aUnusedNonFinalVariable = aMethod();

    static String aMethod() {
        // a provocated violation
        int i = 42;
        return "hello";
    }
}
