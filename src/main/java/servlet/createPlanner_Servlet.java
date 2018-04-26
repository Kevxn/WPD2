package servlet;

import lombok.Data;
import db.H2Planner;
import app.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//for new planner (index page)

public class createPlanner_Servlet extends HttpServlet {
    private static final String DB_TEMPLATE = "src/main/resources/templates/index.mustache";
    private final H2Planner h2Planner;
    private final MustacheRenderer mustache;

    public createPlanner_Servlet(H2Planner h2Planner) {
        mustache = new MustacheRenderer();
        this.h2Planner = h2Planner;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Planner> plannerList = h2Planner.findPlanner();

        //printing out the contents of the planner for testing
        for(Planner p : plannerList){
            System.out.println(p.toString());
        }

        Map <String, Object> data = new HashMap<>();
        data.put("plannerList", plannerList);
        String html = mustache.render(DB_TEMPLATE, data);
        response.setContentType("text/html");
        response.setStatus(200);
        response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String plannerName = request.getParameter("plannerName");
        Planner planner = new Planner(plannerName);
        h2Planner.addPlanner(planner);
        //System.out.print(planner.toString());
        response.sendRedirect("/pickPlanner");

    }
}






