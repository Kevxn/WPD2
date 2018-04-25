package servlet;

import db.H2Planner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;


public class LoginServlet extends HttpServlet {
    // this servlet responds to POST requests
    @SuppressWarnings("unused")
    private static final String DB_TEMPLATE = "src/main/resources/templates/login.mustache";
    private final MustacheRenderer mustache;
    private final H2Planner h2planner;

    static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);
    private static final long serialVersionUID = -7461821901454655091L;

    public LoginServlet(H2Planner h2planner) {
        mustache = new MustacheRenderer();
        this.h2planner = h2planner;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String data = "hellooooooo";
        String html = mustache.render(DB_TEMPLATE, data);
        response.setContentType("text/html");
        response.setStatus(200);
        response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("txtUsername");
        String password = req.getParameter("txtPassword");

        System.out.println("User tried to login with " + username + " and " + password);
    }
}
