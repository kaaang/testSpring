package com.example.test.proxy;

import com.example.test.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CGLIB {
    @Test
    void save_normal_member() {
        String normalMemberName = "PARKER";
        MemberRepository target = new NormalMemberRepository();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MemberRepository.class);
        enhancer.setCallback(new TransactionMethodInterceptor(target));
        MemberRepository proxy = (MemberRepository) enhancer.create();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.save(normalMemberName);
    }

    @Test
    void save_admin_member() {
        String adminMemberName = "PARKER_ADMIN";
        MemberRepository target = new AdminMemberRepository();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MemberRepository.class);
        enhancer.setCallback(new TransactionMethodInterceptor(target));
        MemberRepository proxy = (MemberRepository) enhancer.create();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.save(adminMemberName);
    }

    @Test
    void save_order() {
        String orderName = "주문01";
        OrderRepository target = new OrderRepository();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderRepository.class);
        enhancer.setCallback(new TransactionMethodInterceptor(target));
        OrderRepository proxy = (OrderRepository) enhancer.create();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
        proxy.save(orderName);
    }

    @Test
    void final_class_test() {
        FinalRepository target = new FinalRepository();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FinalRepository.class);
        enhancer.setCallback(new TransactionMethodInterceptor(target));
        FinalRepository proxy = (FinalRepository) enhancer.create();

        proxy.save();
    }

    @Test
    void final_method_test() {
        FinalMethodRepository target = new FinalMethodRepository();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FinalMethodRepository.class);
        enhancer.setCallback(new TransactionMethodInterceptor(target));
        FinalMethodRepository proxy = (FinalMethodRepository) enhancer.create();

        proxy.save();
    }

    @Test
    void no_default_constructor_test() {
        NoDefaultConstructorRepository target = new NoDefaultConstructorRepository("test");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(NoDefaultConstructorRepository.class);
        enhancer.setCallback(new TransactionMethodInterceptor(target));
        NoDefaultConstructorRepository proxy = (NoDefaultConstructorRepository) enhancer.create();

        proxy.save();
    }
}