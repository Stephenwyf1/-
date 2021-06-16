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
 * 耳鼻喉科体检信息
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("EBH")
public class EbhEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查的医生ID
     */
    @TableField("EBH_doctor_id")
    private String ebhDoctorId;

    /**
     * 体检信息提交时间
     */
    @TableField("EBH_operation_time")
    private LocalDateTime ebhOperationTime;

    /**
     * 咽喉
     */
    @TableField("EBH_throat")
    private String ebhThroat;

    /**
     * 口吃
     */
    @TableField("EBH_stammer")
    private String ebhStammer;

    /**
     * 医师意见
     */
    @TableField("EBH_idea")
    private String ebhIdea;

    /**
     * 是否全部录入完毕
     */
    @TableField("EBH_all")
    private String ebhAll;

    /**
     * 学生ID
     */
    @TableId("Stu_id")
    private Integer stuId;

    /**
     * 是否处于被驳回状态
     */
    @TableField("EBH_error")
    private String ebhError;


}
