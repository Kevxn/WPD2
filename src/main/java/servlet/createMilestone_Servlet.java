package servlet;

import app.model.Planner;
import db.H2Planner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.model.*;
import db.*;

public class createMilestone_Servlet extends HttpServlet{

    private static final String DB_TEMPLATE = "src/main/resources/templates/milestone.mustache";
    private final H2Planner h2Planner;
    private final H2Milestone h2Milestone;
    private final MustacheRenderer mustache;

    public createMilestone_Servlet(H2Planner h2Planner, H2Milestone h2Milestone) {
        mustache = new MustacheRenderer();
        this.h2Planner = h2Planner;
        this.h2Milestone = h2Milestone;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Milestone> milestoneList = h2Milestone.findMilestone();
        //validation

        Map<String, Object> data = new HashMap<>();
        data.put("milestoneList", milestoneList);
        String html = mustache.render(DB_TEMPLATE, data);
        response.setContentType("text/html");
        response.setStatus(200);
        response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("txtTitle");
        String description = request.getParameter("txtDescription");
        int plannerId = h2Planner.getId();

        System.out.println(title + " " + description + plannerId);
      //  Milestone tempm = new Milestone();
      //  Long datec = tempm.getCreatedDate();


        Milestone milestone = new Milestone(title, description, plannerId);

        h2Milestone.addMilestone(milestone);
        //System.out.print(planner.toString());
        response.sendRedirect("/plannerHomepage");
    }
}
