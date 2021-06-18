package com.company.project.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.StudentEntity;

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


    /**
     * 添加学生
     * @param stu
     */
    void addStudent(StudentEntity stu);

    /**
     * 改变学生信息
     * @param stu
     */
    void updateStudent(StudentEntity stu);

    void deleteStudent(int StuId);

    IPage<StudentEntity> pageInfo(StudentEntity vo);

    int getStuCount();

    int getTestedStuCount();

}