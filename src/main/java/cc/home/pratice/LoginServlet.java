package cc.home.pratice;

import cc.home.pratice.javabean.LoginBean;
import cc.home.pratice.javabean.SpringContextHelper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chengcheng
 */
@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 614545835968097710L;

    private final LoginBean loginBean ;

    public LoginServlet() {
        this.loginBean = SpringContextHelper.getBean(LoginBean.class);
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        final String author = config.getServletContext().getInitParameter("author");
        System.out.println(author);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        final String login = loginBean.login(username, password);
        resp.getWriter().write("login "+ login);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
