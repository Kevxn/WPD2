package servlet;


import app.model.*;

import db.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class deleteServlet extends BaseServlet {


    private static final String USER_MESSAGES_TEMPLATE = "src/main/resources/templates/delete.mustache";
    private static final String MESSAGE_PARAMETER = "message";
    private static final String METHOD_PARAMETER = "method";
    private static final String ID_PARAMETER = "msgId";

    private final H2Planner db;
    private final MustacheRenderer mustache;

    public deleteServlet(H2Planner db) {
        this.db = db;
        mustache = new MustacheRenderer();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Milestone> milestoneList = db.findMilestone();
        Map <String, Object> data = new HashMap<>();
        data.put("milestoneList", milestoneList);

        String html = mustache.render(USER_MESSAGES_TEMPLATE, data);
        response.setContentType("text/html");
        response.setStatus(200);
        response.getOutputStream().write(html.getBytes(Charset.forName("utf-8")));

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = getString(request, METHOD_PARAMETER, "post");
        if ("delete".equals(method)) {
            doDelete(request, response);
        } else {
            response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
        }
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = getInt(request, ID_PARAMETER);
        Milestone m = db.getMilestone(id);
        if (m != null) {
            db.delete(id);
        }
        response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
    }

    static String userFromRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] sub = uri.split("/");
        if (sub.length == 3) {
            return sub[2];
        }
        return "";
    }
}