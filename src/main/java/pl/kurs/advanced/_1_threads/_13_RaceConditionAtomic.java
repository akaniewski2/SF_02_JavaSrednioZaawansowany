package pl.kurs.advanced._1_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class _13_RaceConditionAtomic {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Counter2 counter = new Counter2();

        for (int i = 0; i <1000 ; i++) {
            executor.submit(()->counter.incraese());

        }

        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.MINUTES);
        System.out.println(counter.getCount());



    }



}


class Counter2{

    private AtomicInteger count = new AtomicInteger(0);
    private AtomicBoolean aBoolean = new AtomicBoolean(true);

    //synchronized
     public void incraese() {

         System.out.println("costam1");
         System.out.println("costam2");


         synchronized (this)
         {
             count.getAndIncrement();
         }

         System.out.println("costam3");
     }


    public int getCount() {
        return count.get();
    }
}