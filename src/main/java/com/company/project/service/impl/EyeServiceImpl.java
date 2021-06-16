package com.company.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.EyeEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.EyeMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IEyeService;
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
public class EyeServiceImpl extends ServiceImpl<EyeMapper, EyeEntity> implements IEyeService {
    @Resource
    private EyeMapper eyeMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList() {
    LambdaQueryWrapper<StudentEntity> StudentQueryWrapper = Wrappers.lambdaQuery();
    LambdaQueryWrapper<EyeEntity> EyeQueryWrapper = Wrappers.lambdaQuery();
        StudentQueryWrapper.orderByAsc(StudentEntity::getStuId);
    JSONObject ResultJSON = new JSONObject();

    List<Map<String, Object>> StudentEntityMaps = studentMapper.selectMaps(StudentQueryWrapper);
    List<Map<String, Object>> EyeEntityMaps = eyeMapper.selectMaps(EyeQueryWrapper);

        for(Map<String, Object> StudentEntityMap : StudentEntityMaps)
    {
        StudentEntityMap.put("Eye_all", "0");
        for(Map<String, Object> EyeEntityMap : EyeEntityMaps)
        {
            if(EyeEntityMap.get("Stu_id") == StudentEntityMap.get("Stu_id"))
            {
                StudentEntityMap.put("Eye_all", "1");
            }
            //set eye_all=2 驳回

        }
    }

        return StudentEntityMaps;
}

    @Override
    public List<Map<String, Object>> getStuEyeInfo(int Stu_id) {
        LambdaQueryWrapper<EyeEntity> EyeQueryWrapper = Wrappers.lambdaQuery();
        EyeQueryWrapper.eq(EyeEntity::getStuId, Stu_id);
        return eyeMapper.selectMaps(EyeQueryWrapper);
    }

    @Override
    public void insertStuEyeInfo(EyeEntity eyeEntity) {

        System.out.println( eyeMapper.selectById(eyeEntity.getStuId()) );

        if( eyeMapper.selectById(eyeEntity.getStuId()) == null )
        {
            eyeMapper.insert(eyeEntity);
        }
        else
        {
            eyeMapper.updateById(eyeEntity);
        }
    }

}
