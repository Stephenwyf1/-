package com.company.project.controller;


import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.AssayEntity;
import com.company.project.service.IAssayService;
import org.json.JSONException;
import org.json.JSONObject;
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
 * 化验 前端控制器
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/assay")
public class AssayController {

    @Resource
    private IAssayService iAssayService;

    @RequestMapping("/getStuList")
    public void getList(HttpServletResponse response, @RequestParam(name = "Stu_id", required = false, defaultValue = "-1") int Stu_id) throws JSONException{
        System.out.println("--------------------In getStuList Controller--------------------");

        List<Map<String, Object>> DataList = iAssayService.getStuInfoList(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getAssayInfo")
    public void getAssayInfo(HttpServletResponse response, @RequestParam(name = "Stu_id") int Stu_id) throws JSONException{
        System.out.println("--------------------In getAssayInfo Controller--------------------");

        List<Map<String, Object>> DataList = iAssayService.getStuAssayInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertAssayInfo")
    public void insertAssayInfo(HttpServletResponse response, AssayEntity assayEntity) throws JSONException{
        System.out.println("--------------------In insertAssayInfo Controller--------------------");

        assayEntity.setAssayAll("1");
        assayEntity.setAssayError("0");
        assayEntity.setAssayOperationTime(LocalDateTime.now());
        iAssayService.insertStuAssayInfo(assayEntity);

        JSONObject jsonObject = JSONUtil.CreateJSON(0,"ok",0,null);

        System.out.println("--------------------JSON--------------------\n"+jsonObject);

        JSONUtil.JSONToResponse(response, jsonObject);
    }
}
