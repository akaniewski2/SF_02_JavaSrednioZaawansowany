package pl.kurs.advanced._1_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _3_ExecutorService_2 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Wykonywany watek (główny)" + Thread.currentThread().getName());

        ExecutorService executorService = Executors.newFixedThreadPool(2);


        Runnable worker1= () ->{
            try {
            System.out.println("Aktualny watek (worker1): " + Thread.currentThread().getName());
            System.out.println("Laduje butle z tlenem");
            TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable worker2= () ->{
            try {
                System.out.println("Aktualny watek (worker2): " + Thread.currentThread().getName());
                System.out.println("Laduje zapas pożywienia");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable worker3= () ->{
            try {
                System.out.println("Aktualny watek (worker3): " + Thread.currentThread().getName());
                System.out.println("Laduje paliwo");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable worker4= () ->{
            try {
                System.out.println("Aktualny watek (worker4): " + Thread.currentThread().getName());
                System.out.println("Laduje smalec");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };


           executorService.submit(worker1);
           executorService.submit(worker2);
           executorService.submit(worker3);
           executorService.submit(worker4);

           executorService.shutdown();






    }
}
