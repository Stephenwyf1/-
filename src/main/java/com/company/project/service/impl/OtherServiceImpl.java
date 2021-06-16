package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.OtherEntity;
import com.company.project.entity.StuTestEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.DoctorMapper;
import com.company.project.mapper.OtherMapper;
import com.company.project.mapper.StuTestMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IOtherService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 其他检查 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class OtherServiceImpl extends ServiceImpl<OtherMapper, OtherEntity> implements IOtherService {

    @Resource
    private OtherMapper otherMapper;

    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private StuTestMapper stuTestMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {
        String sql;
        if(Stu_id == -1)
        {
            sql = "select Student.*, (case when Other_error is NULL then '0' "
                    +"when Other_error = '1' then '2' "
                    +"else '1' end)Other_all "
                    +"from Student left join Other "
                    +"on Student.Stu_id = Other.Stu_id;";
        }
        else
        {
            sql = "select s.*, (case when Other_error is NULL then '0' "
                    +"when Other_error = '1' then '2' "
                    +"else '1' end)Other_all "
                    +"from (select * from Student where Stu_id = "+Stu_id+") as s "
                    +"left join Other "
                    +"on s.Stu_id = Other.Stu_id;";
        }
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getStuOtherInfo(int Stu_id) {
        LambdaQueryWrapper<OtherEntity> AssayQueryWrapper = Wrappers.lambdaQuery();
        AssayQueryWrapper.eq(OtherEntity::getStuId, Stu_id);
        return otherMapper.selectMaps(AssayQueryWrapper);
    }

    @Override
    public void insertStuOtherInfo(OtherEntity otherEntity) {
        StuTestEntity stuTestEntity = new StuTestEntity();
        boolean bFirstInsert;

        if( otherMapper.selectById(otherEntity.getStuId()) == null )
        {
            otherMapper.insert(otherEntity);
            bFirstInsert = true;
        }
        else
        {
            otherMapper.updateById(otherEntity);
            bFirstInsert = false;
        }

        //插入Other表的同时要把部分数据插入到StuTest表
        stuTestEntity.setStuId(otherEntity.getStuId());
        stuTestEntity.setOtherIdea(otherEntity.getOtherIdea());
        stuTestEntity.setOtherDoctorName(doctorMapper.selectById(otherEntity.getOtherDoctorId()).getDoctorName());
        stuTestEntity.setOtherDoctorId(otherEntity.getOtherDoctorId());
        stuTestEntity.setOtherOperationTime(otherEntity.getOtherOperationTime());

        StuTestEntity selectEntity = stuTestMapper.selectById(otherEntity.getStuId());

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
