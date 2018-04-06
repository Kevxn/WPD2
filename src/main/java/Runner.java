

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


    private Runner() { h2Planner = new H2Planner();
    }

    private void start() throws Exception {
        Server server = new Server(PORT);

        ServletContextHandler handler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        handler.setInitParameter("org.eclipse.jetty.servlet.Default." + "resourceBase", "src/main/resources/webapp");

        TestServlet demoServlet = new TestServlet();
        handler.addServlet(new ServletHolder(demoServlet), "/serv/*");


       handler.addServlet(new ServletHolder(new dbServlet(h2Planner)), "/index.html");
       handler.addServlet(new ServletHolder(new dbServlet(h2Planner)), "/add"); // we post to here

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
