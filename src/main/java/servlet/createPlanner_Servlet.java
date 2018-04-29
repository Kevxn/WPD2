package servlet;

import db.H2Planner;
import app.model.*;
import util.MustacheRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//for new planner (index page)

public class createPlanner_Servlet extends BaseServlet{
    private static final String DB_TEMPLATE = "src/main/resources/templates/createPlanner.mustache";
    private final H2Planner h2Planner;
    private final MustacheRenderer mustache;

    public createPlanner_Servlet(H2Planner h2Planner) {
        mustache = new MustacheRenderer();
        this.h2Planner = h2Planner;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Planner> plannerList = h2Planner.findPlanner();

        Map <String, Object> data = new HashMap<>();
        data.put("plannerList", plannerList);
        String html = mustache.render(DB_TEMPLATE, data);
        response.setContentType("text/html");
        response.setStatus(200);
        response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == ""){
            response.sendRedirect("/login");
        }
        String currentUser = (String)session.getAttribute("username");

        String plannerName = request.getParameter("plannerName");
        String guid = UUID.randomUUID().toString();
        Planner planner = new Planner(plannerName, currentUser, guid);

        h2Planner.addPlanner(planner);
        response.sendRedirect("/pickPlanner");

    }
}







