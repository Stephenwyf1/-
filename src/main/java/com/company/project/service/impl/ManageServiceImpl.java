package com.company.project.service.impl;

import com.alibaba.druid.pool.ha.selector.StickyDataSourceHolder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.AssayEntity;
import com.company.project.entity.ManageEntity;
import com.company.project.entity.StuTestEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.ManageMapper;
import com.company.project.mapper.StuTestMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IManageService;
import org.apache.shiro.session.mgt.DelegatingSession;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {

        String sql =
            "select Student.*, (case when Manage.Manage_error = 1 then 2 else Manage_all end)Manage_all, Stu_test.Stu_test_count"
            + " from Student, Manage, Stu_test"
            + " where Student.Stu_id = Manage.Stu_id and Manage.Stu_id = Stu_test.Stu_id";

        List<Map<String, Object>> QueryResultList = jdbcTemplate.queryForList(sql);

        if(Stu_id != -1)
        {
            List<Map<String, Object>> SearchResultStudentMaps = new ArrayList<>();
            for(Map<String, Object> queryResultMap : QueryResultList)
            {
                if( (int)queryResultMap.get("Stu_id") == Stu_id)
                {
                    SearchResultStudentMaps.add(queryResultMap);
                    break;
                }
            }
            return SearchResultStudentMaps;
        }

        return QueryResultList;
    }

    @Override
    public List<Map<String, Object>> getStuManageInfo(int Stu_id) {
        LambdaQueryWrapper<ManageEntity> ManageQueryWrapper = Wrappers.lambdaQuery();
        ManageQueryWrapper.eq(ManageEntity::getStuId, Stu_id);
        return manageMapper.selectMaps(ManageQueryWrapper);
    }

    @Override
    public void insertStuManageInfo(ManageEntity manageEntity) {
        if( manageMapper.selectById(manageEntity.getStuId()) == null )
        {
            manageMapper.insert(manageEntity);
        }
        else
        {
            manageMapper.updateById(manageEntity);
        }
    }

    @Override
    public void rejectTestReport(int Stu_id, int Table_index) {
        String[] TablesName = {"Eye", "EBH", "Tooth", "Surgery", "Blood", "Internal", "Assay", "Chest", "Other"};

        String sql = "update "+TablesName[Table_index]
                    +" set "+(TablesName[Table_index]+"_error")+" = 1"+" where Stu_id = "+Stu_id;
        jdbcTemplate.execute(sql);
    }

}
