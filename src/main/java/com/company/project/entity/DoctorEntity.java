package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 医生表
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("Doctor")
public class DoctorEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId("Doctor_id")
    private Integer doctorId;

    /**
     * 医生姓名
     */
    @TableField("Doctor_name")
    private String doctorName;

    /**
     * 医生身份证号
     */
    @TableField("Doctor_card")
    private String doctorCard;

    /**
     * 医生科室
     */
    @TableField("Doctor_department")
    private String doctorDepartment;


}
