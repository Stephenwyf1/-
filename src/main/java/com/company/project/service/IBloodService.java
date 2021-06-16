package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.BloodEntity;
import com.company.project.entity.BloodEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 血压脉搏 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IBloodService extends IService<BloodEntity> {
    //获取Blood科体检学生列表
    List<Map<String, Object>> getStuInfoList();

    //获取特定学生的Blood信息
    List<Map<String, Object>> getStuBloodInfo(int Stu_id);

    //插入学生的Blood信息(如果存在则更新)
    void insertStuBloodInfo(BloodEntity bloodEntity);

}
