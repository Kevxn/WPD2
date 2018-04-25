package servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginServlet extends BaseServlet {
    // this servlet responds to POST requests
    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);
    private static final long serialVersionUID = -7461821901454655091L;

    public LoginServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String n=request.getParameter("userName");
        String output = "<p>Welcome "+n+"</p>";


        HttpSession session=request.getSession(true);
        session.setAttribute("uname",n);
        output += "<a href='/servlet2'>Visit again!</a>"    ;
        issue(HTML_UTF_8, HttpServletResponse.SC_OK, output.getBytes(CHARSET_UTF8), response);




    }


}
