package com.company.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.EbhEntity;
import com.company.project.entity.StuTestEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.DoctorMapper;
import com.company.project.mapper.EbhMapper;
import com.company.project.mapper.StuTestMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IEbhService;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 耳鼻喉 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class EbhServiceImpl extends ServiceImpl<EbhMapper, EbhEntity> implements IEbhService {
    @Resource
    private EbhMapper ebhMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private StuTestMapper stuTestMapper;

    @Resource
    private DoctorMapper doctorMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {
        String sql;
        if(Stu_id == -1)
        {
            sql = "select Student.*, (case when EBH_error is NULL then '0' "
                    +"when EBH_error = '1' then '2' "
                    +"else '1' end)EBH_all "
                    +"from Student left join EBH "
                    +"on Student.Stu_id = EBH.Stu_id;";
        }
        else
        {
            sql = "select s.*, (case when EBH_error is NULL then '0' "
                    +"when EBH_error = '1' then '2' "
                    +"else '1' end)EBH_all "
                    +"from (select * from Student where Stu_id = "+Stu_id+") as s "
                    +"left join EBH "
                    +"on s.Stu_id = EBH.Stu_id;";
        }
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getStuEbhInfo(int Stu_id) {
        LambdaQueryWrapper<EbhEntity> EbhQueryWrapper = Wrappers.lambdaQuery();
        EbhQueryWrapper.eq(EbhEntity::getStuId, Stu_id);
        return ebhMapper.selectMaps(EbhQueryWrapper);
    }

    @Override
    public void insertStuEbhInfo(EbhEntity ebhEntity) {
        StuTestEntity stuTestEntity = new StuTestEntity();
        boolean bFirstInsert;

        if( ebhMapper.selectById(ebhEntity.getStuId()) == null )
        {
            ebhMapper.insert(ebhEntity);
            bFirstInsert = true;
        }
        else
        {
            ebhMapper.updateById(ebhEntity);
            bFirstInsert = false;
        }

        //插入EBH表的同时要把部分数据插入到StuTest表
        stuTestEntity.setStuId(ebhEntity.getStuId());
        stuTestEntity.setEBHIdea(ebhEntity.getEbhIdea());
        stuTestEntity.setEBHDoctorName(doctorMapper.selectById(ebhEntity.getEbhDoctorId()).getDoctorName());
        stuTestEntity.setEBHDoctorId(ebhEntity.getEbhDoctorId());
        stuTestEntity.setEBHOperationTime(ebhEntity.getEbhOperationTime());

        StuTestEntity selectEntity = stuTestMapper.selectById(ebhEntity.getStuId());

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
