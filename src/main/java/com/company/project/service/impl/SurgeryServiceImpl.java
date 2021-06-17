package com.company.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.StuTestEntity;
import com.company.project.entity.SurgeryEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.DoctorMapper;
import com.company.project.mapper.StuTestMapper;
import com.company.project.mapper.SurgeryMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.ISurgeryService;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 外科 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class SurgeryServiceImpl extends ServiceImpl<SurgeryMapper, SurgeryEntity> implements ISurgeryService {
    @Resource
    private SurgeryMapper surgeryMapper;

    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private StuTestMapper stuTestMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {
        String sql;
        if(Stu_id == -1)
        {
            sql = "select Student.*, (case when Surgery_error is NULL then '0' "
                    +"when Surgery_error = '1' then '2' "
                    +"else '1' end)Surgery_all "
                    +"from Student left join Surgery "
                    +"on Student.Stu_id = Surgery.Stu_id;";
        }
        else
        {
            sql = "select s.*, (case when Surgery_error is NULL then '0' "
                    +"when Surgery_error = '1' then '2' "
                    +"else '1' end)Surgery_all "
                    +"from (select * from Student where Stu_id = "+Stu_id+") as s "
                    +"left join Surgery "
                    +"on s.Stu_id = Surgery.Stu_id;";
        }
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getStuSurgeryInfo(int Stu_id) {
        LambdaQueryWrapper<SurgeryEntity> SurgeryQueryWrapper = Wrappers.lambdaQuery();
        SurgeryQueryWrapper.eq(SurgeryEntity::getStuId, Stu_id);
        return surgeryMapper.selectMaps(SurgeryQueryWrapper);
    }

    @Override
    public void insertStuSurgeryInfo(SurgeryEntity surgeryEntity) {
        StuTestEntity stuTestEntity = new StuTestEntity();
        boolean bFirstInsert;

        if( surgeryMapper.selectById(surgeryEntity.getStuId()) == null )
        {
            surgeryMapper.insert(surgeryEntity);
            bFirstInsert = true;
        }
        else
        {
            surgeryMapper.updateById(surgeryEntity);
            bFirstInsert = false;
        }

        //插入Surgery表的同时要把部分数据插入到StuTest表
        stuTestEntity.setStuId(surgeryEntity.getStuId());
        stuTestEntity.setSurgeryIdea(surgeryEntity.getSurgeryIdea());
        stuTestEntity.setSurgeryDoctorName(doctorMapper.selectById(surgeryEntity.getSurgeryDoctorId()).getDoctorName());
        stuTestEntity.setSurgeryDoctorId(surgeryEntity.getSurgeryDoctorId());
        stuTestEntity.setSurgeryOperationTime(surgeryEntity.getSurgeryOperationTime());

        StuTestEntity selectEntity = stuTestMapper.selectById(surgeryEntity.getStuId());

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
