package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * 学生体检信息表
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-06-12 09:40:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("stu_test")
public class StuTestEntity implements Serializable {
//	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId("Stu_id")
	private Integer stuId;

	/**
	 * 
	 */
	@TableField("Eye_idea")
	private String eyeIdea;

	/**
	 * 
	 */
	@TableField("Eye_doctor_name")
	private String eyeDoctorName;

	/**
	 * 
	 */
	@TableField("Eye_doctor_id")
	private Integer eyeDoctorId;

	/**
	 * 
	 */
	@TableField("Eye_operation_time")
	private LocalDateTime eyeOperationTime;

	/**
	 * 
	 */
	@TableField("EBH_idea")
	private String eBHIdea;

	/**
	 * 
	 */
	@TableField("EBH_doctor_name")
	private String eBHDoctorName;

	/**
	 * 
	 */
	@TableField("EBH_doctor_id")
	private Integer eBHDoctorId;

	/**
	 * 
	 */
	@TableField("EBH_operation_time")
	private LocalDateTime eBHOperationTime;

	/**
	 * 
	 */
	@TableField("Tooth_idea")
	private String toothIdea;

	/**
	 * 
	 */
	@TableField("Tooth_doctor_id")
	private Integer toothDoctorId;

	/**
	 * 
	 */
	@TableField("Tooth_doctor_name")
	private String toothDoctorName;

	/**
	 * 
	 */
	@TableField("Tooth_operation_time")
	private LocalDateTime toothOperationTime;

	/**
	 * 
	 */
	@TableField("Surgery_idea")
	private String surgeryIdea;

	/**
	 * 
	 */
	@TableField("Surgery_doctor_id")
	private Integer surgeryDoctorId;

	/**
	 * 
	 */
	@TableField("Surgery_doctor_name")
	private String surgeryDoctorName;

	/**
	 * 
	 */
	@TableField("Surgery_operation_time")
	private LocalDateTime surgeryOperationTime;

	/**
	 * 
	 */
	@TableField("Blood_idea")
	private String bloodIdea;

	/**
	 * 
	 */
	@TableField("Blood_doctor_id")
	private Integer bloodDoctorId;

	/**
	 * 
	 */
	@TableField("Blood_doctor_name")
	private String bloodDoctorName;

	/**
	 * 
	 */
	@TableField("Blood_operation_time")
	private LocalDateTime bloodOperationTime;

	/**
	 * 
	 */
	@TableField("Internal_idea")
	private String internalIdea;

	/**
	 * 
	 */
	@TableField("Internal_doctor_id")
	private Integer internalDoctorId;

	/**
	 * 
	 */
	@TableField("Internal_doctor_name")
	private String internalDoctorName;

	/**
	 * 
	 */
	@TableField("Internal_operation_time")
	private LocalDateTime internalOperationTime;

	/**
	 * 
	 */
	@TableField("Assay_idea")
	private String assayIdea;

	/**
	 * 
	 */
	@TableField("Assay_doctor_id")
	private Integer assayDoctorId;

	/**
	 * 
	 */
	@TableField("Assay_doctor_name")
	private String assayDoctorName;

	/**
	 * 
	 */
	@TableField("Assay_operation_time")
	private LocalDateTime assayOperationTime;

	/**
	 * 
	 */
	@TableField("Chest_idea")
	private String chestIdea;

	/**
	 * 
	 */
	@TableField("Chest_doctor_id")
	private Integer chestDoctorId;

	/**
	 * 
	 */
	@TableField("Chest_doctor_name")
	private String chestDoctorName;

	/**
	 * 
	 */
	@TableField("Chest_operation_time")
	private LocalDateTime chestOperationTime;

	/**
	 * 
	 */
	@TableField("Other_idea")
	private String otherIdea;

	/**
	 * 
	 */
	@TableField("Other_doctor_id")
	private Integer otherDoctorId;

	/**
	 * 
	 */
	@TableField("Other_doctor_name")
	private String otherDoctorName;

	/**
	 * 
	 */
	@TableField("Other_operation_time")
	private LocalDateTime otherOperationTime;

	/**
	 * 
	 */
	@TableField("Manage_conclusion")
	private String manageConclusion;

	/**
	 * 
	 */
	@TableField("Manage_doctor_id")
	private Integer manageDoctorId;

	/**
	 * 
	 */
	@TableField("Manage_doctor_name")
	private String manageDoctorName;

	/**
	 * 
	 */
	@TableField("Manage_operation_time")
	private LocalDateTime manageOperationTime;

	/**
	 * 
	 */
	@TableField("Stu_test_count")
	private Integer stuTestCount;


}
