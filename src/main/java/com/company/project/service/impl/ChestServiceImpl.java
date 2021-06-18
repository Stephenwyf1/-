package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.ChestEntity;
import com.company.project.entity.StuTestEntity;
import com.company.project.mapper.ChestMapper;
import com.company.project.mapper.DoctorMapper;
import com.company.project.mapper.StuTestMapper;
import com.company.project.service.IChestService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private DoctorMapper doctorMapper;

    @Resource
    private StuTestMapper stuTestMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {
        String sql;
        if(Stu_id == -1)
        {
            sql = "select Student.*, (case when Chest_error is NULL then '0' "
                    +"when Chest_error = '1' then '2' "
                    +"else '1' end)Chest_all "
                    +"from Student left join Chest "
                    +"on Student.Stu_id = Chest.Stu_id;";
        }
        else
        {
            sql = "select s.*, (case when Chest_error is NULL then '0' "
                    +"when Chest_error = '1' then '2' "
                    +"else '1' end)Chest_all "
                    +"from (select * from Student where Stu_id = "+Stu_id+") as s "
                    +"left join Chest "
                    +"on s.Stu_id = Chest.Stu_id;";
        }
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getStuChestInfo(int Stu_id) {
        LambdaQueryWrapper<ChestEntity> AssayQueryWrapper = Wrappers.lambdaQuery();
        AssayQueryWrapper.eq(ChestEntity::getStuId, Stu_id);
        return chestMapper.selectMaps(AssayQueryWrapper);
    }

    @Override
    public void insertStuChestInfo(ChestEntity chestEntity) {
        StuTestEntity stuTestEntity = new StuTestEntity();
        boolean bFirstInsert;

        if( chestMapper.selectById(chestEntity.getStuId()) == null )
        {
            chestMapper.insert(chestEntity);
            bFirstInsert = true;
        }
        else
        {
            chestMapper.updateById(chestEntity);
            bFirstInsert = false;
        }

        stuTestEntity.setStuId(chestEntity.getStuId());
        stuTestEntity.setChestIdea(chestEntity.getChestIdea());
        stuTestEntity.setChestDoctorName(doctorMapper.selectById(chestEntity.getChestDoctorId()).getDoctorName());
        stuTestEntity.setChestDoctorId(chestEntity.getChestDoctorId());
        stuTestEntity.setChestOperationTime(chestEntity.getChestOperationTime());

        StuTestEntity selectEntity = stuTestMapper.selectById(chestEntity.getStuId());

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
}
