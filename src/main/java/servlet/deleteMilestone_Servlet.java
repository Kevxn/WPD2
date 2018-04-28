package servlet;


import app.model.*;

import db.*;
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

public class deleteMilestone_Servlet extends BaseServlet {


    private static final String USER_MESSAGES_TEMPLATE = "src/main/resources/templates/deleteMilestone.mustache";
    private static final String MESSAGE_PARAMETER = "message";
    private static final String METHOD_PARAMETER = "method";
    private static final String ID_PARAMETER = "msgId";

    private final H2Planner h2Planner;
    private final H2Milestone h2Milestone;
    private final MustacheRenderer mustache;

    public deleteMilestone_Servlet(H2Planner h2Planner, H2Milestone h2Milestone) {
        this.h2Planner = h2Planner;
        this.h2Milestone = h2Milestone;
        mustache = new MustacheRenderer();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Milestone> milestoneList = h2Milestone.findMilestone();
        Map<String, Object> data = new HashMap<>();
        int pid = h2Planner.getId();
        List<Milestone> temp = new ArrayList<>();

        for (Milestone m: milestoneList){
            if (m.getPlannerId() == pid) {
                temp.add(m);
            }
        }

        if (temp.size() == 0){
            response.sendRedirect("errorH2.html");
        }
        else{
            data.put("milestoneList", temp);
            String html = mustache.render(USER_MESSAGES_TEMPLATE, data);
            response.setContentType("text/html");
            response.setStatus(200);
            response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = getString(request, METHOD_PARAMETER, "post");
        if ("delete".equals(method)) {
            doDelete(request, response);
        } else {
            response.sendRedirect("/plannerHomepage");
        }
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = getInt(request, ID_PARAMETER);
        Milestone m = h2Milestone.getMilestone(id);
        if (m != null) {
            h2Milestone.delete(id);
        }
        response.sendRedirect("/plannerHomepage");
    }



}