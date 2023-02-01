package by.mishastoma.connection;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

@Configuration
@PropertySource("classpath:application.properties")
@Slf4j
@RequiredArgsConstructor
public class ConnectionHolder {
    @Value("${db.con.number}")
    private int connectionsNumber;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    private BlockingQueue<Connection> free;
    private BlockingQueue<Connection> used;
    private ConcurrentHashMap<String, Connection> transactions;

    @PostConstruct
    private void init() throws SQLException {
        free = new LinkedBlockingDeque<>(connectionsNumber);
        used = new LinkedBlockingDeque<>(connectionsNumber);
        transactions = new ConcurrentHashMap<>();
        for (int i = 0; i < connectionsNumber; i++) {
            free.add(createConnection());
        }
    }

    @SneakyThrows
    public Connection getConnection() {
        if (transactions.containsKey(Thread.currentThread().getName())) {
            return transactions.get(Thread.currentThread().getName());
        }
        Connection connection = connection = free.take();
        used.put(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        boolean isRemoved = false;
        if (!transactions.containsKey(Thread.currentThread().getName())) {
            isRemoved = used.remove(connection);
            try {
                free.put(connection);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        return isRemoved;
    }

    public void beginTransaction() throws SQLException {
        Connection connection = getConnection();
        transactions.put(Thread.currentThread().getName(), connection);
        connection.setAutoCommit(false);
    }

    public void commitTransaction() throws SQLException {
        Connection connection = transactions.get(Thread.currentThread().getName());
        connection.commit();
        transactions.remove(Thread.currentThread().getName());
        releaseConnection(connection);
    }

    public void rollbackTransaction() throws SQLException {
        Connection connection = transactions.get(Thread.currentThread().getName());
        connection.rollback();
        transactions.remove(Thread.currentThread().getName());
        releaseConnection(connection);
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
