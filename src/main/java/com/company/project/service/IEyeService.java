package com.company.project.service;

import com.company.project.entity.EyeEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 眼科信息表 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IEyeService extends IService<EyeEntity> {
    //获取Eye科体检学生列表
    List<Map<String, Object>> getStuInfoList();

    //获取特定学生的Eye信息
    List<Map<String, Object>> getStuEyeInfo(int Stu_id);

    //插入学生的Eye信息(如果存在则更新)
    void insertStuEyeInfo(EyeEntity eyeEntity);

}
