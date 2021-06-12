package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.StuTestMapper;
import com.company.project.entity.StuTestEntity;
import com.company.project.service.StuTestService;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;


@Service("stuTestService")
public class StuTestServiceImpl extends ServiceImpl<StuTestMapper, StuTestEntity> implements StuTestService {

    @Resource
    private StuTestMapper stuTestMapper;

    @Override
    public List<Map<String, Object>> getStuTestInfo(int Stu_id) throws IllegalAccessException {
        List<Map<String, Object>> StuTestList = new ArrayList<>();
        String[] DepartmentName = {"眼科", "耳鼻喉科", "口腔科", "外科", "血压脉搏科", "内科",
                    "化验科", "胸部放射科", "其它科"};
        StuTestEntity stuTestEntity = stuTestMapper.selectById(Stu_id);
        Field[] Fields = stuTestEntity.getClass().getDeclaredFields();

        for(Field field:Fields)
            field.setAccessible(true);

        for(int i = 1; i <= 9; ++i)
        {
            Map<String, Object> TempStuTestMap = new HashMap<>();
            TempStuTestMap.put("Doctor_idea", Fields[4*i-3].get(stuTestEntity));
            TempStuTestMap.put("Doctor_name", Fields[4*i-2].get(stuTestEntity));
            TempStuTestMap.put("Doctor_id", Fields[4*i-1].get(stuTestEntity));
            TempStuTestMap.put("Doctor_operation_time", Fields[4*i].get(stuTestEntity));
            TempStuTestMap.put("Doctor_department", DepartmentName[i-1]);
            StuTestList.add(TempStuTestMap);
        }

        return StuTestList;
    }
}