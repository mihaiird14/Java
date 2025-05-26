import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;

class SimpleConnectionPool {
    private final List<Connection> pool = new LinkedList<>();
    private final int poolSize;

    public SimpleConnectionPool(String url, String user, String password, int dim) throws SQLException {
        this.poolSize = dim;
        for (int i = 0; i < dim; i++) {
            pool.add(DriverManager.getConnection(url, user, password));
        }
    }

    public synchronized Connection getConnection() throws InterruptedException {
        while (pool.isEmpty()) {
            wait();
        }
        return pool.remove(0);
    }

    public synchronized void releaseConnection(Connection conn) {
        pool.add(conn);
        notifyAll();
    }

    public synchronized void closeAll() {
        for (Connection conn : pool) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        pool.clear();
    }
}

class WorkerThread extends Thread {
    private final SimpleConnectionPool pool;
    private final int id;

    public WorkerThread(SimpleConnectionPool pool, int id) {
        this.pool = pool;
        this.id = id;
    }

    @Override
    public void run() {
        Connection conn = null;
        try {
            conn = pool.getConnection();
            String sql = "INSERT INTO Log (message, created_at) VALUES (?, NOW())";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "Mesaj din thread-ul " + id);
                stmt.executeUpdate();
            }

            int delay = 100 + new Random().nextInt(401);
            Thread.sleep(delay);

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                pool.releaseConnection(conn);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        final int M = 5;
        final int K = 10;

        final String url = "jdbc:postgresql://localhost:5432/ex3";
        final String user = "user";
        final String password = "parola";

        SimpleConnectionPool pool = null;

        try {
            pool = new SimpleConnectionPool(url, user, password, M);
            Thread[] threads = new Thread[K];
            for (int i = 0; i < K; i++) {
                threads[i] = new WorkerThread(pool, i + 1);
                threads[i].start();
            }

            for (Thread t : threads) {
                t.join();
            }

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Log")) {

                if (rs.next()) {
                    System.out.println("Număr total de înregistrări în Log: " + rs.getInt(1));
                }
            }

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 CallableStatement cs = conn.prepareCall("{ call delete_old_entries() }")) {
                cs.execute();
                System.out.println("Procedura stocată de curățare a fost apelată.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pool != null) {
                pool.closeAll();
            }
        }
    }
}
