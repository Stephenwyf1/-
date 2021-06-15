package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户账号表
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("User_Account")
public class UserAccount extends BaseEntity implements Serializable {
    /**
     * 用户账号
     */
    @TableField("Account_name")
    private String username;

    /**
     * 用户密码
     */
    @TableField("Password")
    private String password;

    @TableField(exist = false)
    private String oldPwd;

    @TableField(exist = false)
    private String newPwd;
    /**
     * 用户id
     */
    @TableId("User_id")
    private Integer userId;

    @TableField("User_type")
    private Integer userType;

    @TableField("salt")
    private String salt;

    @TableField(exist = false)
    private String Captcha;

    @TableField("createTime")
    private Date createTime;

    @TableField("status")
    private Boolean status;

    @TableField("sex")
    private Integer sex;
}
