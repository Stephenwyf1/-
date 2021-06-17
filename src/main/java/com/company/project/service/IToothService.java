package com.company.project.service;

import com.company.project.entity.ToothEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 口腔 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IToothService extends IService<ToothEntity> {
        //获取Tooth科体检学生列表
        List<Map<String, Object>> getStuInfoList(int Stu_id);

        //获取特定学生的Tooth信息
        List<Map<String, Object>> getStuToothInfo(int stuId);

        //插入学生的Tooth信息(如果存在则更新)
        void insertStuToothInfo(ToothEntity toothEntity);
}
