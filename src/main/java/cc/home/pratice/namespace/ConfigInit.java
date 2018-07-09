package cc.home.pratice.namespace;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.*;

/**
 * @author chengcheng
 */
public class ConfigInit implements BeanFactoryPostProcessor, EnvironmentAware, PriorityOrdered, BeanDefinitionRegistryPostProcessor,ApplicationListener<ContextRefreshedEvent> {

    private ConfigurableEnvironment environment;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        initializePropertySources();
    }

    private final String CONFIG_SOURCE_NAME = "cc_local_config";

    private void initializePropertySources() {
        if (environment.getPropertySources().contains(CONFIG_SOURCE_NAME)) {
            //already initialized
            return;
        }
        CompositePropertySource composite = new CompositePropertySource(CONFIG_SOURCE_NAME);
        ConfigUnit config = new ConfigUnit();
        config.put("username", "cc");
        config.put("author", "cc");
        config.put("password", "123");
        composite.addPropertySource(new CcPropertiesSource(CONFIG_SOURCE_NAME, config));
        environment.getPropertySources().addFirst(composite);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        final MutablePropertySources propertySources = environment.getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            System.out.println(propertySource.getProperty("service-name"));
            System.out.println(propertySource.getName());
            System.out.println(propertySource.getSource());
        }

    }
}
