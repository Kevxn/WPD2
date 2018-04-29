package servlet;

import app.model.Milestone;
import app.model.Planner;
import db.H2Milestone;
import db.H2Planner;
import util.MustacheRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class sharedPlanner_Servlet extends HttpServlet{

    private static String MILESTONE_TEMPLATE = "src/main/resources/templates/shared.mustache";
    private static H2Planner h2Planner;
    private static H2Milestone h2Milestone;
    private static MustacheRenderer mustache;

    public sharedPlanner_Servlet(H2Planner h2Planner, H2Milestone h2Milestone){
        this.h2Planner = h2Planner;
        this.h2Milestone = h2Milestone;
        mustache = new MustacheRenderer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == ""){
            resp.sendRedirect("/login");
        }
        else{
            String shareId = (String)session.getAttribute("shareId");

            int id = h2Planner.shareIdToId(shareId);
            List<Planner> allPlanners = h2Planner.findPlanner();
            List<Milestone> allMilestones = h2Milestone.findMilestone();
            List<Milestone> temp = new ArrayList<Milestone>();
            Planner p = h2Planner.getPlanner(id);

            for (Milestone m : allMilestones){
                if (m.getPlannerId() == id){
                    temp.add(m);
                }
            }

            p.addMilestones(temp);
            String html = mustache.render(MILESTONE_TEMPLATE, p);
            resp.setContentType("text/html");
            resp.setStatus(200);
            resp.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
