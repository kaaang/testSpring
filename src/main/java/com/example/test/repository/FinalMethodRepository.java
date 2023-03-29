package com.example.test.repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FinalMethodRepository {
    public final void save() {
        log.info("final method 호출");
    }
}
