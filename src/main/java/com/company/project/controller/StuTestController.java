//package com.company.project.controller;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.company.project.common.utils.DataResult;
//import com.company.project.entity.StuTestEntity;
//import com.company.project.service.StuTestService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
///**
// * 学生体检信息表
// *
// * @author wenbin
// * @email *****@mail.com
// * @date 2021-06-12 09:40:44
// */
//@Controller
//@RequestMapping("/")
//public class StuTestController {
//    @Autowired
//    private StuTestService stuTestService;
//
//
//    /**
//    * 跳转到页面
//    */
//    @GetMapping("/index/stuTest")
//    public String stuTest() {
//        return "stutest/list";
//        }
//
//    @ApiOperation(value = "新增")
//    @PostMapping("stuTest/add")
//    @RequiresPermissions("stuTest:add")
//    @ResponseBody
//    public DataResult add(@RequestBody StuTestEntity stuTest){
//        stuTestService.save(stuTest);
//        return DataResult.success();
//    }
//
//    @ApiOperation(value = "删除")
//    @DeleteMapping("stuTest/delete")
//    @RequiresPermissions("stuTest:delete")
//    @ResponseBody
//    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
//        stuTestService.removeByIds(ids);
//        return DataResult.success();
//    }
//
//    @ApiOperation(value = "更新")
//    @PutMapping("stuTest/update")
//    @RequiresPermissions("stuTest:update")
//    @ResponseBody
//    public DataResult update(@RequestBody StuTestEntity stuTest){
//        stuTestService.updateById(stuTest);
//        return DataResult.success();
//    }
//
//    @ApiOperation(value = "查询分页数据")
//    @PostMapping("stuTest/listByPage")
//    @RequiresPermissions("stuTest:list")
//    @ResponseBody
//    public DataResult findListByPage(@RequestBody StuTestEntity stuTest){
//        Page page = new Page(stuTest.getPage(), stuTest.getLimit());
//        LambdaQueryWrapper<StuTestEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        //queryWrapper.eq(StuTestEntity::getId, stuTest.getId());
//        IPage<StuTestEntity> iPage = stuTestService.page(page, queryWrapper);
//        return DataResult.success(iPage);
//    }
//
//}
