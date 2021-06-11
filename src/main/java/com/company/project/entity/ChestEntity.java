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
 * 胸部
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Chest")
public class ChestEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 检查的医生ID
     */
    @TableField("Chest_doctor_id")
    private int chestDoctorId;

    /**
     * 体检信息提交时间
     */
    @TableField("Chest_operation_time")
    private LocalDateTime chestOperationTime;

    /**
     * 胸部放射检查
     */
    @TableField("Chest_test")
    private String chestTest;

    /**
     * 医师意见
     */
    @TableField("Chest_idea")
    private String chestIdea;

    /**
     * 是否全部填写完毕
     */
    @TableField("Chest_all")
    private String chestAll;

    /**
     * 学生ID
     */
    @TableId("Stu_id")
    private Integer stuId;

    /**
     * 是否处于被驳回状态
     */
    @TableField("Chest_error")
    private String chestError;


}
