package servlet;

import db.H2User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


public class LoginServlet extends HttpServlet {
    // this servlet responds to POST requests
    @SuppressWarnings("unused")
    private static final String DB_TEMPLATE = "src/main/resources/templates/login.mustache";
    private final MustacheRenderer mustache;
    private H2User h2User;

    static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);
    private static final long serialVersionUID = -7461821901454655091L;
    public LoginServlet(H2User h2User) {
        this.h2User = h2User;
        mustache = new MustacheRenderer();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = getCurrentUser(request);
        Map<String, String> params = new HashMap<>();
        params.put("username", user);

//        response.sendRedirect("/createPlanner");

        String data = "hello";
        String html = mustache.render(DB_TEMPLATE, data);
        response.setContentType("text/html");
        response.setStatus(200);
        response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("txtUsername");
        String password = req.getParameter("txtPassword");

        Map<String, String> params = new HashMap<>();
        params.put("username", username);

        if (username.length() == 0 || password.length() == 0){
            params.put("err", "Field cannot be empty");
        }
        else if (!checkUserAndPass(username, password)){
            params.put("err", "username and pass don't work");
        }

        if (params.containsKey("err")){

            String html = mustache.render(DB_TEMPLATE, params);
            resp.setContentType("text/html");
            resp.setStatus(200);
            resp.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));
//            resp.sendRedirect("/login");
        }
        else{
            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);
            resp.sendRedirect("/pickPlanner");
        }

        System.out.println("User tried to login with " + username + " and " + password);
    }

    private boolean checkUserAndPass(String userName, String password) {
        if (h2User.isRegistered(userName)) {
            return h2User.login(userName, password);
        } else {
            if (h2User.register(userName, password)) {
                return true;
            }
        }
        return false;
    }

    private String getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "";
        }
        String val = (String)session.getAttribute("username");
        return val == null ? "" : val;
    }
}
