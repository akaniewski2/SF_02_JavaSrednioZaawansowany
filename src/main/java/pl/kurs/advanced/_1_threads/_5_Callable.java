package pl.kurs.advanced._1_threads;

import com.sun.source.tree.WhileLoopTree;

import java.util.concurrent.*;
//Callable=Runnable , tylze ze Callable zwroca wynik
public class _5_Callable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("Wykonywany watek (główny): " + Thread.currentThread().getName());

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Integer> answerToEverything = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(10);
                return 42;
            }
        };

        Callable<Integer> answerToEverything2 = () -> {
            TimeUnit.SECONDS.sleep(10);
            return 42;
        };
        Future<Integer> futureResult = executorService.submit(answerToEverything);


        while (!futureResult.isDone()){

            System.out.println("Brak wyniku - więc robie coś innego");
            TimeUnit.SECONDS.sleep(2);
        }
        Integer futureResultGet = futureResult.get();

//        Integer futureResultGet = null; //blokuje wykonanie głównego watku na 10 s
//        try {
//            futureResultGet = futureResult.get(4, TimeUnit.SECONDS);
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }

        System.out.println(futureResultGet);
        executorService.shutdown();

    }
}
