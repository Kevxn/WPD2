package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSupplier {
    public static final String MEMORY = "jdbc:h2:mem:test";
    public static final String FILE = "jdbc:h2:~/test";

    private final String db;

    public ConnectionSupplier(String db) {
        this.db = db;
    }

    public Connection provide() {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(db, "sa", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionSupplierException(e);
        }
    }

    public class ConnectionSupplierException extends RuntimeException {
        ConnectionSupplierException(Exception e) {
            super(e);
        }
    }
}

