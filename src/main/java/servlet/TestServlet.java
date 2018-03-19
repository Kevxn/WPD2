package servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends servlet.BaseServlet {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -7461821901454655091L;



    public TestServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello</h1><p>Dynamic Webpage</p>");

    }

}
