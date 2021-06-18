package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.project.common.exception.BusinessException;
import com.company.project.common.exception.code.BaseResponseCode;
import com.company.project.common.utils.PasswordUtils;
import com.company.project.entity.SysRole;
import com.company.project.entity.UserAccount;
import com.company.project.mapper.UserAccountMapper;
import com.company.project.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.project.vo.resp.LoginRespVO;
import com.company.project.vo.resp.UserOwnRoleRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户账号表 服务实现类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements IUserAccountService {
    @Resource
    private UserAccountMapper accountMapper;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private HttpSessionService httpSessionService;

    @Value("${spring.redis.allowMultipleLogin}")
    private Boolean allowMultipleLogin;
    @Value("${spring.profiles.active}")
    private String env;

    @Override
    public void register(UserAccount sysUser) {
        UserAccount sysUserOne = accountMapper.selectOne(Wrappers.<UserAccount>lambdaQuery().eq(UserAccount::getUsername, sysUser.getUsername()));
        if (sysUserOne != null) {
            throw new BusinessException("用户名已存在！");
        }
        sysUser.setSalt(PasswordUtils.getSalt());
        String encode = PasswordUtils.encode(sysUser.getPassword(), sysUser.getSalt());
        sysUser.setPassword(encode);
        accountMapper.insert(sysUser);
    }

    @Override
    public LoginRespVO login(UserAccount vo) {
        UserAccount sysUser = accountMapper.selectOne(Wrappers.<UserAccount>lambdaQuery().eq(UserAccount::getUsername, vo.getUsername()));
        if (null == sysUser) {
            throw new BusinessException(BaseResponseCode.NOT_ACCOUNT);
        }
        if (sysUser.getStatus() == 2) {
            throw new BusinessException(BaseResponseCode.USER_LOCK);
        }
        if (!PasswordUtils.matches(sysUser.getSalt(), vo.getPassword(), sysUser.getPassword())) {
            throw new BusinessException(BaseResponseCode.PASSWORD_ERROR);
        }
        LoginRespVO respVO = new LoginRespVO();
        BeanUtils.copyProperties(sysUser, respVO);

        //是否删除之前token， 此处控制是否支持多登陆端；
        // true:允许多处登陆; false:只能单处登陆，顶掉之前登陆
        if (!allowMultipleLogin) {
            httpSessionService.abortUserById(sysUser.getUserId().toString());
        }

        String token = httpSessionService.createTokenAndUser(sysUser, roleService.getRoleNames(sysUser.getUserId().toString()), permissionService.getPermissionsByUserId(sysUser.getUserId().toString()));
        respVO.setAccessToken(token);

        return respVO;
    }

    @Override
    public void updateUserInfo(UserAccount vo) {


        UserAccount sysUser = accountMapper.selectById(vo.getUserId());
        if (null == sysUser) {
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }

        //如果用户名变更
        if (!sysUser.getUsername().equals(vo.getUsername())) {
            UserAccount sysUserOne = accountMapper.selectOne(Wrappers.<UserAccount>lambdaQuery().eq(UserAccount::getUsername, vo.getUsername()));
            if (sysUserOne != null) {
                throw new BusinessException("用户名已存在！");
            }
        }

        //如果用户名、密码、状态 变更，删除redis中用户绑定的角色跟权限
        if (!sysUser.getUsername().equals(vo.getUsername())
                || (!StringUtils.isEmpty(vo.getPassword())
                && !sysUser.getPassword().equals(PasswordUtils.encode(vo.getPassword(), sysUser.getSalt())))
                /*|| !sysUser.getStatus().equals(vo.getStatus())*/) {
            httpSessionService.abortUserById(vo.getUserId().toString());
        }

        if (!StringUtils.isEmpty(vo.getPassword())) {
            String newPassword = PasswordUtils.encode(vo.getPassword(), sysUser.getSalt());
            vo.setPassword(newPassword);
        } else {
            vo.setPassword(null);
        }
//        vo.setUserId(httpSessionService.getCurrentUserId());
        accountMapper.updateById(vo);

    }

    @Override
    public void updateUserInfoMy(UserAccount vo) {


        UserAccount user = accountMapper.selectById(httpSessionService.getCurrentUserId());
        if (null == user) {
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        if (!StringUtils.isEmpty(vo.getPassword())) {
            String newPassword = PasswordUtils.encode(vo.getPassword(), user.getSalt());
            vo.setPassword(newPassword);
        } else {
            vo.setPassword(null);
        }
        //vo.setUpdateId(httpSessionService.getCurrentUserId());
        //sysUserMapper.updateById(vo);

    }

    @Override
    public IPage<UserAccount> pageInfo(UserAccount vo) {
        Page page = new Page(vo.getPage(), vo.getLimit());
        LambdaQueryWrapper<UserAccount> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(vo.getUsername())) {
            queryWrapper.like(UserAccount::getUsername, vo.getUsername());
        }
        if (!StringUtils.isEmpty(vo.getCreateTime())) {
            queryWrapper.gt(UserAccount::getCreateTime, vo.getCreateTime());
        }

//        if (!StringUtils.isEmpty(vo.getEndTime())) {
//            queryWrapper.lt(SysUser::getCreateTime, vo.getEndTime());
//        }
//        if (!StringUtils.isEmpty(vo.getNickName())) {
//            queryWrapper.like(SysUser::getNickName, vo.getNickName());
//        }
        if (!StringUtils.isEmpty(vo.getStatus())) {
            queryWrapper.eq(UserAccount::getStatus, vo.getStatus());
        }
        if (!StringUtils.isEmpty(vo.getSex())) {
            queryWrapper.eq(UserAccount::getSex, vo.getSex());
        }
//        if (!StringUtils.isEmpty(vo.getDeptNo())) {
//            LambdaQueryWrapper<SysDept> queryWrapperDept = Wrappers.lambdaQuery();
//            queryWrapperDept.select(SysDept::getId).like(SysDept::getRelationCode, vo.getDeptNo());
//            List<Object> list = sysDeptMapper.selectObjs(queryWrapperDept);
//            queryWrapper.in(SysUser::getDeptId, list);
//        }
//        queryWrapper.orderByDesc(SysUser::getCreateTime);
        IPage<UserAccount> iPage = accountMapper.selectPage(page, queryWrapper);
//        if (!iPage.getRecords().isEmpty()) {
//            for (SysUser sysUser : iPage.getRecords()) {
//                SysDept sysDept = sysDeptMapper.selectById(sysUser.getDeptId());
//                if (sysDept != null) {
//                    sysUser.setDeptName(sysDept.getName());
//                }
//            }
//        }
        return iPage;
    }

    @Override
    public void addUser(UserAccount vo) {

        UserAccount sysUserOne = accountMapper.selectOne(Wrappers.<UserAccount>lambdaQuery().eq(UserAccount::getUsername, vo.getUsername()));
        if (sysUserOne != null) {
            throw new BusinessException("用户已存在，请勿重复添加！");
        }
        vo.setSalt(PasswordUtils.getSalt());
        String encode = PasswordUtils.encode(vo.getPassword(), vo.getSalt());
        vo.setPassword(encode);
//        vo.setStatus(1);
//        vo.setCreateWhere(1);
        accountMapper.insert(vo);
//        if (null != vo.getRoleIds() && !vo.getRoleIds().isEmpty()) {
//            UserRoleOperationReqVO reqVO = new UserRoleOperationReqVO();
//            reqVO.setUserId(vo.getId());
//            reqVO.setRoleIds(vo.getRoleIds());
//            userRoleService.addUserRoleInfo(reqVO);
//        }
    }

    @Override
    public void updatePwd(UserAccount vo) {

        UserAccount sysUser = accountMapper.selectById(vo.getUserId());
        if (sysUser == null) {
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        if ("test".equals(env) && "guest".equals(sysUser.getUsername())) {
            throw new BusinessException("演示环境禁止修改演示账号密码");
        }

        if (!PasswordUtils.matches(sysUser.getSalt(), vo.getOldPwd(), sysUser.getPassword())) {
            throw new BusinessException(BaseResponseCode.OLD_PASSWORD_ERROR);
        }
        if (sysUser.getPassword().equals(PasswordUtils.encode(vo.getNewPwd(), sysUser.getSalt()))) {
            throw new BusinessException("新密码不能与旧密码相同");
        }
        sysUser.setPassword(PasswordUtils.encode(vo.getNewPwd(), sysUser.getSalt()));
        accountMapper.updateById(sysUser);
        httpSessionService.abortAllUserByToken();

    }

    @Override
    public UserOwnRoleRespVO getUserOwnRole(String userId) {
        List<String> roleIdsByUserId = userRoleService.getRoleIdsByUserId(userId);
        List<SysRole> list = roleService.list();
        UserOwnRoleRespVO vo = new UserOwnRoleRespVO();
        vo.setAllRole(list);
        vo.setOwnRoles(roleIdsByUserId);
        return vo;
    }
}
