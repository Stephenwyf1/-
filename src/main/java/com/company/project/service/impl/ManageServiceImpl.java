package com.company.project.service.impl;

import com.alibaba.druid.pool.ha.selector.StickyDataSourceHolder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.AssayEntity;
import com.company.project.entity.ManageEntity;
import com.company.project.entity.StuTestEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.mapper.DoctorMapper;
import com.company.project.mapper.ManageMapper;
import com.company.project.mapper.StuTestMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IManageService;
import org.apache.shiro.session.mgt.DelegatingSession;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    private ManageMapper manageMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private StuTestMapper stuTestMapper;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {
        String sql;
        if(Stu_id == -1)
        {
            sql = "SELECT sts.*, (CASE when Manage_error IS NULL then '0'"
                    +"when Manage_error = '1' then '2'"
                    +"else '1' END )Manage_all "
                    +"FROM"
                    +"("
                    +"SELECT s.* FROM"
                    +"(SELECT * from stu_test WHERE Stu_test_count >= 9) as st "
                    +"LEFT JOIN "
                    +"student as s "
                    +"ON s.Stu_id = st.Stu_id"
                    +")as sts "
                    +"LEFT JOIN "
                    +"manage as m "
                    +"ON sts.Stu_id = m.Stu_id";
        }
        else
        {
            sql = "SELECT sts.*, (CASE when Manage_error IS NULL then '0'"
                    +"when Manage_error = '1' then '2'"
                    +"else '1' END )Manage_all "
                    +"FROM"
                    +"("
                    +"SELECT s.* FROM"
                    +"(SELECT * from stu_test WHERE Stu_test_count >= 9 AND Stu_id = "+Stu_id+") as st "
                    +"LEFT JOIN "
                    +"student as s "
                    +"ON s.Stu_id = st.Stu_id"
                    +")as sts "
                    +"LEFT JOIN "
                    +"manage as m "
                    +"ON sts.Stu_id = m.Stu_id";
        }
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getStuManageInfo(int Stu_id) {
        LambdaQueryWrapper<ManageEntity> ManageQueryWrapper = Wrappers.lambdaQuery();
        ManageQueryWrapper.eq(ManageEntity::getStuId, Stu_id);

        String sql;
        String[] TablesName = {"Eye", "EBH", "Tooth", "Surgery", "Blood", "Internal", "Assay", "Chest", "Other"};
        for(int i=0;i<TablesName.length;++i)
        {
            sql = "select "+TablesName[i]+"_error"+" from "+TablesName[i]+" where Stu_id = "+Stu_id;
            if(jdbcTemplate.queryForObject(sql, String.class).equals("1"))
            {
                List<Map<String, Object>> DataList = new ArrayList<>();
                Map<String, Object> map = new HashMap<>();
                map.put("code", -1);
                map.put("message", "存在已驳回状态的表单");
                DataList.add(map);
                return DataList;
            }
        }

        return manageMapper.selectMaps(ManageQueryWrapper);
    }

    @Override
    public void insertStuManageInfo(ManageEntity manageEntity) {
        StuTestEntity stuTestEntity = new StuTestEntity();
        boolean bFirstInsert;

        if( manageMapper.selectById(manageEntity.getStuId()) == null )
        {
            manageMapper.insert(manageEntity);
            bFirstInsert = true;
        }
        else
        {
            manageMapper.updateById(manageEntity);
            bFirstInsert = false;
        }

        //插入Manage表的同时要把部分数据插入到StuTest表
        stuTestEntity.setStuId(manageEntity.getStuId());
        stuTestEntity.setManageConclusion(manageEntity.getManageConclusion());
        stuTestEntity.setManageDoctorName(doctorMapper.selectById(manageEntity.getManageDoctorId()).getDoctorName());
        stuTestEntity.setManageDoctorId(manageEntity.getManageDoctorId());
        stuTestEntity.setManageOperationTime(manageEntity.getManageOperationTime());

        StuTestEntity selectEntity = stuTestMapper.selectById(manageEntity.getStuId());

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

    @Override
    public void rejectTestReport(int Stu_id, int Table_index) {
        String[] TablesName = {"Eye", "EBH", "Tooth", "Surgery", "Blood", "Internal", "Assay", "Chest", "Other"};

        String sql = "update "+TablesName[Table_index]
                    +" set "+(TablesName[Table_index]+"_error")+" = 1"+" where Stu_id = "+Stu_id;
        jdbcTemplate.execute(sql);
    }

}
