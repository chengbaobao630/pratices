package cc.home.pratice.netty.bearbee;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author chengcheng
 */
public class Factory {

    public static void main(String[] args) throws InterruptedException {
        Container container = new Container();
        final ExecutorService service = Executors.newFixedThreadPool(10);
        Runnable bearR = ()->{
            Bear bear = new Bear();
            try {
                for(;;) {
                    TimeUnit.SECONDS.sleep(1);
                    bear.getTang(container);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        service.execute(bearR);
        service.execute(bearR);
        service.execute(bearR);
        service.execute(bearR);
        service.execute(bearR);

        Runnable beeR = ()->{
            Bee bear = new Bee(100);
            for(;;) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    bear.addTang(container);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        Executors.newSingleThreadExecutor().execute(beeR);

        Thread.currentThread().join();
    }
}
