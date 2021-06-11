package com.company.project.controller;


import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.OtherEntity;
import com.company.project.service.IOtherService;
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
 * 其他检查 前端控制器
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/other")
public class OtherController {

    @Resource
    private IOtherService iOtherService;

    @RequestMapping("/getStuList")
    public void getList(HttpServletResponse response, @RequestParam(name = "Stu_id", required = false, defaultValue = "-1") int Stu_id) throws JSONException {
        System.out.println("--------------------In getStuList Controller--------------------");

        List<Map<String, Object>> DataList = iOtherService.getStuInfoList(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getOtherInfo")
    public void getAssayInfo(HttpServletResponse response, @RequestParam(name = "Stu_id") int Stu_id) throws JSONException{
        System.out.println("--------------------In getOtherInfo Controller--------------------");

        List<Map<String, Object>> DataList = iOtherService.getStuOtherInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertOtherInfo")
    public void insertAssayInfo(HttpServletResponse response, OtherEntity otherEntity) throws JSONException{
        System.out.println("--------------------In insertOtherInfo Controller--------------------");

        otherEntity.setOtherAll("1");
        otherEntity.setOtherError("0");
        otherEntity.setOtherOperationTime(LocalDateTime.now());
        iOtherService.insertStuOtherInfo(otherEntity);

        JSONObject jsonObject = JSONUtil.CreateJSON(0,"ok",0,null);

        System.out.println("--------------------JSON--------------------\n"+jsonObject);

        JSONUtil.JSONToResponse(response, jsonObject);
    }

}
