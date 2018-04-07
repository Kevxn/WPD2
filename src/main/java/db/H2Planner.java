package db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.Planner;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class H2Planner implements AutoCloseable {

    static final Logger LOG = LoggerFactory.getLogger(H2Planner.class);

    public static final String MEMORY = "jdbc:h2:mem:shop";
    public static final String FILE = "jdbc:h2:~/shop";

    private Connection connection;

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
        final String ADD_PERSON_QUERY = "INSERT INTO planner (plannerName) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(ADD_PERSON_QUERY)) {
            ps.setString(1, planner.getPlannerName ());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Planner> findPlanner() {
        final String LIST_PERSONS_QUERY = "SELECT plannerName  FROM planner";
        List<Planner> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(LIST_PERSONS_QUERY)) {
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




}

