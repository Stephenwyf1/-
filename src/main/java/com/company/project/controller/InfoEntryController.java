package com.company.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.company.project.common.aop.annotation.LogAnnotation;
import com.company.project.common.utils.DataResult;
import com.company.project.entity.DoctorEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "批量导入数据")
@RequestMapping("/index")
@Slf4j
public class InfoEntryController {

    @Resource
    private IDoctorService iDoctorService;
    @Resource
    private IStudentService iStudentService;

    @Resource
    HttpSessionService httpSessionService;

    @PostMapping("importStudent")
    @ApiOperation(value = "批量导入数据")
//    @RequiresPermissions("")
    @LogAnnotation(title = "导入数据", action = "学生数据导入")
    public DataResult insertStudentInfo(@RequestBody StudentEntity[] students) {
        for (StudentEntity student:students) {
            System.out.println("\n\n\n\n\n\n\n\n\n"+students[0].toString()+"\n\n\n\n\n\n\n\n\n" );
            iStudentService.addStudent(student);
        }
        return DataResult.success();
    }

    @PostMapping("insertStudent")
    @ApiOperation(value = "插入单个学生数据")
//    @RequiresPermissions("")
    @LogAnnotation(title = "导入数据", action = "插入单个学生数据")
    public DataResult insertStudent(@RequestBody StudentEntity student) {
        iStudentService.addStudent(student);
        return DataResult.success();
    }


    @PostMapping("/importDoctor")
    @ApiOperation(value = "批量导入数据")
//    @RequiresPermissions("")
    @LogAnnotation(title = "导入数据", action = "医生数据插入")
    public DataResult insertDoctorInfo(@RequestBody DoctorEntity[] doctors) {
        for (DoctorEntity doctor:doctors) {
            System.out.println("\n\n\n\n\n\n\n\n\n"+doctors[0].toString()+"\n\n\n\n\n\n\n\n\n" );
            iDoctorService.addDoctor(doctor);
        }
        return DataResult.success();
    }

    @PostMapping("/updateDoctor")
    @ApiOperation(value = "更新单个医生信息")
//    @RequiresPermissions("")
    @LogAnnotation(title = "更新", action = "更新医生信息")
    public DataResult updateDoctorInfo(@RequestBody DoctorEntity vo) {
        iDoctorService.updateDoctor(vo);
        return DataResult.success();
    }

    @PostMapping("/updateStudent")
    @ApiOperation(value = "更新单个学生信息")
//    @RequiresPermissions("")
    @LogAnnotation(title = "更新", action = "更新学生信息")
    public DataResult updateStudentInfo(@RequestBody StudentEntity vo) {
        iStudentService.updateStudent(vo);
        return DataResult.success();
    }

    @PostMapping("/deleteStudents")
    @ApiOperation(value = "批量删除学生")
//    @RequiresPermissions("")
    @LogAnnotation(title = "删除", action = "删除一批学生")
    public DataResult deleteStudentInfo(@RequestBody @ApiParam(value = "学生id集合") List<String> stuIds) {
        httpSessionService.abortUserByUserIds(stuIds);
        LambdaQueryWrapper<StudentEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(StudentEntity::getStuId, stuIds);
        iStudentService.remove(queryWrapper);
        return DataResult.success();
    }

    @PostMapping("/deleteDoctors")
    @ApiOperation(value = "批量删除医生")
//    @RequiresPermissions("")
    @LogAnnotation(title = "删除", action = "删除一批医生")
    public DataResult deleteDoctorInfo(@RequestBody @ApiParam(value = "医生id集合") List<String> doctorIds) {
        for(String doctorId:doctorIds){
            iDoctorService.deleteDoctor(Integer.getInteger(doctorId));
        }
        return DataResult.success();
    }

    @PostMapping("/doctorPage")
    @ApiOperation(value = "批量拿到医生信息")
//    @RequiresPermissions("")
    @LogAnnotation(title = "获取", action = "获取所有信息")
    public DataResult doctorPageInfo(@RequestBody DoctorEntity vo) {

        return DataResult.success(iDoctorService.pageInfo(vo));
    }

    @PostMapping("/studentPage")
    @ApiOperation(value = "批量拿到学生信息")
//    @RequiresPermissions("")
    @LogAnnotation(title = "获取", action = "获取所有学生信息")
    public DataResult studentPageInfo(@RequestBody StudentEntity vo) {
        return DataResult.success(iStudentService.pageInfo(vo));
    }

    @GetMapping("/singleDocotorInfo")
    @ApiOperation(value = "拿到单个医生信息")
    @LogAnnotation(title = "获取", action = "单个医生信息")
//    @RequiresPermissions("")
    public DataResult doctorInfo(@PathVariable("id") String id) {

        return DataResult.success(iDoctorService.getById(id));
    }

    @GetMapping("singleStudentInfo")
    @ApiOperation(value = "拿到单个学生信息")
    @LogAnnotation(title = "获取", action = "单个学生信息")
//    @RequiresPermissions("")
    public DataResult studentInfo(@PathVariable("id") String id) {

        return DataResult.success(iStudentService.getById(id));
    }

}
