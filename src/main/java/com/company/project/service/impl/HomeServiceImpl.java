package com.company.project.service.impl;


import com.company.project.entity.SysUser;

import com.company.project.entity.UserAccount;
import com.company.project.service.*;
import com.company.project.vo.resp.HomeRespVO;
import com.company.project.vo.resp.PermissionRespNode;
import com.company.project.vo.resp.UserInfoRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private IUserAccountService userService;

    @Resource
    private PermissionService permissionService;

    @Override
    public HomeRespVO getHomeInfo(String userId) {


        UserAccount sysUser = userService.getById(userId);
        UserAccount vo = new UserAccount();

        if (sysUser != null) {
            BeanUtils.copyProperties(sysUser, vo);
        }

        List<PermissionRespNode> menus = permissionService.permissionTreeList(userId);

        HomeRespVO respVO = new HomeRespVO();
        respVO.setMenus(menus);
        respVO.setUserInfo(vo);

        return respVO;
    }
}
