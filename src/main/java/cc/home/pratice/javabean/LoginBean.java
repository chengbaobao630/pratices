package cc.home.pratice.javabean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author chengcheng
 */
public class LoginBean implements InitializingBean,EnvironmentAware {

    private final UserCheckService userCheckService;

    private String serviceName;

    private String version;

    public LoginBean(UserCheckService userCheckService) {
        this.userCheckService = userCheckService;
    }

    public String login(String username,String password){
        return serviceName + version + String.valueOf(userCheckService.doCheck(username,password));
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setEnvironment(Environment environment) {
//        environment.resolvePlaceholders();
    }

}
