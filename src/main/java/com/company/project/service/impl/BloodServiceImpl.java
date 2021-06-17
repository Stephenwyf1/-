package com.company.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.BloodEntity;
import com.company.project.entity.StuTestEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.BloodMapper;
import com.company.project.mapper.DoctorMapper;
import com.company.project.mapper.StuTestMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IBloodService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 血压脉搏 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class BloodServiceImpl extends ServiceImpl<BloodMapper, BloodEntity> implements IBloodService {
    @Resource
    private BloodMapper bloodMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private StuTestMapper stuTestMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList() {
    LambdaQueryWrapper<StudentEntity> StudentQueryWrapper = Wrappers.lambdaQuery();
    LambdaQueryWrapper<BloodEntity> BloodQueryWrapper = Wrappers.lambdaQuery();
        StudentQueryWrapper.orderByAsc(StudentEntity::getStuId);
    JSONObject ResultJSON = new JSONObject();

    List<Map<String, Object>> StudentEntityMaps = studentMapper.selectMaps(StudentQueryWrapper);
    List<Map<String, Object>> BloodEntityMaps = bloodMapper.selectMaps(BloodQueryWrapper);

        for(Map<String, Object> StudentEntityMap : StudentEntityMaps)
    {
        StudentEntityMap.put("Blood_all", "0");
        for(Map<String, Object> BloodEntityMap : BloodEntityMaps)
        {
            if(BloodEntityMap.get("Stu_id") == StudentEntityMap.get("Stu_id"))
            {
                StudentEntityMap.put("Blood_all", "1");
            }
            //set blood_all=2 驳回

        }
    }

        return StudentEntityMaps;
}

    @Override
    public List<Map<String, Object>> getStuBloodInfo(int Stu_id) {
        LambdaQueryWrapper<BloodEntity> BloodQueryWrapper = Wrappers.lambdaQuery();
        BloodQueryWrapper.eq(BloodEntity::getStuId, Stu_id);
        return bloodMapper.selectMaps(BloodQueryWrapper);
    }

    @Override
    public void insertStuBloodInfo(BloodEntity bloodEntity) {
        StuTestEntity stuTestEntity = new StuTestEntity();
        boolean bFirstInsert;


        System.out.println( bloodMapper.selectById(bloodEntity.getStuId()) );

        if( bloodMapper.selectById(bloodEntity.getStuId()) == null )
        {
            bloodMapper.insert(bloodEntity);
            bFirstInsert = true;
        }
        else
        {
            bloodMapper.updateById(bloodEntity);
            bFirstInsert = false;
        }

        //插入Blood表的同时要把部分数据插入到StuTest表
        stuTestEntity.setStuId(bloodEntity.getStuId());
        stuTestEntity.setBloodIdea(bloodEntity.getBloodIdea());
        stuTestEntity.setBloodDoctorName(doctorMapper.selectById(bloodEntity.getBloodDoctorId()).getDoctorName());
        stuTestEntity.setBloodDoctorId(bloodEntity.getBloodDoctorId());
        stuTestEntity.setBloodOperationTime(bloodEntity.getBloodOperationTime());

        StuTestEntity selectEntity = stuTestMapper.selectById(bloodEntity.getStuId());

        if(selectEntity == null)//if StuTest 没有数据
        {
            stuTestEntity.setStuTestCount(1);
            stuTestMapper.insert(stuTestEntity);
        }
        else
        {
            if(bFirstInsert)//if first insert Entity then StuTestCount + 1
            {
                stuTestEntity.setStuTestCount(selectEntity.getStuTestCount() + 1);
            }
            stuTestMapper.updateById(stuTestEntity);
        }

    }

}
