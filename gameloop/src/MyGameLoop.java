public class MyGameLoop {
    /**
     * quick and drity example for a gameloop
     */
    public static void main(String[]args) throws InterruptedException {
        final int TARGET_FPS = 30;
        final long OPTIMAL_TIME = 1000 / TARGET_FPS;
        while (true) {
            //update game
            System.out.println("I do nothing, hopefully I'll be done with it tomorrow.");

            long sleepTime = (OPTIMAL_TIME - System.currentTimeMillis()) % OPTIMAL_TIME + OPTIMAL_TIME;
            Thread.sleep(sleepTime);
        }
    }
}
