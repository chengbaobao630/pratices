package cc.home.pratice.concurrent;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author chengcheng
 */
public class ForkJoin {

    static class SumCount extends RecursiveTask<Integer> {

        private List<Integer> numbers;

        public SumCount(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        protected Integer compute() {
            System.out.println(Thread.currentThread().getId()+":"+numbers);
            if (numbers.size() <= 10){
                return numbers.parallelStream().reduce((n1,n2)-> n1 + n2).orElse(0);
            }else {
                SumCount left = new SumCount(numbers.subList(0,10));
                SumCount right = new SumCount(numbers.subList(10,numbers.size()));
                left.fork();
                right.fork();
                return left.join() + right.join();

            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        final ForkJoinTask<Integer> submit = forkJoinPool.submit(new SumCount(IntStream.range(1, 51).boxed().collect(Collectors.toList())));
        System.out.println(submit.get());
    }
}
