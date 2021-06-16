package com.company.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.InternalEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.InternalMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IInternalService;
import org.json.JSONObject;
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
    private StudentMapper studentMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList() {
    LambdaQueryWrapper<StudentEntity> StudentQueryWrapper = Wrappers.lambdaQuery();
    LambdaQueryWrapper<InternalEntity> InternalQueryWrapper = Wrappers.lambdaQuery();
        StudentQueryWrapper.orderByAsc(StudentEntity::getStuId);
    JSONObject ResultJSON = new JSONObject();

    List<Map<String, Object>> StudentEntityMaps = studentMapper.selectMaps(StudentQueryWrapper);
    List<Map<String, Object>> InternalEntityMaps = internalMapper.selectMaps(InternalQueryWrapper);

        for(Map<String, Object> StudentEntityMap : StudentEntityMaps)
    {
        StudentEntityMap.put("Internal_all", "0");
        for(Map<String, Object> InternalEntityMap : InternalEntityMaps)
        {
            if(InternalEntityMap.get("Stu_id") == StudentEntityMap.get("Stu_id"))
            {
                StudentEntityMap.put("Internal_all", "1");
            }
            //set internal_all=2 驳回

        }
    }

        return StudentEntityMaps;
}

    @Override
    public List<Map<String, Object>> getStuInternalInfo(int Stu_id) {
        LambdaQueryWrapper<InternalEntity> InternalQueryWrapper = Wrappers.lambdaQuery();
        InternalQueryWrapper.eq(InternalEntity::getStuId, Stu_id);
        return internalMapper.selectMaps(InternalQueryWrapper);
    }

    @Override
    public void insertStuInternalInfo(InternalEntity internalEntity) {

        System.out.println( internalMapper.selectById(internalEntity.getStuId()) );

        if( internalMapper.selectById(internalEntity.getStuId()) == null )
        {
            internalMapper.insert(internalEntity);
        }
        else
        {
            internalMapper.updateById(internalEntity);
        }
    }

}
