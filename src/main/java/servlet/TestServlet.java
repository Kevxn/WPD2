package servlet;


import app.model.*;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
//import java.nio.charset.Charset;



public class TestServlet extends servlet.BaseServlet {
    @SuppressWarnings("unused")

    private static final String MESSAGE_BOARD_TEMPLATE = "src/main/resources/webapp/mb.mustache.html";
    private static final long serialVersionUID = -7461821901454655091L;
  //  public static final Charset HTML_UTF_8 = Charset.forName("UTF-8");

    public TestServlet() {

    }

    MustacheRenderer mustache = new MustacheRenderer();

    private Object getObject(){

        //Create new Planner with the 'Planner One'
        Planner p = new Planner("Planner one");
        //Create two Milestone objects 'milestone one' & Milestone two
        Milestone m1 = new Milestone("Milestone one Title", "Milestone one Description");
        Milestone m2 = new Milestone();
        m2.setTitle("Milestone two Title");
        m2.setDescription("Milestone two Description");
        //Add to Planner Object (Currently this only works on the first planner)
        p.addMilestone(m1);
        p.addMilestone(m2);
        return p;
    }


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

        showView(response, MESSAGE_BOARD_TEMPLATE, getObject());
    }


}
