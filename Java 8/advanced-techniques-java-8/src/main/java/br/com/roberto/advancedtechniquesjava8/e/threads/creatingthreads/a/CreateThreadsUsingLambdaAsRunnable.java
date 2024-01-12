package br.com.roberto.advancedtechniquesjava8.e.threads.creatingthreads.a;

public class CreateThreadsUsingLambdaAsRunnable {

    public static void main(String[] args) {
       Thread t = new Thread(() -> System.out.println("run(): "+Thread.currentThread().getName()));
       //t.start();
       t.run(); //Pode-se usar as 2 opções tanto start quanto run
        System.out.println("main(): "+Thread.currentThread().getName());
    }


}
