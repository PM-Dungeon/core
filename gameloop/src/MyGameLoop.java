public class MyGameLoop {
    /**
     * quick and drity example for a gameloop
     */
    public static void main(String[]args) throws InterruptedException {
        final int TARGET_FPS = 60;
        // time a loop should take to reach exactly TARGET_FPS  1000000000ns =1sec
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        while(true){
            //get current time in nanosec
            long now = System.nanoTime();

            //update game
            System.out.println("I do nothing, hopefully I'll be done with it tomorrow.");

           /*
                if the loop still has time, pause (convert to ms)
                Thread.sleep has "slight" fluctuations
                10ms wait can become therefore also gladly times 5ms or 20ms
            */
            Thread.sleep((now+ OPTIMAL_TIME - System.nanoTime()) / 1000000);
        }
    }
}
