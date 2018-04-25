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

public class editServlet extends HttpServlet{

    private static final String DB_TEMPLATE = "src/main/resources/templates/edit.mustache";
    private final H2Planner h2Planner;
    private final MustacheRenderer mustache;

    public editServlet(H2Planner h2Planner) {
        mustache = new MustacheRenderer();
        this.h2Planner = h2Planner;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Milestone m = h2Planner.editMilestone();

dfs

        String html = mustache.render(DB_TEMPLATE, m);
        response.setContentType("text/html");
        response.setStatus(200);
        response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        String etitle = request.getParameter("etxtTitle");
        String edescription = request.getParameter("etxtDescription");
        System.out.println(etitle + edescription);
        List<Milestone> milestoneList = h2Planner.findMilestone();
        Milestone m = milestoneList.get(1);
        m.setTitle(etitle);
        m.setDescription(edescription);

       System.out.println(m.toString());
       //System.out.print(planner.toString());
        response.sendRedirect("/serv");
    }

}
