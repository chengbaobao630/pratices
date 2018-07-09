package cc.home.pratice;

import cc.home.pratice.javabean.SpringContextHelper;
import cc.home.pratice.scan.TestScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import java.util.stream.Stream;

/**
 * @author chengcheng
 */
public class ApplicationStartup implements cc.home.pratice.spring.ApplicationContext {

    @Override
    public void init(ServletContext servletContext) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring-ApplicationContext.xml");

        SpringContextHelper.setApplicationContext(applicationContext);

        System.out.println((applicationContext.getBean(TestScan.class)).getAuthor());
    }

}
