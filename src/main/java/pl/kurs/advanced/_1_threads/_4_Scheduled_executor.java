package pl.kurs.advanced._1_threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _4_Scheduled_executor {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Wykonywany watek (główny): " + Thread.currentThread().getName());

        //mozna opoznic wykonanie watku lub wykonywac jakies zadanie cyklicznie
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);



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


          // executorService.schedule(worker1,5,TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(worker3,0,6,TimeUnit.SECONDS);


        // uwaga wyłaczony , bo tu shutdown zadziala zamim skonczy sie zadanie (??)
        //   executorService.shutdown();






    }
}
