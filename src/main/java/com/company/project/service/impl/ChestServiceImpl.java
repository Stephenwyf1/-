package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.ChestEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.ChestMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IChestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 胸部 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class ChestServiceImpl extends ServiceImpl<ChestMapper, ChestEntity> implements IChestService {

    @Resource
    private ChestMapper chestMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {
        LambdaQueryWrapper<StudentEntity> StudentQueryWrapper = Wrappers.lambdaQuery();
        LambdaQueryWrapper<ChestEntity> ChestQueryWrapper = Wrappers.lambdaQuery();

        List<Map<String, Object>> StudentEntityMaps = studentMapper.selectMaps(StudentQueryWrapper);
        List<Map<String, Object>> ChestEntityMaps = chestMapper.selectMaps(ChestQueryWrapper);

        for(Map<String, Object> StudentEntityMap : StudentEntityMaps)
        {
            StudentEntityMap.put("Chest_all", "0");
            for(Map<String, Object> ChestEntityMap : ChestEntityMaps)
            {
                if(ChestEntityMap.get("Stu_id") == StudentEntityMap.get("Stu_id"))
                {
                    StudentEntityMap.put("Chest_all", "1");
                    if(Integer.parseInt( (String)ChestEntityMap.get("Chest_error") ) == 1)
                    {
                        StudentEntityMap.put("Chest_all", "2");
                    }
                }
            }
        }

        if(Stu_id != -1)
        {
            List<Map<String, Object>> SearchResultStudentMaps = new ArrayList<>();
            for(Map<String, Object> StudentEntityMap : StudentEntityMaps)
            {
                if( (int)StudentEntityMap.get("Stu_id") == Stu_id)
                {
                    SearchResultStudentMaps.add(StudentEntityMap);
                }
            }
            return SearchResultStudentMaps;
        }

        return StudentEntityMaps;
    }

    @Override
    public List<Map<String, Object>> getStuChestInfo(int Stu_id) {
        LambdaQueryWrapper<ChestEntity> AssayQueryWrapper = Wrappers.lambdaQuery();
        AssayQueryWrapper.eq(ChestEntity::getStuId, Stu_id);
        return chestMapper.selectMaps(AssayQueryWrapper);
    }

    @Override
    public void insertStuChestInfo(ChestEntity chestEntity) {
        System.out.println( chestMapper.selectById(chestEntity.getStuId()) );

        if( chestMapper.selectById(chestEntity.getStuId()) == null )
        {
            chestMapper.insert(chestEntity);
        }
        else
        {
            chestMapper.updateById(chestEntity);
        }
    }
}
