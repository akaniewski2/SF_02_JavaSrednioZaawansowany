package pl.kurs.advanced._1_threads;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

//Callable=Runnable , tylze ze Callable zwroca wynik
public class _7_CompletableFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(LocalDateTime.now());
//        System.out.println("Wykonywany watek (główny): " + Thread.currentThread().getName());
        System.out.println(LocalDateTime.now());

        ExecutorService executorService = Executors.newFixedThreadPool(5);

//        Callable<Integer> answerToEverything = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                TimeUnit.SECONDS.sleep(10);
//                return 10;
//            }
//        };

        CompletableFuture.runAsync(
                ()-> System.out.println("Watek: "+Thread.currentThread().getName()
                ),executorService //opcja jesli ale ja chcemy
                );


//        CompletableFuture.supplyAsync(new Supplier<Integer>() {
//            @Override
//            public Integer get()  {
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return 10;
//            }
//        });


        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        }
                ,executorService // opcja jesli ale ja chcemy
        );

        CompletableFuture.runAsync(
                ()-> System.out.println("Watek: "+Thread.currentThread().getName())
                //,executorService // opcja jesli ale ja chcemy
        );

        System.out.println(integerCompletableFuture.get()); // tez tu get jest blokujacy


        executorService.shutdown();
        System.out.println(LocalDateTime.now());
    }
}
