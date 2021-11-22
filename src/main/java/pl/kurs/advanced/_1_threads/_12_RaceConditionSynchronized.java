package pl.kurs.advanced._1_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _12_RaceConditionSynchronized {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Counter counter = new Counter();

        for (int i = 0; i <1000 ; i++) {
            executor.submit(()->counter.incraese());

        }

        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.MINUTES);
        System.out.println(counter.getCount());



    }



}


class Counter{

    private int count = 0;

    //synchronized
     public void incraese() {

         System.out.println("costam1");
         System.out.println("costam2");


         synchronized (this)
         {
             count = count + 1;
         }

         System.out.println("costam3");
     }


    public int getCount() {
        return count;
    }
}