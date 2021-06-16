package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.AssayEntity;
import com.company.project.entity.StuTestEntity;
import com.company.project.mapper.AssayMapper;
import com.company.project.mapper.DoctorMapper;
import com.company.project.mapper.StuTestMapper;
import com.company.project.service.IAssayService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 化验 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class AssayServiceImpl extends ServiceImpl<AssayMapper, AssayEntity> implements IAssayService {

    @Resource
    private AssayMapper assayMapper;

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
            sql = "select Student.*, (case when Assay_error is NULL then '0' "
                                         +"when Assay_error = '1' then '2' "
                                         +"else '1' end)Assay_all "
                    +"from Student left join Assay "
                    +"on Student.Stu_id = Assay.Stu_id;";
        }
        else
        {
            sql = "select s.*, (case when Assay_error is NULL then '0' "
                                         +"when Assay_error = '1' then '2' "
                                         +"else '1' end)Assay_all "
                    +"from (select * from Student where Stu_id = "+Stu_id+") as s "
                    +"left join Assay "
                    +"on s.Stu_id = Assay.Stu_id;";
        }
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getStuAssayInfo(int Stu_id) {
        LambdaQueryWrapper<AssayEntity> AssayQueryWrapper = Wrappers.lambdaQuery();
        AssayQueryWrapper.eq(AssayEntity::getStuId, Stu_id);
        return assayMapper.selectMaps(AssayQueryWrapper);
    }

    @Override
    public void insertStuAssayInfo(AssayEntity assayEntity) {
        StuTestEntity stuTestEntity = new StuTestEntity();
        boolean bFirstInsert;

        if( assayMapper.selectById(assayEntity.getStuId()) == null )
        {
            assayMapper.insert(assayEntity);
            bFirstInsert = true;
        }
        else
        {
            assayMapper.updateById(assayEntity);
            bFirstInsert = false;
        }

        //插入Assay表的同时要把部分数据插入到StuTest表
        stuTestEntity.setStuId(assayEntity.getStuId());
        stuTestEntity.setAssayIdea(assayEntity.getAssayIdea());
        stuTestEntity.setAssayDoctorName(doctorMapper.selectById(assayEntity.getAssayDoctorId()).getDoctorName());
        stuTestEntity.setAssayDoctorId(assayEntity.getAssayDoctorId());
        stuTestEntity.setAssayOperationTime(assayEntity.getAssayOperationTime());

        StuTestEntity selectEntity = stuTestMapper.selectById(assayEntity.getStuId());

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
