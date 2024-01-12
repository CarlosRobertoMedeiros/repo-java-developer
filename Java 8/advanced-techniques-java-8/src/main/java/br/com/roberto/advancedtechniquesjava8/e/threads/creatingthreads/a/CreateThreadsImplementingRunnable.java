package br.com.roberto.advancedtechniquesjava8.e.threads.creatingthreads.a;

public class CreateThreadsImplementingRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("run(): "+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new Thread(new CreateThreadsImplementingRunnable()).start();
        System.out.println("main(): "+Thread.currentThread().getName());
    }


}
