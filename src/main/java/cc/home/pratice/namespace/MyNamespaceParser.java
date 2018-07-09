package cc.home.pratice.namespace;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * @author chengcheng
 */
public class MyNamespaceParser extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("config",new CcTargetNamespaceParser());
    }

    class CcTargetNamespaceParser extends AbstractSingleBeanDefinitionParser {

        @Override
        protected Class<?> getBeanClass(Element element) {
            return ConfigInit.class;
        }

        @Override
        protected void doParse(Element element, BeanDefinitionBuilder builder) {
            final String namespace = element.getAttribute("namespace");
            if (Objects.nonNull(namespace) && namespace.length() >0){
                System.out.println(namespace);
            }
        }

        @Override
        protected boolean shouldGenerateId() {
            return true;
        }
    }
}
