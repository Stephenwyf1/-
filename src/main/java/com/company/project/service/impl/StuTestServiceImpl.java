package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.StuTestMapper;
import com.company.project.entity.StuTestEntity;
import com.company.project.service.StuTestService;


@Service("stuTestService")
public class StuTestServiceImpl extends ServiceImpl<StuTestMapper, StuTestEntity> implements StuTestService {


}