package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 内科医生
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Internal")
public class InternalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查的医生ID
     */
    @TableField("Internal_doctor_id")
    private int internalDoctorId;

    /**
     * 体检信息提交时间
     */
    @TableField("Internal_operation_time")
    private LocalDateTime internalOperationTime;

    /**
     * 发育营养
     */
    @TableField("Internal_nutrition")
    private String internalNutrition;

    /**
     * 神经及精神
     */
    @TableField("Internal_nerve_spirit")
    private String internalNerveSpirit;

    /**
     * 肺及呼吸道
     */
    @TableField("Internal_lung_respiratory")
    private String internalLungRespiratory;

    /**
     * 心脏及血管
     */
    @TableField("Internal_heart_blood")
    private String internalHeartBlood;

    /**
     * 腹部器官-肝
     */
    @TableField("Internal_liver")
    private String internalLiver;

    /**
     * 腹部器官-脾
     */
    @TableField("Internal_spleen")
    private String internalSpleen;

    /**
     * 其他
     */
    @TableField("Internal_other")
    private String internalOther;

    /**
     * 医师意见
     */
    @TableField("Internal_idea")
    private String internalIdea;

    /**
     * 学生ID
     */
    @TableId("Stu_id")
    private Integer stuId;

    /**
     * 是否全部填写完毕
     */
    @TableField("Internal_all")
    private String internalAll;

    /**
     * 是否处于驳回状态
     */
    @TableField("Internal_error")
    private String internalError;


}
