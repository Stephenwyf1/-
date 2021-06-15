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
import java.util.HashMap;
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
        String sql;
        if(Stu_id == -1)
        {
            sql = "SELECT sts.*, (CASE when Boss_error IS NULL then '0'"
                    +"when Boss_error = '1' then '2'"
                    +"else '1' END) Boss_all "
                    +"FROM"
                    +"("
                    +"SELECT s.* FROM"
                    +"(SELECT * from stu_test WHERE Stu_test_count >= 10) as st "
                    +"LEFT JOIN "
                    +"student as s "
                    +"ON s.Stu_id = st.Stu_id"
                    +")as sts "
                    +"LEFT JOIN "
                    +"Boss as b "
                    +"ON sts.Stu_id = b.Stu_id";
        }
        else
        {
            sql = "SELECT sts.*, (CASE when Boss_error IS NULL then '0'"
                    +"when Boss_error = '1' then '2'"
                    +"else '1' END )Boss_all "
                    +"FROM"
                    +"("
                    +"SELECT s.* FROM"
                    +"(SELECT * from stu_test WHERE Stu_test_count >= 10 AND Stu_id = "+Stu_id+") as st "
                    +"LEFT JOIN "
                    +"student as s "
                    +"ON s.Stu_id = st.Stu_id"
                    +")as sts "
                    +"LEFT JOIN "
                    +"Boss as b "
                    +"ON sts.Stu_id = b.Stu_id";
        }
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getStuBossInfo(int Stu_id) {
        LambdaQueryWrapper<BossEntity> BossQueryWrapper = Wrappers.lambdaQuery();
        BossQueryWrapper.eq(BossEntity::getStuId, Stu_id);

        String sql;
        String[] TablesName = {"Eye", "EBH", "Tooth", "Surgery", "Blood", "Internal", "Assay",
                "Chest", "Other", "Manage"};
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

        return bossMapper.selectMaps(BossQueryWrapper);
    }

    @Override
    public boolean insertStuBossInfo(BossEntity bossEntity) {
        if( bossMapper.selectById(bossEntity.getStuId()) == null )
        {
            bossMapper.insert(bossEntity);
        }
        else
        {
            bossMapper.updateById(bossEntity);
        }
        // 体检报告全部填写完毕
        jdbcTemplate.execute("UPDATE Student SET Stu_test_all = '1' WHERE Stu_id = "+bossEntity.getStuId());

        return true;
    }

    @Override
    public void rejectTestReport(int Stu_id, int Table_index) {
        String[] TablesName = {"Eye", "EBH", "Tooth", "Surgery", "Blood", "Internal", "Assay", "Chest", "Other", "Manage"};

        String sql = "update "+TablesName[Table_index]
                +" set "+(TablesName[Table_index]+"_error")+" = 1"+" where Stu_id = "+Stu_id;
        jdbcTemplate.execute(sql);
    }
}
