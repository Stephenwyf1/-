package com.company.project.service;

import com.company.project.entity.SurgeryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 外科 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface ISurgeryService extends IService<SurgeryEntity> {
    //获取Surgery科体检学生列表
    List<Map<String, Object>> getStuInfoList();

    //获取特定学生的Surgery信息
    List<Map<String, Object>> getStuSurgeryInfo(int Stu_id);

    //插入学生的Surgery信息(如果存在则更新)
    void insertStuSurgeryInfo(SurgeryEntity surgeryEntity);

}
