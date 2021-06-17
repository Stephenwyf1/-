package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学生信息
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("Student")
public class StudentEntity extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @TableField("Stu_name")
    private String stuName;

    /**
     * 编号
     */
    @TableId("Stu_id")
    private Integer stuId;

    /**
     * 身份证
     */
    @TableField("Stu_card")
    private String stuCard;

    /**
     * 学院
     */
    @TableField("Stu_college")
    private String stuCollege;

    /**
     * 专业
     */
    @TableField("Stu_major")
    private String stuMajor;

    /**
     * 班级
     */
    @TableField("Stu_class")
    private String stuClass;

    /**
     * 学号
     */
    @TableField("Stu_num")
    private String stuNum;

    /**
     * 性别
     */
    @TableField("Stu_sex")
    private String stuSex;

    /**
     * 出生年月日
     */
    @TableField("Stu_birth")
    private Date stuBirth;

    /**
     * 年龄
     */
    @TableField("Stu_age")
    private Integer stuAge;

    /**
     * 文化程度
     */
    @TableField("Stu_education")
    private String stuEducation;

    /**
     * 民族
     */
    @TableField("Stu_nation")
    private String stuNation;

    /**
     * 职业
     */
    @TableField("Stu_profession")
    private String stuProfession;

    /**
     * 籍贯
     */
    @TableField("Stu_native")
    private String stuNative;

    /**
     * 家庭住址
     */
    @TableField("Stu_location")
    private String stuLocation;

    /**
     * 毕业学校或工作单位
     */
    @TableField("Stu_graduate_job")
    private String stuGraduateJob;

    /**
     * 既往病史
     */
    @TableField("Stu_anamnesis")
    private String stuAnamnesis;


}
