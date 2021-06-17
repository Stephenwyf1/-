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
 * 医院领导信息录入
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Boss")
public class BossEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查的医生ID
     */
    @TableField("Boss_doctor_id")
    private int bossDoctorId;

    /**
     * 体检信息提交时间
     */
    @TableField("Boss_operation_time")
    private LocalDateTime bossOperationTime;

    /**
     * 检查医院意见
     */
    @TableField("Boss_conclusion")
    private String bossConclusion;

    /**
     * 是否全部填写完毕
     */
    @TableField("Boss_all")
    private String bossAll;

    /**
     * 学生ID
     */
    @TableId("Stu_id")
    private Integer stuId;

    /**
     * 是否处于被驳回状态
     */
    @TableField("Boss_error")
    private String bossError;


}
