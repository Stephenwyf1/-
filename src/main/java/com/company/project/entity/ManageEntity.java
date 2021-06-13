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
 * 体检负责医生
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Manage")
public class ManageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查的医生ID
     */
    @TableField("Manage_doctor_id")
    private int manageDoctorId;

    /**
     * 体检信息提交时间
     */
    @TableField("Manage_operation_time")
    private LocalDateTime manageOperationTime;

    /**
     * 检查结论
     */
    @TableField("Manage_conclusion")
    private String manageConclusion;

    /**
     * 是否全部填写完毕
     */
    @TableField("Manage_all")
    private String manageAll;

    /**
     * 学生ID
     */
    @TableId("Stu_id")
    private Integer stuId;

    /**
     * 是否处于被驳回状态
     */
    @TableField("Manage_error")
    private String manageError;


}
