package pl.kurs.advanced._1_threads;

import java.util.concurrent.*;

public class _9_CompletableFuture_LaczenieWywolanZalezne {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<Long> userIdFuture = CompletableFuture.supplyAsync(() ->
        {
            try {
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getUserId();
        });


        CompletableFuture<Void> future = userIdFuture.thenCompose(userId -> CompletableFuture.supplyAsync(
                () -> getDiscount(userId)
        )).thenAccept(discount -> System.out.println(discount));

        future.get();
        executor.shutdown();

    }


    public static Long getUserId() {
        return  324L;
    }

    public static Double getDiscount(Long userId) {
        return 0.15;

    }
}
