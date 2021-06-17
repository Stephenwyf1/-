package com.company.project.common.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

import lombok.extern.slf4j.Slf4j;

/**
 * int ID生成器
 * 原项目的ID生成是long，此处改为int类型生成器
 *
 */
@Slf4j
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    private final AtomicInteger al = new AtomicInteger((int) System.currentTimeMillis());

    @Override
    public Integer nextId(Object entity) {
        String bizKey = entity.getClass().getName();
        log.info("bizKey:{}", bizKey);
        final int id = al.getAndAdd(1);
        return id;
    }
}