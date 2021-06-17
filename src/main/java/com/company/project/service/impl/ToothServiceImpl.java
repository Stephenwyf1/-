package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.StuTestEntity;
import com.company.project.entity.ToothEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.*;
import com.company.project.mapper.ToothMapper;
import com.company.project.service.IToothService;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 口腔 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class ToothServiceImpl extends ServiceImpl<ToothMapper, ToothEntity> implements IToothService {
    @Resource
    private ToothMapper toothMapper;

    @Resource
    private StuTestMapper stuTestMapper;

    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {
        String sql;
        if(Stu_id == -1)
        {
            sql = "select Student.*, (case when Tooth_error is NULL then '0' "
                    +"when Tooth_error = '1' then '2' "
                    +"else '1' end)Tooth_all "
                    +"from Student left join Tooth "
                    +"on Student.Stu_id = Tooth.Stu_id;";
        }
        else
        {
            sql = "select s.*, (case when Tooth_error is NULL then '0' "
                    +"when Tooth_error = '1' then '2' "
                    +"else '1' end)Tooth_all "
                    +"from (select * from Student where Stu_id = "+Stu_id+") as s "
                    +"left join Tooth "
                    +"on s.Stu_id = Tooth.Stu_id;";
        }
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getStuToothInfo(int stuId) {
        LambdaQueryWrapper<ToothEntity> ToothQueryWrapper = Wrappers.lambdaQuery();
        ToothQueryWrapper.eq(ToothEntity::getStuId, stuId);
        return toothMapper.selectMaps(ToothQueryWrapper);
    }

    @Override
    public void insertStuToothInfo(ToothEntity toothEntity) {
        StuTestEntity stuTestEntity = new StuTestEntity();
        boolean bFirstInsert;

        if( toothMapper.selectById(toothEntity.getStuId()) == null )
        {
            toothMapper.insert(toothEntity);
            bFirstInsert = true;
        }
        else
        {
            toothMapper.updateById(toothEntity);
            bFirstInsert = false;
        }

        //插入Tooth表的同时要把部分数据插入到StuTest表
        stuTestEntity.setStuId(toothEntity.getStuId());
        stuTestEntity.setToothIdea(toothEntity.getToothIdea());
        stuTestEntity.setToothDoctorName(doctorMapper.selectById(toothEntity.getToothDoctorId()).getDoctorName());
        stuTestEntity.setToothDoctorId(toothEntity.getToothDoctorId());
        stuTestEntity.setToothOperationTime(toothEntity.getToothOperationTime());

        StuTestEntity selectEntity = stuTestMapper.selectById(toothEntity.getStuId());

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
