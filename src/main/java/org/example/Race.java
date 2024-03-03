package org.example;

import java.util.concurrent.*;
import java.util.*;
import java.util.stream.IntStream;

public class Race {

    private static final int NUM_CARS = 10;
    private static final int TUNNEL_CAPACITY = 3;
    private static final Random random = new Random();

    private static final Semaphore tunnelSemaphore = new Semaphore(TUNNEL_CAPACITY);
    private static CountDownLatch finishLatch = new CountDownLatch(NUM_CARS);
    private static long startTime;
    private static final long[] finishTimes = new long[NUM_CARS];
    private static final boolean[] eliminated = new boolean[NUM_CARS];

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_CARS);

        while (IntStream.range(0, NUM_CARS).filter(i -> !eliminated[i]).count() > 3) {
            Arrays.fill(finishTimes, 0);
            finishLatch = new CountDownLatch((int) IntStream.range(0, NUM_CARS).filter(i -> !eliminated[i]).count());

            for (int i = 0; i < NUM_CARS; i++) {
                if (!eliminated[i]) {
                    executorService.submit(new Car(i));
                }
            }
            startTime = System.nanoTime();

            // finish
            try {
                finishLatch.await();
            } catch (InterruptedException ignored) {
            }

            // eliminate max finish time
            int maxTimeIndex = IntStream.range(0, NUM_CARS)
                    .filter(i -> !eliminated[i])
                    .boxed()
                    .max(Comparator.comparingLong(i -> finishTimes[i]))
                    .orElse(-1);
            eliminated[maxTimeIndex] = true;
        }

        executorService.shutdown();
    }

    private static class Car implements Runnable {

        private final int id;

        public Car(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            // preparing
            long preparationTime = random.nextInt(1500) + 2000;
            try {
                Thread.sleep(preparationTime);
                System.out.println("Auto " + id + " prepared");
            } catch (InterruptedException ignored) {
            }



            // common road
            long roadTime = random.nextInt(3000);
            try {
                Thread.sleep(roadTime);
            } catch (InterruptedException ignored){
            }

            // enter the tunnel
            try {
                tunnelSemaphore.acquire();
                System.out.println("Auto " + id + " entered the tunnel");
            } catch (InterruptedException ignored) {
            }

            // road through the tunnel
            long tunnelTime = random.nextInt(2000) + 1000; // добавляем случайность, чтобы увеличить разрыв
            try {
                Thread.sleep(tunnelTime);
            } catch (InterruptedException ignored) {
            }

            // tunnel exit
            System.out.println("Auto " + id + " has exited the tunnel");
            tunnelSemaphore.release();


            // common road before finish
            long roadToFinishTime = random.nextInt(3000);
            try {
                Thread.sleep(roadToFinishTime);
            } catch (InterruptedException ignored) {
            }

            // finish
            finishTimes[id] = System.nanoTime();
            finishLatch.countDown();
            System.out.println("Auto " + id + " finished");
        }
    }
}
