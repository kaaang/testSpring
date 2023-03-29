package com.example.test.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class TransactionInvocationHandler implements InvocationHandler {
    private final Object target;

    public TransactionInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("InvocationHandler parameter: proxy={}, method={}, args={}", proxy, method, args);

        try {
            log.info("--- 트랜잭션 커밋 시작 ---");

            Object result = method.invoke(target, args);

            log.info("--- 트랜잭션 커밋 완료 ---");
            return result;
        } catch (Exception e) {
            log.info("--- 트랜잭션 롤백 ---");
            throw e;
        } finally {
            log.info("--- DB 커넥션 자원 반환 ---");
        }
    }
}
