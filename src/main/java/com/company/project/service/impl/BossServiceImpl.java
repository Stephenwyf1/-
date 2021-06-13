package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.BossEntity;
import com.company.project.entity.ManageEntity;
import com.company.project.mapper.BossMapper;
import com.company.project.service.IBossService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 医院领导信息录入 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class BossServiceImpl extends ServiceImpl<BossMapper, BossEntity> implements IBossService {

    @Resource
    private BossMapper bossMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getStuInfoList(int Stu_id) {
        String sql = "SELECT sts.*, (CASE when Boss_error IS NULL then '0'"
                +"when Boss_error = '1' then '2'"
                +"else '1' END) Boss_all "
                +"FROM"
                +"("
                +"SELECT s.* FROM"
                +"(SELECT * from stu_test WHERE Stu_test_count = 10) as st "
                +"LEFT JOIN "
                +"student as s "
                +"ON s.Stu_id = st.Stu_id"
                +")as sts "
                +"LEFT JOIN "
                +"Boss as b "
                +"ON sts.Stu_id = b.Stu_id";

        List<Map<String, Object>> QueryResultList = jdbcTemplate.queryForList(sql);

        if(Stu_id != -1)//搜索
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
    public List<Map<String, Object>> getStuBossInfo(int Stu_id) {
        LambdaQueryWrapper<BossEntity> ManageQueryWrapper = Wrappers.lambdaQuery();
        ManageQueryWrapper.eq(BossEntity::getStuId, Stu_id);
        return bossMapper.selectMaps(ManageQueryWrapper);
    }

    @Override
    public void insertStuBossInfo(BossEntity bossEntity) {
        if( bossMapper.selectById(bossEntity.getStuId()) == null )
        {
            bossMapper.insert(bossEntity);
        }
        else
        {
            bossMapper.updateById(bossEntity);
        }
    }

    @Override
    public void rejectTestReport(int Stu_id, int Table_index) {
        String[] TablesName = {"Eye", "EBH", "Tooth", "Surgery", "Blood", "Internal", "Assay", "Chest", "Other", "Manage"};

        String sql = "update "+TablesName[Table_index]
                +" set "+(TablesName[Table_index]+"_error")+" = 1"+" where Stu_id = "+Stu_id;
        jdbcTemplate.execute(sql);
    }
}
