package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.common.exception.BusinessException;
import com.company.project.common.utils.PasswordUtils;
import com.company.project.entity.*;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IStudentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 学生信息 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service("StudentService")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements IStudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public void addStudent(StudentEntity stu) {
        StudentEntity StuOne = studentMapper.selectOne(Wrappers.<StudentEntity>lambdaQuery().eq(StudentEntity::getStuCard, stu.getStuCard()));
        if (StuOne != null) {
            throw new BusinessException("用户已存在，请勿重复添加！");
        }
        studentMapper.insert(stu);
    }

    @Override
    public void updateStudent(StudentEntity stu) {
        StudentEntity StuOne = studentMapper.selectById(stu.getStuId());
        if(StuOne==null){
            throw new BusinessException("用户不存在");
        }
        studentMapper.updateById(stu);
    }

    @Override
    public void deleteStudent(int StuId) {

        System.out.println("\n\n\n\n\n\n\n"+StuId+"\n\n\n\n\n\n\n");
        studentMapper.deleteById(StuId);
    }

    @Override
    public IPage<StudentEntity> pageInfo(StudentEntity vo) {
        Page page = new Page(vo.getPage(), vo.getLimit());
        LambdaQueryWrapper<StudentEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(vo.getStuName())) {
            queryWrapper.like(StudentEntity::getStuName, vo.getStuName());
        }
        if (!StringUtils.isEmpty(vo.getStuCollege())) {
            queryWrapper.like(StudentEntity::getStuCollege, vo.getStuCollege());
        }
        if (!StringUtils.isEmpty(vo.getStuAge())) {
            queryWrapper.like(StudentEntity::getStuAge, vo.getStuAge());
        }
        if (!StringUtils.isEmpty(vo.getStuAnamnesis())) {
            queryWrapper.like(StudentEntity::getStuAnamnesis, vo.getStuAnamnesis());
        }
        if (!StringUtils.isEmpty(vo.getStuBirth())) {
            queryWrapper.like(StudentEntity::getStuBirth, vo.getStuBirth());
        }
        if (!StringUtils.isEmpty(vo.getStuCard())) {
            queryWrapper.like(StudentEntity::getStuCard, vo.getStuCard());
        }
        if (!StringUtils.isEmpty(vo.getStuClass())) {
            queryWrapper.like(StudentEntity::getStuClass, vo.getStuClass());
        }
        if (!StringUtils.isEmpty(vo.getStuEducation())) {
            queryWrapper.like(StudentEntity::getStuEducation, vo.getStuEducation());
        }
        if (!StringUtils.isEmpty(vo.getStuGraduateJob())) {
            queryWrapper.like(StudentEntity::getStuGraduateJob, vo.getStuGraduateJob());
        }
        if (!StringUtils.isEmpty(vo.getStuMajor())) {
            queryWrapper.like(StudentEntity::getStuMajor, vo.getStuMajor());
        }
        if (!StringUtils.isEmpty(vo.getStuNum())) {
            queryWrapper.like(StudentEntity::getStuNum, vo.getStuNum());
        }
        if (!StringUtils.isEmpty(vo.getStuSex())) {
            queryWrapper.like(StudentEntity::getStuSex, vo.getStuSex());
        }
        if (!StringUtils.isEmpty(vo.getStuNation())) {
            queryWrapper.like(StudentEntity::getStuNation, vo.getStuNation());
        }
        if (!StringUtils.isEmpty(vo.getStuNative())) {
            queryWrapper.like(StudentEntity::getStuNative, vo.getStuNative());
        }
        if (!StringUtils.isEmpty(vo.getStuProfession())) {
            queryWrapper.like(StudentEntity::getStuProfession, vo.getStuProfession());
        }
        if (!StringUtils.isEmpty(vo.getStuLocation())) {
            queryWrapper.like(StudentEntity::getStuLocation, vo.getStuLocation());
        }
        IPage<StudentEntity> iPage = studentMapper.selectPage(page, queryWrapper);

        return iPage;
    }


}
