package pl.kurs.advanced._1_threads;

import java.util.concurrent.TimeUnit;

public class _1_Thread_Runnable_1 {

    public static void main(String[] args) {

        System.out.println("--------------------------------------------------------");
        System.out.println("Główny watek: " + Thread.currentThread().getName());
        Thread thread = new MyThreads("My-Thread-1");
        Thread thread2 = new MyThreads("My-Thread-2");

        // thread.run(); //metodar .run wywolalaby nowy watek w wątku głownym , a nie dodatkowym
        thread.start(); //metodar .start uruchomi nowy watek
        thread2.start();

        // -------------------------------------------------
        // Runnuble nie zwraca wyniku
        Runnable runnable = new MyRunnable();
        Thread myRunnable = new Thread(runnable, "My-Runnable-1");

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                String myThreadName = "MyRunnable : " + Thread.currentThread().getName();
                System.out.println(myThreadName);
            }
        };

        Thread myRunnable2 = new Thread(runnable2, "My-Runnable-2");

        Thread myRunnable3 = new Thread(() -> System.out.println("MyRunnable3 - > FunctionalInterface ->" + Thread.currentThread().getName()), "My-Runnable-3");
        Thread myRunnable4 = new Thread(() -> System.out.println("MyRunnable4 - > FunctionalInterface ->" + Thread.currentThread().getName()));

//-----------------------
        Runnable runnable5 = () -> {
            try {
                System.out.println("MyRunnable4 - > FunctionalInterface ->" + Thread.currentThread().getName());
                for (int i = 0; i <= 10; i++) {
                    System.out.println("i: " + i);
                    Thread.sleep(1000); //minimum 1000 ms , lub 1000 + , zalezy od zajetosci procesora
                    System.out.println("i: " + i + 1000);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread myRunnable5 = new Thread(runnable5, "My-Runnable-3");

//------------------------
        myRunnable.start();
        myRunnable2.start();
        myRunnable3.start();
        myRunnable4.start();
        myRunnable5.start();


        //preferowany runnable !!!

    }
}
