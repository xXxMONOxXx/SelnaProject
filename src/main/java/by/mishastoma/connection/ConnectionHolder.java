package by.mishastoma.connection;

import by.mishastoma.annotation.Transaction;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@PropertySource("classpath:application.properties")
@Slf4j
public class ConnectionHolder {
    @Value("${db.con.number}")
    private int connectionsNumber;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    private BlockingQueue<Connection> free;
    private BlockingQueue<Connection> used;
    private AtomicBoolean transactionActive;

    @PostConstruct
    private void init() throws SQLException {
        transactionActive = new AtomicBoolean(false);
        free = new LinkedBlockingDeque<>(connectionsNumber);
        used = new LinkedBlockingDeque<>(connectionsNumber);
        for (int i = 0; i < connectionsNumber; i++) {
            free.add(createConnection());
        }
    }

    @SneakyThrows
    public Connection getConnection() {
        Method method = null;
        final Thread t = Thread.currentThread();
        final StackTraceElement[] stackTrace = t.getStackTrace();
        final StackTraceElement ste = stackTrace[2];
        final String methodName = ste.getMethodName();
        final String className = ste.getClassName();
        Class<?> kls = Class.forName(className);
        do {
            for (final Method candidate : kls.getDeclaredMethods()) {
                if (candidate.getName().equals(methodName)) {
                    method = candidate;
                }
            }
            kls = kls.getSuperclass();
        } while (kls != null);
        Connection connection = null;
        try {
            if (method != null) {
                if (method.isAnnotationPresent(Transaction.class)) {
                    transactionActive.set(true);
                } else {
                    if (transactionActive.get()) {
                        synchronized (transactionActive) {
                            transactionActive.wait();
                        }
                    }
                }
            }
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        boolean isRemoved = false;
        isRemoved = used.remove(connection);
        if (isRemoved) {
            try {
                free.put(connection);
                if (transactionActive.get()) {
                    transactionActive.set(false);
                    synchronized (transactionActive) {
                        transactionActive.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        return isRemoved;
    }

    public void beginTransaction(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commitTransaction(Connection connection) throws SQLException {
        connection.commit();
    }

    public void rollbackTransaction(Connection connection) throws SQLException {
        connection.rollback();
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
