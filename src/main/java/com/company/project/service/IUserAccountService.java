package com.company.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.company.project.entity.UserAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.vo.resp.LoginRespVO;
import com.company.project.vo.resp.UserOwnRoleRespVO;

/**
 * <p>
 * 用户账号表 服务类
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
public interface IUserAccountService extends IService<UserAccount> {

    /**
     * 注册
     * @param vo vo
     */
    void register(UserAccount vo);

    /**
     * 登陆
     * @param vo vo
     * @return LoginRespVO
     */
    LoginRespVO login(UserAccount vo);

    /**
     * 更新用户信息
     * @param vo vo
     */
    void updateUserInfo(UserAccount vo);

    /**
     * 分页
     * @param vo vo
     * @return IPage
     */
    IPage<UserAccount> pageInfo(UserAccount vo);

    /**
     * 添加用户
     * @param vo vo
     */
    void addUser(UserAccount vo);

    /**
     * 修改密码
     * @param vo vo
     */
    void updatePwd(UserAccount vo);

    /**
     * 根据userid获取绑定角色
     * @param userId userId
     * @return UserOwnRoleRespVO
     */
    UserOwnRoleRespVO getUserOwnRole(String userId);

    /**
     * 修改自己信息
     * @param vo vo
     */
    void updateUserInfoMy(UserAccount vo);

}
