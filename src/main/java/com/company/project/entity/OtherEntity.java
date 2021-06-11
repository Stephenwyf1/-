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
 * 其他检查
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Other")
public class OtherEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查的医生ID
     */
    @TableField("Other_doctor_id")
    private int otherDoctorId;

    /**
     * 体检信息提交时间
     */
    @TableField("Other_operation_time")
    private LocalDateTime otherOperationTime;

    /**
     * 其他检查
     */
    @TableField("Other_test")
    private String otherTest;

    /**
     * 医师意见
     */
    @TableField("Other_idea")
    private String otherIdea;

    /**
     * 是否全部填写完毕
     */
    @TableField("Other_all")
    private String otherAll;

    /**
     * 学生ID
     */
    @TableId("Stu_id")
    private Integer stuId;

    /**
     * 是否处于被驳回状态
     */
    @TableField("Other_error")
    private String otherError;


}
