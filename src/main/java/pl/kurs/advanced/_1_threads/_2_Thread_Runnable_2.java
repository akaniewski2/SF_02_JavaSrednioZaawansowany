package pl.kurs.advanced._1_threads;

import java.util.concurrent.TimeUnit;

public class _2_Thread_Runnable_2 {

    public static void main(String[] args) throws InterruptedException {



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
                e.printStackTrace();
            }
        };

        Runnable blastOff = ()-> {
            System.out.println("Wykonywany watek (blastOff)  " + Thread.currentThread().getName());
            System.out.println("blastOff");

        };

        Thread countdownThread = new Thread(countdown, "CountDown");
        Thread blastOffThread = new Thread(blastOff, "BlastOff");



        countdownThread.start();
       // countdownThread.join(3000); // jesli dodamy millis to blastOffThread nie zaczeka
        countdownThread.join();

        System.out.println("Wykonywany watek (główny)" + Thread.currentThread().getName());
        blastOffThread.start();




    }
}
