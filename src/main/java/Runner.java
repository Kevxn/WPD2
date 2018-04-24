

import servlet.TestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import db.H2Planner;
import servlet.*;

public class Runner {
    @SuppressWarnings("unused")


    private static final int PORT = 9000;
   private final H2Planner h2Planner;



    private Runner() {
        h2Planner = new H2Planner();

    }

    private void start() throws Exception {
        Server server = new Server(PORT);

        ServletContextHandler handler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        handler.setInitParameter("org.eclipse.jetty.servlet.Default." + "resourceBase", "src/main/resources/webapp");


        handler.addServlet(new ServletHolder(new ppServlet(h2Planner)), "/pickPlanner/*");
        handler.addServlet(new ServletHolder(new ppServlet(h2Planner)), "/add3"); // we post to here

        handler.addServlet(new ServletHolder(new TestServlet(h2Planner)), "/serv/*");

       handler.addServlet(new ServletHolder(new dbServlet(h2Planner)), "/planner/*");
       handler.addServlet(new ServletHolder(new dbServlet(h2Planner)), "/add"); // we post to here



        handler.addServlet(new ServletHolder(new MilestoneServlet(h2Planner)), "/milestone/*");
       handler.addServlet(new ServletHolder(new MilestoneServlet(h2Planner)), "/add2"); // we post to here
        DefaultServlet ds = new DefaultServlet();
        handler.addServlet(new ServletHolder(ds), "/");

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
