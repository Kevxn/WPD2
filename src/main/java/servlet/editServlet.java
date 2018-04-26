package servlet;

import app.model.Milestone;
import db.H2Planner;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class editServlet extends BaseServlet{

    private static final String DB_TEMPLATE1 = "src/main/resources/templates/edit.mustache";
    private static final String DB_TEMPLATE2 = "src/main/resources/templates/edit2.mustache";
    private final H2Planner h2Planner;
    private final MustacheRenderer mustache;

    private static final String ID_PARAMETER = "msgId";
    private static final String METHOD_PARAMETER = "method";


    public editServlet(H2Planner h2Planner) {
        mustache = new MustacheRenderer();
        this.h2Planner = h2Planner;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  Milestone m = h2Planner.editMilestone();
        List<Milestone> milestoneList = h2Planner.findMilestone();
        Map <String, Object> data = new HashMap<>();
         data.put("milestoneList", milestoneList);

        String html = mustache.render(DB_TEMPLATE1, data);
        response.setContentType("text/html");
        response.setStatus(200);
        response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // int id = Integer.parseInt(request.getParameter("id"));
        String eTitle = request.getParameter("etxtTitle");
       String eDescription = request.getParameter("etxtDescription");
      //  Milestone m = h2Planner.getMilestone(id);
        h2Planner.updateMilestone(1, eTitle, eDescription);
      //  m.setTitle(eTitle);
       // m.setDescription(eDescription);
        response.sendRedirect("/edit");
    }



}
