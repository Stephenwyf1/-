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
 * 血压脉搏
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Blood")
public class BloodEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查的医生ID
     */
    @TableField("Blood_doctor_id")
    private String bloodDoctorId;

    /**
     * 体检信息提交时间
     */
    @TableField("Blood_operation_time")
    private LocalDateTime bloodOperationTime;

    /**
     * 血压
     */
    @TableField("Blood_pressure")
    private String bloodPressure;

    /**
     * 脉搏
     */
    @TableField("Blood_pulse")
    private String bloodPulse;

    /**
     * 医生意见
     */
    @TableField("Blood_idea")
    private String bloodIdea;

    /**
     * 学生ID
     */
    @TableId("Stu_id")
    private Integer stuId;

    /**
     * 是否处于驳回状态
     */
    @TableField("Blood_error")
    private String bloodError;

    /**
     * 是否全部填写完毕
     */
    @TableField("Blood_all")
    private String bloodAll;


}
