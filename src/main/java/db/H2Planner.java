package db;

import app.model.Milestone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.Planner;

import java.awt.*;
import java.io.IOException;
import java.io.SyncFailedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class H2Planner implements AutoCloseable {


    public static final String MEMORY = "jdbc:h2:mem:shop";
    public static final String FILE = "jdbc:h2:~/shop";

    private Connection connection;
    public int id;

    static Connection getConnection(String db) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");  // ensure the driver class is loaded when the DriverManager looks for an installed class. Idiom.
        return DriverManager.getConnection(db, "sa", "");  // default password, ok for embedded.
    }

    public H2Planner() {
        this(MEMORY);
    }

    public H2Planner(String db) {
        try {
            connection = getConnection(db);
            loadResource("/planner.sql");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlanner(Planner planner) {
        final String ADD_PLANNER_QUERY = "INSERT INTO planner (plannerName) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(ADD_PLANNER_QUERY)) {
            ps.setString(1, planner.getPlannerName ());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Planner> findPlanner() {
        final String LIST_PLANNER_QUERY = "SELECT plannerName FROM planner";
        List<Planner> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(LIST_PLANNER_QUERY)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out.add(new Planner(rs.getString(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }




    private void loadResource(String name) {
        try {
            String cmd = new Scanner(getClass().getResource(name).openStream()).useDelimiter("\\Z").next();
            PreparedStatement ps = connection.prepareStatement(cmd);
            ps.execute();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private static Milestone rs2milestone(ResultSet rs) throws SQLException {
        return new Milestone(rs.getInt(1), rs.getString(2),
                rs.getString(3), rs.getInt(4));
    }


}

