package com.company.project.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.StudentEntity;
import com.company.project.entity.SysDictDetailEntity;

/**
 * <p>
 * 学生信息 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IStudentService extends IService<StudentEntity> {

    /**
     * 分页
     * @param page page
     * @param dictId dictId
     * @return IPage
     */
    IPage<SysDictDetailEntity> listByPage(Page<SysDictDetailEntity> page, String dictId);

}
