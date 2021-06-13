package com.company.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.entity.DoctorEntity;
import com.company.project.mapper.DoctorMapper;
import com.company.project.service.IDoctorService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 医生表 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, DoctorEntity> implements IDoctorService {

}
