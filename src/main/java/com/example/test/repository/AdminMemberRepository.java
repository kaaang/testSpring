package com.example.test.repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminMemberRepository implements MemberRepository{
    @Override
    public void save(String name) {
        log.info("admin member save, name={}", name);
    }
}
