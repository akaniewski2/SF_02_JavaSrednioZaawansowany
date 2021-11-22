package pl.kurs.advanced._1_threads;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.function.Function;

//Callable=Runnable , tylze ze Callable zwroca wynik
public class _8_CompletableFutureChains_Wzor {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.ms");

        System.out.println(LocalDateTime.now().format(formatter));
        System.out.println("Wykonywany watek (główny): " + Thread.currentThread().getName());
//        System.out.println(LocalDateTime.now().format(formatter));

        ExecutorService executorService = Executors.newFixedThreadPool(5);

//        CompletableFuture.runAsync(
//                ()-> System.out.println("Watek: "+Thread.currentThread().getName()
//                ),executorService //opcja jesli ale ja chcemy
//                );

        // Watek poboczny

        //CompletableFuture ma 2 metody
        // .thenApply(r->r+1) - pozwala na modyfikacje wyniku
        // .thenAccept (System.out.println("wynik: " + r) ) - pozwala na pobranie tego wyniku

        /*CompletableFuture<Integer> result = */ //thanaccept nie zwraca juz zadnego wyniku,tylko then applay zwracaja
        CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 7;
                }
                , executorService // opcja jesli ale ja chcemy
        ).thenApply(r -> {
                    System.out.println("*2 " + Thread.currentThread().getName());
                    return r * 2;
                }
        ).thenApply(r -> {
                    System.out.println("+1 " + Thread.currentThread().getName());
                    return r + 1;
                }
        ).thenAccept(r -> {
                    System.out.println("wynikThread: " + Thread.currentThread().getName());
                    System.out.println("wynik: " + r);
                });

        CompletableFuture.runAsync(
                () -> System.out.println("Wykonywany watek (główny): " + Thread.currentThread().getName())
                //,executorService // opcja jesli ale ja chcemy
        );


        //  System.out.println(result.get()); // tu blokujemy glowny watek i nawet .shutdownNow musi poczekac na jego zakonczenie !


        executorService.shutdown();
        System.out.println(LocalDateTime.now().format(formatter));
    }
}
