package com.example.test.repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NormalMemberRepository implements MemberRepository{
    @Override
    public void save(String name) {
        log.info("normal member save, name={}", name);
    }
}
