package pl.kurs.advanced._1_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _3_ExecutorService_1 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Wykonywany watek (główny)" + Thread.currentThread().getName());

        ExecutorService executorService = Executors.newSingleThreadExecutor();


        Runnable countdown = () -> {
            try {
                System.out.println("Wykonywany watek (countdown)  " + Thread.currentThread().getName());
                for (int i = 0; i <= 10; i++) {
                    System.out.println("i: " + i);
                   // Thread.sleep(1000); //minimum 1000 ms , lub 1000 + , zalezy od zajetosci procesora
                   // System.out.println("i: " + i + 1000);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                System.out.println("# INTERRUPTED EXCEPTION");
                e.printStackTrace();
            }
        };


        Runnable blastOff = ()-> {
            System.out.println("Wykonywany watek (blastOff)  " + Thread.currentThread().getName());
            System.out.println("blastOff");

        };

      executorService.submit(countdown);
      executorService.submit(blastOff);
      executorService.shutdown();
//      executorService.shutdownNow();





    }
}
