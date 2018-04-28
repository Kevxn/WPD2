

import db.ConnectionSupplier;
import db.H2Milestone;
import servlet.homePage_Servlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import db.H2Planner;
import db.H2User;
import servlet.*;

public class Runner {
    @SuppressWarnings("unused")


    private static final int PORT = 9000;
    private final H2Planner h2Planner;
    private final H2Milestone h2Milestone;
    private final H2User h2User;


    private Runner() {
        h2Planner = new H2Planner();
        h2Milestone = new H2Milestone();
        h2User = new H2User(new ConnectionSupplier(ConnectionSupplier.MEMORY));
    }

    private void start() throws Exception {
        Server server = new Server(PORT);

        ServletContextHandler handler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        handler.setInitParameter("org.eclipse.jetty.servlet.Default." + "resourceBase", "src/main/resources/webapp");

        handler.addServlet(new ServletHolder(new pickPlanner_Servlet(h2Planner)), "/pickPlanner/*");
        handler.addServlet(new ServletHolder(new homePage_Servlet(h2Planner, h2Milestone)), "/plannerHomepage/*");
        handler.addServlet(new ServletHolder(new createPlanner_Servlet(h2Planner)), "/createPlanner/*");

        handler.addServlet(new ServletHolder(new LoginServlet(h2User)), "/login/*");

        handler.addServlet(new ServletHolder(new editMilestone_Servlet(h2Milestone, h2Planner)), "/editMilestone/*");
        handler.addServlet(new ServletHolder(new createMilestone_Servlet(h2Planner, h2Milestone)), "/createMilestone/*");
        handler.addServlet(new ServletHolder(new deleteMilestone_Servlet(h2Planner, h2Milestone)), "/deleteMilestone");
        DefaultServlet ds = new DefaultServlet();
        handler.addServlet(new ServletHolder(ds), "/");

        handler.addServlet(new ServletHolder(new LogoutServlet()), "/logout");
        server.start();
        server.join();

    }

    public static void main(String[] args) {
        try {
            new Runner().start();
        } catch (Exception e) {
        }
    }
}
