package com.example.test.repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderRepository {
    public void save(String name){
        log.info("order save, name={}", name);
    }
}
