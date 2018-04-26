package servlet;

import app.model.Milestone;
import db.H2Milestone;
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

public class editMilestone_Servlet extends BaseServlet{

    private static final String DB_TEMPLATE1 = "src/main/resources/templates/edit.mustache";
    private static final String DB_TEMPLATE2 = "src/main/resources/templates/edit2.mustache";

    private final H2Milestone h2Milestone;
    private final MustacheRenderer mustache;

    private static final String ID_PARAMETER = "msgId";
    private static final String METHOD_PARAMETER = "method";


    public editMilestone_Servlet( H2Milestone h2Milestone) {
        mustache = new MustacheRenderer();

        this.h2Milestone = h2Milestone;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Milestone> milestoneList = h2Milestone.findMilestone();
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
        h2Milestone.updateMilestone(1, eTitle, eDescription);
      //  m.setTitle(eTitle);
       // m.setDescription(eDescription);
        response.sendRedirect("/plannerHomepage");
    }



}
