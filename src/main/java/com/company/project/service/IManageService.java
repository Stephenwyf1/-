package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.AssayEntity;
import com.company.project.entity.ManageEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 体检负责医生 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IManageService extends IService<ManageEntity> {

    //获取"所有前置体检项已检查完毕但检查结论未签字"的体检报告形成列表
    List<Map<String, Object>> getStuInfoList(int Stu_id);

    //获取特定学生的Assay信息
    List<Map<String, Object>> getStuManageInfo(int Stu_id);

    //插入学生的Assay信息(如果存在则更新)
    void insertStuManageInfo(ManageEntity manageEntity);

}
