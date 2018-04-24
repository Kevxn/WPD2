package servlet;


import app.model.Milestone;
import app.model.Planner;
import db.H2Planner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.nio.charset.Charset;


public class ppServlet extends BaseServlet {
    @SuppressWarnings("unused")

    private static final String MESSAGE_BOARD_TEMPLATE = "src/main/resources/templates/pp.mustache";
    private static final long serialVersionUID = -7461821901454655091L;
  //  public static final Charset HTML_UTF_8 = Charset.forName("UTF-8");
    private final H2Planner h2Planner;
    private int id;




    public ppServlet(H2Planner h2Planner) {
        mustache = new MustacheRenderer();
        this.h2Planner = h2Planner;

    }

    MustacheRenderer mustache = new MustacheRenderer();


    public void setId(int id) {
        this.id = id;
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
        List<Planner> plannerList = h2Planner.findPlanner();
        Map <String, Object> data = new HashMap<>();
        data.put("plannerList", plannerList);
        String html = mustache.render(MESSAGE_BOARD_TEMPLATE, data);
        response.setContentType("text/html");
        response.setStatus(200);
        response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        h2Planner.setId(id);
        response.sendRedirect("/serv");
    }


}
