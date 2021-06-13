package com.company.project.service.impl;

import org.springframework.jdbc.core.JdbcTemplate;
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

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getStuTestInfo(int Stu_id, boolean bHasManage) throws IllegalAccessException {
        List<Map<String, Object>> StuTestList = new ArrayList<>();
        String[] DepartmentName = {"眼科", "耳鼻喉科", "口腔科", "外科", "血压脉搏科", "内科",
                    "化验科", "胸部放射科", "其它科", "检查结论"};
        String[] TablesName = {"Eye", "EBH", "Tooth", "Surgery", "Blood", "Internal", "Assay", "Chest", "Other", "Manage"};

        StuTestEntity stuTestEntity = stuTestMapper.selectById(Stu_id);
        Field[] Fields = stuTestEntity.getClass().getDeclaredFields();

        int LoopMax;

        for(Field field:Fields)
            field.setAccessible(true);

        if(bHasManage)
        {
            LoopMax = 10;
        }
        else
        {
            LoopMax = 9;
        }

        for(int i = 1; i <= LoopMax; ++i)
        {
            Map<String, Object> TempStuTestMap = new HashMap<>();
            TempStuTestMap.put("Doctor_idea", Fields[4*i-3].get(stuTestEntity));
            TempStuTestMap.put("Doctor_name", Fields[4*i-2].get(stuTestEntity));
            TempStuTestMap.put("Doctor_id", Fields[4*i-1].get(stuTestEntity));
            TempStuTestMap.put("Doctor_operation_time", Fields[4*i].get(stuTestEntity));
            TempStuTestMap.put("Doctor_department", DepartmentName[i-1]);

            String sql = "select "+(TablesName[i-1]+"_error")+" from "+TablesName[i-1] +" where Stu_id = "+Stu_id;
            String Doctor_error = jdbcTemplate.queryForObject(sql, String.class);

            TempStuTestMap.put("Doctor_error", Doctor_error);
            TempStuTestMap.put("Table_index", i-1);

            StuTestList.add(TempStuTestMap);
        }

        return StuTestList;
    }
}