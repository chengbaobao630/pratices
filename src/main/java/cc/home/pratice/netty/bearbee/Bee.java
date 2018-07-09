package cc.home.pratice.netty.bearbee;

/**
 * @author chengcheng
 */
public class Bee {

    private  Integer limit;

    public Bee(Integer limit) {
        this.limit = limit;
    }

    public void addTang(Container container){
        try {
            container.putTang("tang"+(--limit));
            System.out.println("放了一颗糖:"+(limit));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
