package ru.otus;

public class Steps {

    public static void main(String[] args) throws InterruptedException {
        Steps steps = new Steps();
        steps.start();
    }

    public void start() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                doStep1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                doStep2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    public void doStep1() throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + " ");
            Thread.sleep(500);
        }
    }

    public void doStep2() throws InterruptedException {
        Thread.sleep(250);
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + " ");
            Thread.sleep(500);
        }
    }

}
