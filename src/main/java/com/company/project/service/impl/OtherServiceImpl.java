package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.OtherEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.OtherMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IOtherService;
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
    private StudentMapper studentMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {
        LambdaQueryWrapper<StudentEntity> StudentQueryWrapper = Wrappers.lambdaQuery();
        LambdaQueryWrapper<OtherEntity> OtherQueryWrapper = Wrappers.lambdaQuery();

        List<Map<String, Object>> StudentEntityMaps = studentMapper.selectMaps(StudentQueryWrapper);
        List<Map<String, Object>> OtherEntityMaps = otherMapper.selectMaps(OtherQueryWrapper);

        for(Map<String, Object> StudentEntityMap : StudentEntityMaps)
        {
            StudentEntityMap.put("Other_all", "0");
            for(Map<String, Object> OtherEntityMap : OtherEntityMaps)
            {
                if(OtherEntityMap.get("Stu_id") == StudentEntityMap.get("Stu_id"))
                {
                    StudentEntityMap.put("Other_all", "1");
                    if( Integer.parseInt( (String)OtherEntityMap.get("Other_error") ) == 1)
                    {
                        StudentEntityMap.put("Other_all", "2");
                    }
                }
            }
        }

        if(Stu_id != -1)
        {
            List<Map<String, Object>> SearchResultStudentMaps = new ArrayList<>();
            for(Map<String, Object> StudentEntityMap : StudentEntityMaps)
            {
                if( (int)StudentEntityMap.get("Stu_id") == Stu_id )
                {
                    SearchResultStudentMaps.add(StudentEntityMap);
                }
            }
            return SearchResultStudentMaps;
        }

        return StudentEntityMaps;
    }

    @Override
    public List<Map<String, Object>> getStuOtherInfo(int Stu_id) {
        LambdaQueryWrapper<OtherEntity> AssayQueryWrapper = Wrappers.lambdaQuery();
        AssayQueryWrapper.eq(OtherEntity::getStuId, Stu_id);
        return otherMapper.selectMaps(AssayQueryWrapper);
    }

    @Override
    public void insertStuOtherInfo(OtherEntity otherEntity) {
        if( otherMapper.selectById(otherEntity.getStuId()) == null )
        {
            otherMapper.insert(otherEntity);
        }
        else
        {
            otherMapper.updateById(otherEntity);
        }
    }
}
