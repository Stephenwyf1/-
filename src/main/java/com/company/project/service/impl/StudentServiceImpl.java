package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.*;
import com.company.project.mapper.StudentMapper;
import com.company.project.service.IStudentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

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
    public IPage<SysDictDetailEntity> listByPage(Page<SysDictDetailEntity> page, String dictId) {

////        SysDictEntity sysDictEntity = sysDictMapper.selectById(dictId);
////        if (sysDictEntity == null) {
////            throw new BusinessException("获取字典数据失败!");
////        }
//
//        LambdaQueryWrapper<StudentEntity> wrapper = Wrappers.lambdaQuery();
////        wrapper.eq(SysDictDetailEntity::getDictId, dictId);
//        wrapper.orderByAsc(StudentEntity::getStuId);
////        wrapper.orderByAsc(SysDictDetailEntity::getSort);
//        IPage<StudentEntity> result = studentMapper.selectPage(page, wrapper);
////        IPage<SysDictDetailEntity> result = sysDictDetailMapper.selectPage(page, wrapper);
//        if (!CollectionUtils.isEmpty(result.getRecords())) {
//            result.getRecords().parallelStream().forEach(entity -> entity.setDictName(sysDictEntity.getName()));
//        }
//        return result;
        return null;
    }
}
