package cc.home.pratice.netty.bearbee;

import java.util.Objects;

/**
 * @author chengcheng
 */
public class Bear {


    public void getTang(Container container){
        try {
            final String tang = container.getTang();
            System.out.println("bear:"+tang);
            if (Objects.isNull(tang) || tang.isEmpty()){
                System.out.println("没有糖");
            }else {
                System.out.println("吃了一颗糖:" + tang);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
