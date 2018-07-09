package cc.home.pratice.namespace;

import org.springframework.core.env.EnumerablePropertySource;

/**
 * @author chengcheng
 */
public class CcPropertiesSource extends EnumerablePropertySource<ConfigUnit> {


    public CcPropertiesSource(String name, ConfigUnit source) {
        super(name, source);
    }

    protected CcPropertiesSource(String name) {
        super(name);
    }

    @Override
    public String[] getPropertyNames() {
        return this.source.keySet().toArray(new String[]{});
    }

    @Override
    public Object getProperty(String name) {
        return this.source.get(name);
    }
}
