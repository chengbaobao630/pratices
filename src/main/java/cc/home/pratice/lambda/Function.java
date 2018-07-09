package cc.home.pratice.lambda;

/**
 * @author chengcheng
 */
@FunctionalInterface
public interface Function {

    void onSuccess();


    default  String getName(){
        return "function";
    }
}
