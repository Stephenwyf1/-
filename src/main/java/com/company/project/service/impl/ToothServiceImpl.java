package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.ToothEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.ToothMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.mapper.ToothMapper;
import com.company.project.service.IToothService;
import org.json.JSONObject;
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
    private StudentMapper studentMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList() {
        LambdaQueryWrapper<StudentEntity> StudentQueryWrapper = Wrappers.lambdaQuery();
        LambdaQueryWrapper<ToothEntity> ToothQueryWrapper = Wrappers.lambdaQuery();
        StudentQueryWrapper.orderByAsc(StudentEntity::getStuId);
        JSONObject ResultJSON = new JSONObject();

        List<Map<String, Object>> StudentEntityMaps = studentMapper.selectMaps(StudentQueryWrapper);
        List<Map<String, Object>> ToothEntityMaps = toothMapper.selectMaps(ToothQueryWrapper);

        for(Map<String, Object> StudentEntityMap : StudentEntityMaps)
        {
            StudentEntityMap.put("Tooth_all", "0");
            for(Map<String, Object> ToothEntityMap : ToothEntityMaps)
            {
                if(ToothEntityMap.get("Stu_id") == StudentEntityMap.get("Stu_id"))
                {
                    StudentEntityMap.put("Tooth_all", "1");
                }
            }
        }

        return StudentEntityMaps;
    }

    @Override
    public List<Map<String, Object>> getStuToothInfo(int stuId) {
        LambdaQueryWrapper<ToothEntity> ToothQueryWrapper = Wrappers.lambdaQuery();
        ToothQueryWrapper.eq(ToothEntity::getStuId, stuId);
        return toothMapper.selectMaps(ToothQueryWrapper);
    }

    @Override
    public void insertStuToothInfo(ToothEntity toothEntity) {
        System.out.println( toothMapper.selectById(toothEntity.getStuId()) );

        if( toothMapper.selectById(toothEntity.getStuId()) == null )
        {
            toothMapper.insert(toothEntity);
        }
        else
        {
            toothMapper.updateById(toothEntity);
        }
    }
}
