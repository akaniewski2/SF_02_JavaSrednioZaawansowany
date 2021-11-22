package pl.kurs.advanced._1_threads;

import java.util.stream.IntStream;

public class MyThreads extends Thread {

    public MyThreads(String name) {
        super(name);
    }

    @Override
    public void run() {
        String myThreadName = "MyThreads: " + Thread.currentThread().getName();
        IntStream.rangeClosed(1,20).forEach(i->{
            System.out.println(i+" | "+myThreadName);

                });

    }
}
