package com.company.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.EyeEntity;
import com.company.project.entity.StuTestEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.DoctorMapper;
import com.company.project.mapper.EyeMapper;
import com.company.project.mapper.StuTestMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IEyeService;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class EyeServiceImpl extends ServiceImpl<EyeMapper, EyeEntity> implements IEyeService {
    @Resource
    private EyeMapper eyeMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private StuTestMapper stuTestMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {
        String sql;
        if(Stu_id == -1)
        {
            sql = "select Student.*, (case when Eye_error is NULL then '0' "
                    +"when Eye_error = '1' then '2' "
                    +"else '1' end)Eye_all "
                    +"from Student left join Eye "
                    +"on Student.Stu_id = Eye.Stu_id;";
        }
        else
        {
            sql = "select s.*, (case when Eye_error is NULL then '0' "
                    +"when Eye_error = '1' then '2' "
                    +"else '1' end)Eye_all "
                    +"from (select * from Student where Stu_id = "+Stu_id+") as s "
                    +"left join Eye "
                    +"on s.Stu_id = Eye.Stu_id;";
        }
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getStuEyeInfo(int Stu_id) {
        LambdaQueryWrapper<EyeEntity> EyeQueryWrapper = Wrappers.lambdaQuery();
        EyeQueryWrapper.eq(EyeEntity::getStuId, Stu_id);
        return eyeMapper.selectMaps(EyeQueryWrapper);
    }

    @Override
    public void insertStuEyeInfo(EyeEntity eyeEntity) {
        StuTestEntity stuTestEntity = new StuTestEntity();
        boolean bFirstInsert;

        if( eyeMapper.selectById(eyeEntity.getStuId()) == null )
        {
            eyeMapper.insert(eyeEntity);
            bFirstInsert = true;
        }
        else
        {
            eyeMapper.updateById(eyeEntity);
            bFirstInsert = false;
        }

        //插入Assay表的同时要把部分数据插入到StuTest表
        stuTestEntity.setStuId(eyeEntity.getStuId());
        stuTestEntity.setAssayIdea(eyeEntity.getEyeIdea());
        stuTestEntity.setAssayDoctorName(doctorMapper.selectById(eyeEntity.getEyeDoctorId()).getDoctorName());
        stuTestEntity.setAssayDoctorId(eyeEntity.getEyeDoctorId());
        stuTestEntity.setAssayOperationTime(eyeEntity.getEyeOperationTime());

        StuTestEntity selectEntity = stuTestMapper.selectById(eyeEntity.getStuId());

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
