package pl.kurs.advanced._1_threads;

import java.util.concurrent.*;
import java.util.function.BiFunction;

public class _10_CompletableFuture_LaczenieWywolanNieZalezne {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<Long> cfuture_1 = CompletableFuture.supplyAsync(() ->
        {
            try {
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1L;
        });


        CompletableFuture<Long> cfuture_2 = CompletableFuture.supplyAsync(() ->
        {
            try {
                TimeUnit.SECONDS.sleep(5);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2L;
        });


        CompletableFuture<Long> future = cfuture_1.thenCombine(cfuture_2, (result_1, result_2) -> result_1 * result_2 );

        Long f = future.get();
        System.out.println(f);
        executor.shutdown();

    }


    public static Long getUserId() {
        return  324L;
    }

    public static Double getDiscount(Long userId) {
        return 0.15;

    }
}
