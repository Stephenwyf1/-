package com.company.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.SurgeryEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.SurgeryMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.ISurgeryService;
import org.json.JSONObject;
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
    private StudentMapper studentMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList() {
    LambdaQueryWrapper<StudentEntity> StudentQueryWrapper = Wrappers.lambdaQuery();
    LambdaQueryWrapper<SurgeryEntity> SurgeryQueryWrapper = Wrappers.lambdaQuery();
        StudentQueryWrapper.orderByAsc(StudentEntity::getStuId);
    JSONObject ResultJSON = new JSONObject();

    List<Map<String, Object>> StudentEntityMaps = studentMapper.selectMaps(StudentQueryWrapper);
    List<Map<String, Object>> SurgeryEntityMaps = surgeryMapper.selectMaps(SurgeryQueryWrapper);

        for(Map<String, Object> StudentEntityMap : StudentEntityMaps)
    {
        StudentEntityMap.put("Surgery_all", "0");
        for(Map<String, Object> SurgeryEntityMap : SurgeryEntityMaps)
        {
            if(SurgeryEntityMap.get("Stu_id") == StudentEntityMap.get("Stu_id"))
            {
                StudentEntityMap.put("Surgery_all", "1");
            }
            //set surgery_all=2 驳回

        }
    }

        return StudentEntityMaps;
}

    @Override
    public List<Map<String, Object>> getStuSurgeryInfo(int Stu_id) {
        LambdaQueryWrapper<SurgeryEntity> SurgeryQueryWrapper = Wrappers.lambdaQuery();
        SurgeryQueryWrapper.eq(SurgeryEntity::getStuId, Stu_id);
        return surgeryMapper.selectMaps(SurgeryQueryWrapper);
    }

    @Override
    public void insertStuSurgeryInfo(SurgeryEntity surgeryEntity) {

        System.out.println( surgeryMapper.selectById(surgeryEntity.getStuId()) );

        if( surgeryMapper.selectById(surgeryEntity.getStuId()) == null )
        {
            surgeryMapper.insert(surgeryEntity);
        }
        else
        {
            surgeryMapper.updateById(surgeryEntity);
        }
    }

}
