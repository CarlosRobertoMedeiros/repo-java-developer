package br.com.roberto.advancedtechniquesjava8.e.threads.creatingthreads.a;

/*
 *   Creating Threads
 *      1 - extend Thread
 *      2 - implement Runnable
 *      3 - implement Callable (requires ExecutorService)
 * */
public class CreateThreadsExtendThread extends Thread {
    @Override
    public void run() {
        System.out.println("run(): "+getName());
    }

    public static void main(String[] args) {
        new CreateThreadsExtendThread().start();
        System.out.println("main(): "+Thread.currentThread().getName());
    }
}
