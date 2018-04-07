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
import java.util.List;


public class dbServlet extends HttpServlet {
    private static final String DB_TEMPLATE = "src/main/resources/templates/index.mustache";
    private final H2Planner h2Planner;
    private final MustacheRenderer mustache;

    public dbServlet(H2Planner h2Planner) {
        mustache = new MustacheRenderer();
        this.h2Planner = h2Planner;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Planner> planner = h2Planner.findPlanner();
        System.out.println("test");
        System.out.println(planner.toString());
        String html = mustache.render(DB_TEMPLATE, planner);
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
        response.sendRedirect("/index.html");
    }
}







