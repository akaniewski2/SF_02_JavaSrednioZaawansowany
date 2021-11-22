package pl.kurs.advanced._1_threads;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

//Callable=Runnable , tylze ze Callable zwroca wynik
public class _6_Invoke {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(LocalDateTime.now());
        System.out.println("Wykonywany watek (główny): " + Thread.currentThread().getName());
        System.out.println(LocalDateTime.now());

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Integer> answerToEverything = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(10);
                return 10;
            }
        };

        Callable<Integer> answerToEverything2 = () -> {
            TimeUnit.SECONDS.sleep(10);
            return 1;
        };


        Callable<Integer> answerToEverything3 = () -> {
            TimeUnit.SECONDS.sleep(13);
            return 2;
        };

        Callable<Integer> answerToEverything4 = () -> {
            TimeUnit.SECONDS.sleep(5);
            return 3;
        };

//        Future<Integer> futureResult = executorService.submit(answerToEverything);
        List<Callable<Integer> > callableList = Arrays.asList(answerToEverything2,answerToEverything3,answerToEverything4);

//       List<Future<Integer>> futures =  executorService.invokeAll(callableList); //zwraca wynik jak wykonaja sie wszystkie
//
//       for (Future<Integer> f: futures) {
//            System.out.println(f.get());
//        }
        // ta metoda zwraca integer

        Integer futures = executorService.invokeAny(callableList); //zwraca wynik callable które wykona sie najszybciej
        System.out.println(futures);

        System.out.println(LocalDateTime.now());
        executorService.shutdown();
        System.out.println(LocalDateTime.now());
    }
}
