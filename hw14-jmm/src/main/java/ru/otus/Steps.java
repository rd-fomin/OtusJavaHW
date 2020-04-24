package ru.otus;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Steps {
    AtomicInteger step1 = new AtomicInteger(0);
    AtomicBoolean flag = new AtomicBoolean(false);

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
        int i = 1;
        while (true) {
            for (; i <= 10; i++) {
                if (!flag.get()) {
                    System.out.print(step1.incrementAndGet() + " ");
                    flag.set(true);
                } else i--;
            }
            i = 1;
            for (; i < 10; i++) {
                if (!flag.get()) {
                    System.out.print(step1.decrementAndGet() + " ");
                    flag.set(true);
                } else i--;
            }
            i = 2;
        }
    }

    public void doStep2() throws InterruptedException {
        while (true) {
            if (flag.get()) {
                Thread.sleep(500);
                System.out.println(step1.get());
                flag.set(false);
            }
        }
    }

}
