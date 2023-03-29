package com.example.test.repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoDefaultConstructorRepository {
    private final String str;

    public NoDefaultConstructorRepository(String str) {
        this.str = str;
    }

    public void save() {
        log.info("기본 생성자가 존재하지 않는 클래스");
    }
}
