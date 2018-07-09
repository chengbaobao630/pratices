package cc.home.pratice.javabean;

/**
 * @author chengcheng
 */
public class MockUserCheckServiceImpl implements UserCheckService {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Boolean doCheck(String username, String password) {
        return this.username.equalsIgnoreCase(username) && this.password.equalsIgnoreCase(password);
    }
}
