package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 化验
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Assay")
public class AssayEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查的医生ID
     */
    @TableField("Assay_doctor_id")
    private int assayDoctorId;

    /**
     * 体检信息提交时间
     */
    @TableField("Assay_operation_time")
    private LocalDateTime assayOperationTime;

    /**
     * 化验检查
     */
    @TableField("Assay_test")
    private String assayTest;

    /**
     * 医师意见
     */
    @TableField("Assay_idea")
    private String assayIdea;

    /**
     * 是否全部填写完毕
     */
    @TableField("Assay_all")
    private String assayAll;

    /**
     * 学生ID
     */
    @TableId("Stu_id")
    private Integer stuId;

    /**
     * 是否处于驳回状态
     */
    @TableField("Assay_error")
    private String assayError;


}
