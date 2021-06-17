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
    private StudentMapper studentMapper;

    @Resource
    private StuTestMapper stuTestMapper;

    @Resource
    private DoctorMapper doctorMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList() {
    LambdaQueryWrapper<StudentEntity> StudentQueryWrapper = Wrappers.lambdaQuery();
    LambdaQueryWrapper<EbhEntity> EbhQueryWrapper = Wrappers.lambdaQuery();
        StudentQueryWrapper.orderByAsc(StudentEntity::getStuId);
    JSONObject ResultJSON = new JSONObject();

    List<Map<String, Object>> StudentEntityMaps = studentMapper.selectMaps(StudentQueryWrapper);
    List<Map<String, Object>> EbhEntityMaps = ebhMapper.selectMaps(EbhQueryWrapper);

        for(Map<String, Object> StudentEntityMap : StudentEntityMaps)
    {
        StudentEntityMap.put("Ebh_all", "0");
        for(Map<String, Object> EbhEntityMap : EbhEntityMaps)
        {
            if(EbhEntityMap.get("Stu_id") == StudentEntityMap.get("Stu_id"))
            {
                StudentEntityMap.put("Ebh_all", "1");
            }
            //set ebh_all=2 驳回

        }
    }

        return StudentEntityMaps;
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

        //插入Assay表的同时要把部分数据插入到StuTest表
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
