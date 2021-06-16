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
 * 口腔
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Tooth")
public class ToothEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查的医生ID
     */
    @TableField("Tooth_doctor_id")
    private String toothDoctorId;

    /**
     * 体检信息提交时间
     */
    @TableField("Tooth_operation_time")
    private LocalDateTime toothOperationTime;

    /**
     * 龋齿
     */
    @TableField("Tooth_decayed")
    private String toothDecayed;

    /**
     * 缺齿
     */
    @TableField("Tooth_hypodontia")
    private String toothHypodontia;

    /**
     * 齿槽
     */
    @TableField("Tooth_tooth_space")
    private String toothToothSpace;

    /**
     * 其他
     */
    @TableField("Tooth_other")
    private String toothOther;

    /**
     * 医师意见
     */
    @TableField("Tooth_idea")
    private String toothIdea;

    /**
     * 是否全部
     */
    @TableField("Tooth_all")
    private String toothAll;

    /**
     * 学生ID
     */
    @TableId("Stu_id")
    private Integer stuId;

    /**
     * 是否处于驳回状态
     */
    @TableField("Tooth_error")
    private String toothError;


}
