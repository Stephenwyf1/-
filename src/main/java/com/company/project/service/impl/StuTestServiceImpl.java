package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.StuTestMapper;
import com.company.project.entity.StuTestEntity;
import com.company.project.service.StuTestService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("stuTestService")
public class StuTestServiceImpl extends ServiceImpl<StuTestMapper, StuTestEntity> implements StuTestService {

    @Resource
    private StuTestMapper stuTestMapper;

    @Override
    public List<Map<String, Object>> getStuTestInfo(int Stu_id) {
        LambdaQueryWrapper<StuTestEntity> StuTestQueryWrapper = Wrappers.lambdaQuery();
        StuTestQueryWrapper.eq(StuTestEntity::getStuId, Stu_id);
        return stuTestMapper.selectMaps(StuTestQueryWrapper);
    }
}