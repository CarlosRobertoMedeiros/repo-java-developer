package br.com.roberto.advancedtechniquesjava8.e.threads.creatingthreads.e;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class ScheduledExecutors{

    private static ScheduledExecutorService shedEs = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) {
        //schedule();
        scheduleWithFixedDelay();
    }

    private static void scheduleWithFixedDelay() {
        System.out.println("Start...");
        // 300 msecs is the time to wait from when the previous task finishes to starting the next task
        //  => previous task finishes - wait 300msecs - start next task
        final long INITIAL_DELAY = 2000;
        final long WAIT_PERIOD_AFTER_PREVIOUS_TASK_FINISHED=300;

        shedEs.scheduleWithFixedDelay(() -> System.out.println("Thread id: "+Thread.currentThread().getId()),
                INITIAL_DELAY, WAIT_PERIOD_AFTER_PREVIOUS_TASK_FINISHED, TimeUnit.MILLISECONDS);
        // schedES.shutdown(); // if I shut it down, nothing happens

    }

    private static void schedule(){
        System.out.println(LocalDateTime.now() + " Start...");

        Future<String> future = shedEs.schedule(() -> "A", 2, TimeUnit.SECONDS);
        try {
            System.out.println(future.get()); // block
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }  finally{
            shedEs.shutdown();
        }
        System.out.println(LocalDateTime.now() + " Always at the end!");

    }

}
