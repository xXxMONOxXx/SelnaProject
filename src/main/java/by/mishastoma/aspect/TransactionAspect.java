package by.mishastoma.aspect;

import by.mishastoma.connection.ConnectionHolder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@RequiredArgsConstructor
public class TransactionAspect {
    private final ConnectionHolder connectionHolder;

    @SneakyThrows
    @Before("@annotation(by.mishastoma.annotation.Transaction)")
    public void beforeTransaction() {
        connectionHolder.beginTransaction();
    }

    @SneakyThrows
    @After("@annotation(by.mishastoma.annotation.Transaction)")
    public void afterTransaction() {
        connectionHolder.commitTransaction();
    }

    @SneakyThrows
    @AfterThrowing("@annotation(by.mishastoma.annotation.Transaction)")
    public void exceptionTransaction() {
        connectionHolder.commitTransaction();
    }
}