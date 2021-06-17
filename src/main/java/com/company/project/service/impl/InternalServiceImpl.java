package com.company.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.InternalEntity;
import com.company.project.entity.StuTestEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.DoctorMapper;
import com.company.project.mapper.InternalMapper;
import com.company.project.mapper.StuTestMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IInternalService;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 内科 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class InternalServiceImpl extends ServiceImpl<InternalMapper, InternalEntity> implements IInternalService {
    @Resource
    private InternalMapper internalMapper;

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
            sql = "select Student.*, (case when Internal_error is NULL then '0' "
                    +"when Internal_error = '1' then '2' "
                    +"else '1' end)Internal_all "
                    +"from Student left join Internal "
                    +"on Student.Stu_id = Internal.Stu_id;";
        }
        else
        {
            sql = "select s.*, (case when Internal_error is NULL then '0' "
                    +"when Internal_error = '1' then '2' "
                    +"else '1' end)Internal_all "
                    +"from (select * from Student where Stu_id = "+Stu_id+") as s "
                    +"left join Internal "
                    +"on s.Stu_id = Internal.Stu_id;";
        }
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getStuInternalInfo(int Stu_id) {
        LambdaQueryWrapper<InternalEntity> InternalQueryWrapper = Wrappers.lambdaQuery();
        InternalQueryWrapper.eq(InternalEntity::getStuId, Stu_id);
        return internalMapper.selectMaps(InternalQueryWrapper);
    }

    @Override
    public void insertStuInternalInfo(InternalEntity internalEntity) {
        StuTestEntity stuTestEntity = new StuTestEntity();
        boolean bFirstInsert;

        if( internalMapper.selectById(internalEntity.getStuId()) == null )
        {
            internalMapper.insert(internalEntity);
            bFirstInsert = true;
        }
        else
        {
            internalMapper.updateById(internalEntity);
            bFirstInsert = false;
        }

        //插入internal表的同时要把部分数据插入到StuTest表
        stuTestEntity.setStuId(internalEntity.getStuId());
        stuTestEntity.setInternalIdea(internalEntity.getInternalIdea());
        stuTestEntity.setInternalDoctorName(doctorMapper.selectById(internalEntity.getInternalDoctorId()).getDoctorName());
        stuTestEntity.setInternalDoctorId(internalEntity.getInternalDoctorId());
        stuTestEntity.setInternalOperationTime(internalEntity.getInternalOperationTime());

        StuTestEntity selectEntity = stuTestMapper.selectById(internalEntity.getStuId());

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
