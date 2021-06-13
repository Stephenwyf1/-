package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.StuTestEntity;

import java.util.List;
import java.util.Map;

/**
 * 学生体检信息表
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-06-12 09:40:44
 */
public interface StuTestService extends IService<StuTestEntity> {

    //获取特定学生的各科体检结论信息
    List<Map<String, Object>> getStuTestInfo(int Stu_id, boolean bHasManage) throws IllegalAccessException;

}

