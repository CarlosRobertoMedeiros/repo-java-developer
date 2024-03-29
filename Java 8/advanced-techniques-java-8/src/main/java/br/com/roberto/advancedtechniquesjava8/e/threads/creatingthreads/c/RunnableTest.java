package br.com.roberto.advancedtechniquesjava8.e.threads.creatingthreads.c;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableTest {
    public static void main(String[] args) {
        // create an ExecutorService with a fixed thread pool consisting of one thread
        ExecutorService es = Executors.newSingleThreadExecutor();

        // execute the runnable task (asynchronous) - void run()
        es.execute(() -> System.out.println("Runnable Example"));

        // shutdown the executor service otherwise this application will never terminate;
        // existing tasks will be allowed to complete but no new tasks accepted
        es.shutdown();
    }
}
