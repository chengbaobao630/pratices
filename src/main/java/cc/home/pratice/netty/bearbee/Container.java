package cc.home.pratice.netty.bearbee;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * @author chengcheng
 */
public class Container {
    private List<String> tangs=Collections.synchronizedList(Lists.newArrayList());

    public  String getTang() throws InterruptedException {
        System.out.println(tangs);
        if (tangs.isEmpty()){
            synchronized (this) {
                System.out.println("没有tang");
                wait();
                return getTang();
            }
        }else {
            final String s = tangs.remove(0);
            System.out.println(s);
            return s;
        }
    }

    public  void putTang(String tang) throws InterruptedException {
        tangs.add(tang);
        synchronized (this) {
            notifyAll();
        }
    }

}
