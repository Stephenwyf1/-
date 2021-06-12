package com.company.project.controller;


import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.AssayEntity;
import com.company.project.entity.ManageEntity;
import com.company.project.service.IManageService;
import com.company.project.service.StuTestService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 体检负责医生 前端控制器
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/manage")
public class ManageController {

    @Resource
    private IManageService iManageService;

    @Resource
    private StuTestService stuTestService;

    @RequestMapping("/getStuList")
    public void getList(HttpServletResponse response, @RequestParam(name = "Stu_id", required = false, defaultValue = "-1") int Stu_id) throws JSONException {
        System.out.println("--------------------In getStuList Controller--------------------");

        List<Map<String, Object>> DataList = iManageService.getStuInfoList(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getStuTestInfo")
    public void getAssayInfo(HttpServletResponse response, @RequestParam(name = "Stu_id") int Stu_id) throws JSONException, IllegalAccessException {
        System.out.println("--------------------In getStuTestInfo Controller--------------------");

        List<Map<String, Object>> DataList = stuTestService.getStuTestInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getManageInfo")
    public void getManageInfo(HttpServletResponse response, @RequestParam(name = "Stu_id") int Stu_id) throws JSONException{
        System.out.println("--------------------In getManageInfo Controller--------------------");

        List<Map<String, Object>> DataList = iManageService.getStuManageInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertManageInfo")
    public void insertManageInfo(HttpServletResponse response, ManageEntity manageEntity) throws JSONException{
        System.out.println("--------------------In insertManageInfo Controller--------------------");

        manageEntity.setManageAll("1");
        manageEntity.setManageError("0");
        manageEntity.setManageOperationTime(LocalDateTime.now());
        iManageService.insertStuManageInfo(manageEntity);

        JSONObject jsonObject = JSONUtil.CreateJSON(0,"ok",0,null);

        System.out.println("--------------------JSON--------------------\n"+jsonObject);

        JSONUtil.JSONToResponse(response, jsonObject);
    }

}
