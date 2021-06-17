package com.company.project.service;

import com.company.project.entity.EbhEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 耳鼻喉科体检信息 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IEbhService extends IService<EbhEntity> {
    //获取Ebh科体检学生列表
    List<Map<String, Object>> getStuInfoList(int Stu_id);

    //获取特定学生的Ebh信息
    List<Map<String, Object>> getStuEbhInfo(int stuId);

    //插入学生的Ebh信息(如果存在则更新)
    void insertStuEbhInfo(EbhEntity ebhEntity);
}
