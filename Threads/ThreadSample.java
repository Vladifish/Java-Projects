package Threads;

public class ThreadSample {
    public static void main(String[] args) {
        Thread t = new Thread(new Thread());
        t.start();
    }
}

class MyThread implements Runnable {
    public void run() {
        System.out.print("go ");
    }
}
