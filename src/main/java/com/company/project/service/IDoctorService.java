package com.company.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.DoctorEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.entity.UserAccount;

/**
 * <p>
 * 医生表 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IDoctorService extends IService<DoctorEntity> {

    /**
     * 添加医生
     * @param doc
     */
    void addDoctor(DoctorEntity doc);

    /**
     * 改变医生信息
     * @param doc
     */
    void updateDoctor(DoctorEntity doc);

    /**
     * 删除医生信息
     * @param docId
     */
    void deleteDoctor(int docId);

    /**
     * 分页
     * @param vo vo
     * @return IPage
     */
    IPage<DoctorEntity> pageInfo(DoctorEntity vo);
}
