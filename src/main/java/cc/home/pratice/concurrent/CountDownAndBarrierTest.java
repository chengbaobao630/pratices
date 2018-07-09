package cc.home.pratice.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chengcheng
 */
public class CountDownAndBarrierTest {



    static Integer defaultSize = 10;

    static CountDownLatch countDownLatch = new CountDownLatch(defaultSize);

    static ExecutorService executorService = Executors.newFixedThreadPool(defaultSize);

    public static void main(String[] args) {
        countDownLatchTest();
        cyclicBarrierTest();
    }

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    private static void countDownLatchTest(){
        for (int a = 0 ; a < defaultSize ; a ++) {
            executorService.execute(() -> {
                countDownLatch.countDown();
                try {
                    System.out.println("countDownLatch.await();");
                    countDownLatch.await();
                    incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
    }


    private static void cyclicBarrierTest(){
        for (int a = 0 ; a < defaultSize ; a ++) {
            executorService.execute(() -> {
                try {
                    System.out.println("cyclicBarrier.await();");
                    cyclicBarrier.await();
                    incr();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }

            });
        }
    }


    private static AtomicInteger initialization = new AtomicInteger();

    private static void incr(){
        System.out.println(initialization.incrementAndGet());
    }
}
