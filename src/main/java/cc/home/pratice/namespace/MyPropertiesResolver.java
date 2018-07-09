package cc.home.pratice.namespace;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Properties;

/**
 * @author chengcheng
 */
public class MyPropertiesResolver extends PropertySourcesPlaceholderConfigurer implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("version","10");
        setProperties(properties);
        mergeProperties();
    }
}
