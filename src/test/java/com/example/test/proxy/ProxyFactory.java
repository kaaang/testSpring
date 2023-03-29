package com.example.test.proxy;

import com.example.test.repository.AdminMemberRepository;
import com.example.test.repository.MemberRepository;
import com.example.test.repository.NormalMemberRepository;
import com.example.test.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ProxyFactory {

    @Test
    void save_normal_member() {
        String normalMemberName = "PARKER";
        MemberRepository target = new NormalMemberRepository();

        var proxyFactory = new org.springframework.aop.framework.ProxyFactory(target);
        proxyFactory.addAdvice(new TransactionAdvice());
        MemberRepository proxy = (MemberRepository) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.save(normalMemberName);
    }

    @Test
    void save_admin_member() {
        String adminMemberName = "PARKER_ADMIN";
        MemberRepository target = new AdminMemberRepository();

        var proxyFactory = new org.springframework.aop.framework.ProxyFactory(target);
        proxyFactory.addAdvice(new TransactionAdvice());
        MemberRepository proxy = (MemberRepository) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.save(adminMemberName);
    }

    @Test
    void save_order() {
        String orderName = "주문01";
        OrderRepository target = new OrderRepository();

        var proxyFactory = new org.springframework.aop.framework.ProxyFactory(target);
        proxyFactory.addAdvice(new TransactionAdvice());
        OrderRepository proxy = (OrderRepository) proxyFactory.getProxy();
        proxyFactory.setProxyTargetClass(true);

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.save(orderName);
    }
}