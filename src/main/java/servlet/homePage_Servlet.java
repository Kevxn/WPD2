package servlet;


import app.model.Milestone;
import app.model.Planner;
import db.H2Milestone;
import db.H2Planner;
import util.MustacheRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.text.*;
//import java.nio.charset.Charset;


public class homePage_Servlet extends servlet.BaseServlet {
    @SuppressWarnings("unused")

    private static final String MESSAGE_BOARD_TEMPLATE = "src/main/resources/templates/homepage.mustache";
    private static final long serialVersionUID = -7461821901454655091L;
    //  public static final Charset HTML_UTF_8 = Charset.forName("UTF-8");
    private final H2Planner h2Planner;
    private final H2Milestone h2Milestone;


    public homePage_Servlet(H2Planner h2Planner, H2Milestone h2Milestone) {
        mustache = new MustacheRenderer();
        this.h2Planner = h2Planner;
        this.h2Milestone = h2Milestone;

    }

    MustacheRenderer mustache = new MustacheRenderer();


    protected void showView(HttpServletResponse response, String templateName, Object model)
            throws IOException {

        String html = mustache.render(templateName, model);
        issue("text/html", HttpServletResponse.SC_OK, html.getBytes(CHARSET_UTF8), response);
    }

    protected void issue(String mimeType, int returnCode, byte[] output, HttpServletResponse response)
            throws IOException {
        response.setContentType(mimeType);
        response.setStatus(returnCode);
        response.getOutputStream().write(output);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == ""){
            response.sendRedirect("/login");
        }

        List<Planner> plannerList = h2Planner.findPlanner();
        List<Milestone> milestoneList = h2Milestone.findMilestone();
        if(plannerList.size() == 0 || plannerList == null){
            response.sendRedirect("errorH2.html");
        }else{
        int pid = h2Planner.getId();
        Planner p = h2Planner.getPlanner(pid);

        if (session.getAttribute("shareId") != null){
            p.setShareId("Share this Planner: localhost:9000/shared?shareId=" + (String)session.getAttribute("shareId"));
        }

        List<Milestone> temp = new ArrayList<>();
        for (Milestone m : milestoneList) {
            if (m.getPlannerId() == p.getId()) {
                temp.add(m);

                }
            }
            p.addMilestones(temp);
            String html = mustache.render(MESSAGE_BOARD_TEMPLATE, p);
            response.setContentType("text/html");
            response.setStatus(200);
            response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == ""){
            response.sendRedirect("/login");
        }

       if (request.getParameter("btnShare") != null){

               List<Planner> plannerList = h2Planner.findPlanner();
               List<Milestone> milestoneList = h2Milestone.findMilestone();
               int plannerId = h2Planner.getId();
               Planner p = h2Planner.getPlanner(plannerId);
               String shareableId = "";
               for (Milestone m : milestoneList){
                   if (m.getPlannerId() == p.getId()){
                       shareableId = h2Planner.findPlannerShareId(m.getPlannerId());
                       System.out.println(h2Planner.findPlannerShareId(m.getPlannerId()));
                       session.setAttribute("shareId", shareableId);
                       response.sendRedirect("/plannerHomepage");
                   }
               }
       }
       else if (request.getParameter("btnCompleted") != null){
           int id = getInt(request, "compId");
           Milestone m = h2Milestone.getMilestone(id);
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
           Date date = new Date();
           m.setCompDate((dateFormat.format(date)));
           h2Milestone.completedMilestone(m);
           response.sendRedirect("/plannerHomepage");
       }
    }
}


