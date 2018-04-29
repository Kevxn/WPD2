package servlet;

import app.model.Milestone;
import db.H2Milestone;
import db.H2Planner;
import util.MustacheRenderer;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class editMilestone_Servlet extends BaseServlet{

    private static final String DB_TEMPLATE1 = "src/main/resources/templates/editMilestone.mustache";

    private final H2Milestone h2Milestone;
    private final H2Planner h2Planner;
    private final MustacheRenderer mustache;


    public editMilestone_Servlet( H2Milestone h2Milestone, H2Planner h2Planner) {
        mustache = new MustacheRenderer();

        this.h2Milestone = h2Milestone;
        this.h2Planner = h2Planner;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Milestone> milestoneList = h2Milestone.findMilestone();
        Map <String, Object> data = new HashMap<>();
            int pid = h2Planner.getId();
            List<Milestone> temp = new ArrayList<>();

            for (Milestone m : milestoneList) {
                if (m.getPlannerId() == pid) {
                    temp.add(m);
                    }

            }
            if(temp.size() == 0) {
                response.sendRedirect("errorH.html");
            }else {
                data.put("milestoneList", temp);

                String html = mustache.render(DB_TEMPLATE1, data);
                response.setContentType("text/html");
                response.setStatus(200);
                response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));

            }
        }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     int id = Integer.parseInt(request.getParameter("id"));
        String eTitle = request.getParameter("etxtTitle");
       String eDescription = request.getParameter("etxtDescription");
       String eDueDate = request.getParameter("etxtDueDate");
      //  Milestone m = h2Planner.getMilestone(id);
        h2Milestone.updateMilestone(id, eTitle, eDescription, eDueDate);
      //  m.setTitle(eTitle);
       // m.setDescription(eDescription);
        response.sendRedirect("/plannerHomepage");
    }



}
