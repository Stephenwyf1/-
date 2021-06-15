package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.BossEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 医院领导信息录入 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IBossService extends IService<BossEntity> {

    //获取"所有前置体检项已检查完毕但检查结论未签字"的体检报告形成列表
    List<Map<String, Object>> getStuInfoList(int Stu_id);

    //获取特定学生的Boss填写信息
    List<Map<String, Object>> getStuBossInfo(int Stu_id);

    //插入学生的Boss信息(如果存在则更新)
    boolean insertStuBossInfo(BossEntity bossEntity);

    void rejectTestReport(int Stu_id, int Table_index);

}
