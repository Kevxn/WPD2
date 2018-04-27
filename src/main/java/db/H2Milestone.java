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
public class H2Milestone implements AutoCloseable{

    public static final String MEMORY = "jdbc:h2:mem:shop";
    public static final String FILE = "jdbc:h2:~/shop";

    private Connection connection;
    public int id;

    static Connection getConnection(String db) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");  // ensure the driver class is loaded when the DriverManager looks for an installed class. Idiom.
        return DriverManager.getConnection(db, "sa", "");  // default password, ok for embedded.
    }

    public H2Milestone() {
        this(MEMORY);
    }



    public H2Milestone(String db) {
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

    public void addMilestone(Milestone milestone) {
        final String ADD_MILESTONE_QUERY = "INSERT INTO milestone (id, title, description, plannerId, dueDate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(ADD_MILESTONE_QUERY)) {
            ps.setInt(1,milestone.getId());
            ps.setString(2, milestone.getTitle ());
            ps.setString(3, milestone.getDescription());
            ps.setInt(4, milestone.getPlannerId());
            ps.setString(5, milestone.getDueDate());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Milestone> findMilestone() {
        int varID = 0;
        final String LIST_MILESTONE_QUERY = "SELECT id, title, description, plannerId, dueDate FROM milestone";
        List<Milestone> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(LIST_MILESTONE_QUERY)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out.add(new Milestone(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }



    public Milestone getMilestone(int vid) {
        final String GET_MILESTONE_QUERY = "SELECT id, title, description, plannerId, dueDate FROM milestone WHERE id = ?";
        Milestone m = new Milestone();
        try (PreparedStatement ps = connection.prepareStatement(GET_MILESTONE_QUERY)) {
            ps.setInt(1, vid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = rs2milestone(rs);
            }
            return m;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public void updateMilestone(int id, String title, String description, String dueDate) {
        final String UPDATE_MILESTONE_QUERY = "UPDATE milestone SET title = ?, description = ?, dueDate = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_MILESTONE_QUERY)) {
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, dueDate);
            ps.setInt(4, id);//can change to have m.getdescription and so on
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }







    public void delete(int id) {
        System.out.println("delete");
        String ps = "DELETE FROM milestone WHERE id = ?";
        try (PreparedStatement p = connection.prepareStatement(ps)) {
            p.setLong(1, id);
            p.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private static Milestone rs2milestone(ResultSet rs) throws SQLException {
        return new Milestone(rs.getInt(1), rs.getString(2),
                rs.getString(3), rs.getInt(4), rs.getString(5));
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
