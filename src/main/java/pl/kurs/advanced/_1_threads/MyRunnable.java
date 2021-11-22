package pl.kurs.advanced._1_threads;

public class MyRunnable implements Runnable{


    @Override
    public void run() {
        String myThreadName = "MyRunnable : " + Thread.currentThread().getName();
        System.out.println(myThreadName);
    }

}
