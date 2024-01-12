package br.com.roberto.advancedtechniquesjava8.e.threads.creatingthreads.c;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreatingThreadWithExecutorService {

    public static void main(String[] args) {
        //CachedThread Pool
        ExecutorService executorService = Executors.newCachedThreadPool();

        //FixedThread
        int cpuCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService2 = Executors.newFixedThreadPool(cpuCount);

        //SingleThread
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();

        //Continuar do Minuto 3
    }

}
