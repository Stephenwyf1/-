package com.company.project.service;

import com.company.project.entity.InternalEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 内科医生 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IInternalService extends IService<InternalEntity> {
    //获取Internal科体检学生列表
    List<Map<String, Object>> getStuInfoList();

    //获取特定学生的Internal信息
    List<Map<String, Object>> getStuInternalInfo(int stuId);

    //插入学生的Internal信息(如果存在则更新)
    void insertStuInternalInfo(InternalEntity internalEntity);
}
