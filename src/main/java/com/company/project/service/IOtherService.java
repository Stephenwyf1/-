package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.AssayEntity;
import com.company.project.entity.OtherEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 其他检查 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IOtherService extends IService<OtherEntity> {

    //获取Assay科体检学生列表
    List<Map<String, Object>> getStuInfoList(int Stu_id);

    //获取特定学生的Assay信息
    List<Map<String, Object>> getStuOtherInfo(int Stu_id);

    //插入学生的Assay信息(如果存在则更新)
    void insertStuOtherInfo(OtherEntity otherEntity);

}
