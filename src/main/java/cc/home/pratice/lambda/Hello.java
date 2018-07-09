package cc.home.pratice.lambda;

import java.util.stream.IntStream;

/**
 * @author chengcheng
 */
public class Hello {

    public static void main(String[] args) throws InterruptedException {
        final IntStream range = IntStream.range(1, 100);
        System.out.println(range.sum());
        range.parallel().forEach(System.out::println);
        Thread.currentThread().join();
    }



}
