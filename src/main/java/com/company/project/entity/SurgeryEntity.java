package com.company.project.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 外科
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Surgery")
public class SurgeryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查的医生ID
     */
    @TableField("Surgery_doctor_id")
    private String surgeryDoctorId;

    /**
     * 体检信息提交时间
     */
    @TableField("Surgery_operation_time")
    private LocalDateTime surgeryOperationTime;

    /**
     * 身长
     */
    @TableField("Surgery_height")
    private BigDecimal surgeryHeight;

    /**
     * 腰围
     */
    @TableField("Surgery_waistline")
    private BigDecimal surgeryWaistline;

    /**
     * 体重
     */
    @TableField("Surgery_weight")
    private BigDecimal surgeryWeight;

    /**
     * 皮肤
     */
    @TableField("Surgery_skin")
    private String surgerySkin;

    /**
     * 淋巴
     */
    @TableField("Surgery_lymph")
    private String surgeryLymph;

    /**
     * 甲状腺
     */
    @TableField("Surgery_thyroid")
    private String surgeryThyroid;

    /**
     * 脊柱
     */
    @TableField("Surgery_spine")
    private String surgerySpine;

    /**
     * 四肢
     */
    @TableField("Surgery_fours")
    private String surgeryFours;

    /**
     * 关节
     */
    @TableField("Surgery_joint")
    private String surgeryJoint;

    /**
     * 平拓足
     */
    @TableField("Surgery_flat_extension_foot")
    private String surgeryFlatExtensionFoot;

    /**
     * 其他
     */
    @TableField("Surgery_other")
    private String surgeryOther;

    /**
     * 医师意见
     */
    @TableField("Surgery_idea")
    private String surgeryIdea;

    /**
     * 是否全部填写完毕
     */
    @TableField("Surgery_all")
    private String surgeryAll;

    /**
     * 是否处于驳回状态
     */
    @TableField("Surgery_error")
    private String surgeryError;

    /**
     * 学生ID
     */
    @TableId("Stu_id")
    private Integer stuId;


}
