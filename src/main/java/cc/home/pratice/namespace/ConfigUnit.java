package cc.home.pratice.namespace;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author chengcheng
 */
public class ConfigUnit extends HashMap<String,Object> {

    private Map<String,Object> concurrentHashMap
            = Collections.synchronizedMap(new HashMap<>());


    @Override
    public Set<Entry<String, Object>> entrySet() {
        return concurrentHashMap.entrySet();
    }





}
