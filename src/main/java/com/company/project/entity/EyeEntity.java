package com.company.project.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 眼科信息表
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("Eye")
public class EyeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 左眼视力
     */
    @TableField("Eye_insight_left")
    private BigDecimal eyeInsightLeft;

    /**
     * 右眼视力
     */
    @TableField("Eye_insight_right")
    private BigDecimal eyeInsightRight;

    /**
     * 左眼矫正视力
     */
    @TableField("Eye_insight_left_correct")
    private BigDecimal eyeInsightLeftCorrect;

    /**
     * 右眼矫正视力
     */
    @TableField("Eye_insight_right_correct")
    private BigDecimal eyeInsightRightCorrect;

    /**
     * 其他眼病
     */
    @TableField("Eye_other")
    private String eyeOther;

    /**
     * 红色检查
     */
    @TableField("Eye_red")
    private String eyeRed;

    /**
     * 绿色检查
     */
    @TableField("Eye_green")
    private String eyeGreen;

    /**
     * 紫色检查
     */
    @TableField("Eye_purple")
    private String eyePurple;

    /**
     * 兰色检查
     */
    @TableField("Eye_blue")
    private String eyeBlue;

    /**
     * 黄色检查
     */
    @TableField("Eye_yellow")
    private String eyeYellow;

    /**
     * 医师意见
     */
    @TableField("Eye_idea")
    private String eyeIdea;

    /**
     * 内容是否填写完毕
     */
    @TableField("Eye_all")
    private String eyeAll;

    /**
     * 是否出现错误驳回
     */
    @TableField("Eye_error")
    private String eyeError;

    /**
     * 学生ID
     */
    @TableId("Stu_id")
    private Integer stuId;

    /**
     * 检查的医生ID
     */
    @TableField("Eye_doctor_id")
    private int eyeDoctorId;

    /**
     * 体检信息提交时间
     */
    @TableField("Eye_operation_time")
    private LocalDateTime eyeOperationTime;


}
