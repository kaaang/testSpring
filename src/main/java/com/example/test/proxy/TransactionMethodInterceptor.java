package com.example.test.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TransactionMethodInterceptor implements MethodInterceptor {
    private final Object target;

    public TransactionMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        try {
            log.info("--- 트랜잭션 커밋 시작 ---");
            Object result = proxy.invoke(target, args);
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
