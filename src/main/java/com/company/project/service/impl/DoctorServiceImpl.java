package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.common.exception.BusinessException;
import com.company.project.common.utils.PasswordUtils;
import com.company.project.entity.DoctorEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.entity.SysUserRole;
import com.company.project.entity.UserAccount;
import com.company.project.mapper.DoctorMapper;
import com.company.project.mapper.StudentMapper;
import com.company.project.mapper.SysUserRoleMapper;
import com.company.project.mapper.UserAccountMapper;
import com.company.project.service.HttpSessionService;
import com.company.project.service.IDoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.service.IUserAccountService;
import com.company.project.service.UserRoleService;
import com.company.project.vo.req.UserRoleOperationReqVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private UserAccountMapper userAccountMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private IUserAccountService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private HttpSessionService httpSessionService;

    @Override
    public void addDoctor(DoctorEntity doc) {
        DoctorEntity DoctorOne = doctorMapper.selectOne(Wrappers.<DoctorEntity>lambdaQuery().eq(DoctorEntity::getDoctorCard, doc.getDoctorCard()));
        if (DoctorOne != null) {
            throw new BusinessException("用户已存在，请勿重复添加！");
        }
        doctorMapper.insert(doc);
        updateDoctorAccount(doc);

    }

    @Override
    public void updateDoctor(DoctorEntity doc) {
        DoctorEntity DoctorOne = doctorMapper.selectById(doc.getDoctorId());
        if(DoctorOne==null){
            throw new BusinessException("用户不存在");
        }
        doctorMapper.updateById(doc);
        updateDoctorAccount(doc);
    }

    @Override
    public void deleteDoctor(int docId) {
        DoctorEntity doc=doctorMapper.selectById(docId);
        String name=doc.getDoctorCard().substring(doc.getDoctorCard().length()-6,doc.getDoctorCard().length());
        UserAccount user= userAccountMapper.selectOne(Wrappers.<UserAccount>lambdaQuery().eq(UserAccount::getUsername, name));
        if(user==null){
            return;
        }
        sysUserRoleMapper.delete(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, user.getUserId()));
        List<String> userIds = new ArrayList();
        userIds.add(user.getUserId().toString());
        httpSessionService.abortUserByUserIds(userIds);
        LambdaQueryWrapper<UserAccount> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(UserAccount::getUserId, userIds);
        userService.remove(queryWrapper);
        doctorMapper.deleteById(doc.getDoctorId());
    }

    @Override
    public IPage<DoctorEntity> pageInfo(DoctorEntity vo) {
        Page page = new Page(vo.getPage(), vo.getLimit());
        LambdaQueryWrapper<DoctorEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(vo.getDoctorName())) {
            queryWrapper.like(DoctorEntity::getDoctorName, vo.getDoctorName());
        }
        if (!StringUtils.isEmpty(vo.getDoctorCard())) {
            queryWrapper.like(DoctorEntity::getDoctorCard, vo.getDoctorCard());
        }
        if (!StringUtils.isEmpty(vo.getDoctorDepartment())) {
            queryWrapper.like(DoctorEntity::getDoctorDepartment, vo.getDoctorDepartment());
        }
        IPage<DoctorEntity> iPage = doctorMapper.selectPage(page, queryWrapper);

        return iPage;

    }

    /**
     * 据医生信息创建或更新账户,当医生修改科室信息时更新权限
     * 暂定医生账号为身份证号后六位，密码为123456，roleid与usertype同名，在医生录入信息后创建账户并分配权限
     * @param doctor
     */
    private void updateDoctorAccount(DoctorEntity doctor){
        UserAccount user=new UserAccount();
        user.setUsername(doctor.getDoctorCard().substring(doctor.getDoctorCard().length() - 6, doctor.getDoctorCard().length()));
        UserAccount userOne=userAccountMapper.selectOne(Wrappers.<UserAccount>lambdaQuery().eq(UserAccount::getUsername, user.getUsername()));
        if(userOne==null) {
            user.setPassword("123456");
        }
        else{
            user.setUserId(userOne.getUserId());
            user.setPassword(userOne.getPassword());
        }
        switch(doctor.getDoctorDepartment()) {
            case "眼科":
                user.setUserType(11);
                break;
            case "耳鼻喉科":
                user.setUserType(12);
                break;
            case "口腔科":
                user.setUserType(13);
                break;
            case "外科":
                user.setUserType(14);
                break;
            case "血压脉搏科":
                user.setUserType(15);
                break;
            case "内科":
                user.setUserType(16);
                break;
            case "化验科":
                user.setUserType(17);
                break;
            case "胸部放射科":
                user.setUserType(18);
                break;
            case "其他":
                user.setUserType(19);
                break;
            case "体检负责":
                user.setUserType(20);
                break;
            case "体检负责医师":
                user.setUserType(21);
                break;
            case "医院领导":
                user.setUserType(22);
                break;
            default:
                throw new BusinessException("医生科室不存在");
        }
        if(userOne==null){
            user.setSalt(PasswordUtils.getSalt());
            String encode = PasswordUtils.encode(user.getPassword(), user.getSalt());
            user.setPassword(encode);
            userAccountMapper.insert(user);
        }
        else{
            userService.updateUserInfo(user);
        }
        updateDoctorRole(user);
    }

    /**
     * 根据医生账号修改医生权限，若有则删除后更新，若无则插入
     * @param user
     */
    private void updateDoctorRole(UserAccount user){
        SysUserRole role=new SysUserRole();
        role.setRoleId(user.getUserType().toString());
        role.setUserId(user.getUserId().toString());
        int count=sysUserRoleMapper.selectCount(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, role.getUserId()));
        if(count>1) {
            sysUserRoleMapper.delete(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, role.getUserId()));
            sysUserRoleMapper.insert(role);
        }
        else if(count==1){
            role.setId(sysUserRoleMapper.selectOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, role.getUserId())).getId());
            sysUserRoleMapper.updateById(role);
        }
        else {
            sysUserRoleMapper.insert(role);
        }
        httpSessionService.refreshUerId(user.getUserId().toString());
    }
}
