package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.AssayEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.AssayMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IAssayService;
import org.json.JSONObject;
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
    private StudentMapper studentMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList() {

        LambdaQueryWrapper<StudentEntity> StudentQueryWrapper = Wrappers.lambdaQuery();
        LambdaQueryWrapper<AssayEntity> AssayQueryWrapper = Wrappers.lambdaQuery();
        StudentQueryWrapper.orderByAsc(StudentEntity::getStuId);
        JSONObject ResultJSON = new JSONObject();

        List<Map<String, Object>> StudentEntityMaps = studentMapper.selectMaps(StudentQueryWrapper);
        List<Map<String, Object>> AssayEntityMaps = assayMapper.selectMaps(AssayQueryWrapper);

        for(Map<String, Object> StudentEntityMap : StudentEntityMaps)
        {
            StudentEntityMap.put("Assay_all", "0");
            for(Map<String, Object> AssayEntityMap : AssayEntityMaps)
            {
                if(AssayEntityMap.get("Stu_id") == StudentEntityMap.get("Stu_id"))
                {
                    StudentEntityMap.put("Assay_all", "1");
                }
            }
        }

        return StudentEntityMaps;
    }

    @Override
    public List<Map<String, Object>> getStuAssayInfo(int Stu_id) {

        LambdaQueryWrapper<AssayEntity> AssayQueryWrapper = Wrappers.lambdaQuery();
        AssayQueryWrapper.eq(AssayEntity::getStuId, Stu_id);
        return assayMapper.selectMaps(AssayQueryWrapper);
    }

    @Override
    public void insertStuAssayInfo(AssayEntity assayEntity) {
        assayMapper.insert(assayEntity);
    }

}
