package servlet;


import app.model.*;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import db.H2Planner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
//import java.nio.charset.Charset;



public class TestServlet extends servlet.BaseServlet {
    @SuppressWarnings("unused")

    private static final String MESSAGE_BOARD_TEMPLATE = "src/main/resources/templates/mb.mustache";
    private static final long serialVersionUID = -7461821901454655091L;
  //  public static final Charset HTML_UTF_8 = Charset.forName("UTF-8");
    private final H2Planner h2Planner;




    public TestServlet(H2Planner h2Planner) {
        mustache = new MustacheRenderer();
        this.h2Planner = h2Planner;
    }

    MustacheRenderer mustache = new MustacheRenderer();




    protected void showView(HttpServletResponse response, String templateName, Object model)
            throws IOException{

        String html = mustache.render(templateName, model);
        issue("text/html", HttpServletResponse.SC_OK, html.getBytes(CHARSET_UTF8), response);
    }

    protected void issue(String mimeType, int returnCode, byte[] output, HttpServletResponse response)
        throws IOException{
        response.setContentType(mimeType);
        response.setStatus(returnCode);
        response.getOutputStream().write(output);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       //  response.setContentType("text/html");
       //  response.setStatus(HttpServletResponse.SC_OK);
       //  response.getWriter().println("<h1>Hello</h1><p>Dynamic Webpage</p>");


        //response.sendRedirect("/serv.html");

       // showView(response, MESSAGE_BOARD_TEMPLATE, h2Planner);

        List<Planner> planner = h2Planner.findPlanner();
        String h = mustache.render(MESSAGE_BOARD_TEMPLATE, planner);
        response.setContentType("text/html");
        response.setStatus(200);
        response.getOutputStream().write(h.getBytes(Charset.forName("utf-8")));

    }


}
