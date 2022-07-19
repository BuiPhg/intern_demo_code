import java.util.concurrent.*;
import java.util.*;
import java.io.*;
import java.util.stream.*;
import static java.util.concurrent.TimeUnit.*;

class MutualExclusion {

    private static int COUNTER = 0;

    // class increaseCounterFunc extends Runnable {
    //     void run() {
    //         for (int i = 0; i < 100; i++) {
    //             MutualExclusion.increaseCounter();
    //             System.out.println(COUNTER);
    //         }
    //     }
    // }

    public static void main(String... args) throws Exception {
        
        final Runnable increaseCounterFunc = () -> IntStream
                .range(0, 100)
                .forEach(MutualExclusion::increaseCounter);

        final var first = new Thread(increaseCounterFunc);
        final var second = new Thread(increaseCounterFunc);

        first.start();
        second.start();
        
        first.join();
        second.join();
        
        System.out.println(COUNTER);
    }
    
    private static void increaseCounter(int i) {
        ++COUNTER;
    }
}
