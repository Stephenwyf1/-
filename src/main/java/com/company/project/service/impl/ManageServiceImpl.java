package com.company.project.service.impl;

import com.alibaba.druid.pool.ha.selector.StickyDataSourceHolder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.ManageEntity;
import com.company.project.entity.StuTestEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.ManageMapper;
import com.company.project.mapper.StuTestMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IManageService;
import org.apache.shiro.session.mgt.DelegatingSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 体检负责医生 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class ManageServiceImpl extends ServiceImpl<ManageMapper, ManageEntity> implements IManageService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ManageMapper manageMapper;

    @Resource
    private StuTestMapper stuTestMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {
        LambdaQueryWrapper<StudentEntity> StudentQueryWrapper = Wrappers.lambdaQuery();
        LambdaQueryWrapper<ManageEntity> ManageQueryWrapper = Wrappers.lambdaQuery();
        LambdaQueryWrapper<StuTestEntity> StuTestQueryWrapper = Wrappers.lambdaQuery();


        List<Map<String, Object>> StudentEntityMaps = studentMapper.selectMaps(StudentQueryWrapper);
        List<Map<String, Object>> ManageEntityMaps = manageMapper.selectMaps(ManageQueryWrapper);
        List<Map<String, Object>> StuTestEntityMaps = stuTestMapper.selectMaps(StuTestQueryWrapper);

        // 创建迭代器，便于删除元素
        Iterator<Map<String, Object>> StuItr = StudentEntityMaps.iterator();

        while (StuItr.hasNext())
        {

            Map<String, Object> StudentEntityMap = StuItr.next();

            int StudentID = (int)StudentEntityMap.get("Stu_id");

            StudentEntityMap.put("Manage_all", "0");

            // 判断Manage_all
            for(Map<String, Object> ManageEntityMap : ManageEntityMaps)
            {
                if((int)ManageEntityMap.get("Stu_id") == StudentID)
                {
                    StudentEntityMap.put("Manage_all", "1");
                    if( Integer.parseInt( (String)ManageEntityMap.get("Manage_error") ) == 1)
                    {
                        StudentEntityMap.put("Manage_all", "2");
                    }
                }
            }

            boolean bHasDone = false;

            // 计数器
            for(Map<String, Object> StuTestEntityMap : StuTestEntityMaps)
            {
                if((int)StuTestEntityMap.get("Stu_id") == StudentID && (int)StuTestEntityMap.get("Stu_test_count") == 9)
                {
                    bHasDone = true;
                }
            }

            if(!bHasDone)
            {
                StuItr.remove();
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
    public List<Map<String, Object>> getStuAssayInfo(int Stu_id) {
        return null;
    }
}
