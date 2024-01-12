package br.com.roberto.advancedtechniquesjava8.e.threads.creatingthreads.b;

public class UsingSleepAndJoin {

    static class CountDown implements Runnable{
        String[] timeStr = {"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
        @Override
        public void run() {
            for (int i = 9; i >=0 ; i--){
                System.out.println(timeStr[i]);
                try {
                    Thread.sleep(1 * 500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    public static void main(String[] args) {
        Thread timer = new Thread(new CountDown());
        System.out.println("Starting 10 seconds for count down ...");
        timer.start();
        try {
            timer.join(); //// Aguarda até que thread timer termine antes de continuar
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ;
        System.out.println("Boom!!");



    }
}
