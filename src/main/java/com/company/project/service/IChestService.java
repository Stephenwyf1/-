package com.company.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.ChestEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 胸部 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IChestService extends IService<ChestEntity> {

    //获取Chest科体检学生列表
    List<Map<String, Object>> getStuInfoList(int Stu_id);

    //获取特定学生的Chest信息
    List<Map<String, Object>> getStuChestInfo(int Stu_id);

    //插入学生的Chest信息（如果存在则更新）
    void insertStuChestInfo(ChestEntity chestEntity);

}
