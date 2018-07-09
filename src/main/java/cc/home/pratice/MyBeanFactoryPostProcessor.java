package cc.home.pratice;

import cc.home.pratice.javabean.LoginBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author chengcheng
 */
public class MyBeanFactoryPostProcessor  implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(1231231000);
        final Object login = beanFactory.getBean("login");
        final Object loginBean = beanFactory.getBean(LoginBean.class);
        System.out.println(login);
        System.out.println(loginBean);
    }
}
