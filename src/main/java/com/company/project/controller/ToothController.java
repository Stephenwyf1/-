package com.company.project.controller;


import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.ToothEntity;
import com.company.project.service.IToothService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

@ResponseBody
@RestController
@RequestMapping("/tooth")
public class ToothController {

    @Resource
    private IToothService iToothService;

    @RequestMapping("/getStuList")
    public void getList(HttpServletResponse response, @RequestParam(name = "Stu_id", required = false, defaultValue = "-1") int Stu_id) throws JSONException{
        List<Map<String, Object>> DataList = iToothService.getStuInfoList(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getToothInfo")
    public void getToothInfo(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        System.out.println("--------------------In getToothInfo Controller--------------------");
        System.out.println("attention1:"+request.getParameter("Stu_id"));
        int Stu_id = Integer.parseInt(request.getParameter("Stu_id"));
        List<Map<String, Object>> DataList = iToothService.getStuToothInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertToothInfo")
    public void insertToothInfo(HttpServletResponse response, HttpServletRequest request,ToothEntity toothEntity) throws JSONException{
        System.out.println("--------------------In insertToothInfo Controller--------------------");
        toothEntity.setToothDoctorId(Integer.parseInt(request.getParameter("Tooth_doctor_id")));
        toothEntity.setToothOperationTime( LocalDateTime.now() );
        toothEntity.setToothDecayed( request.getParameter("Tooth_decayed") );
        toothEntity.setToothHypodontia( request.getParameter("Tooth_hypodontia") );
        toothEntity.setToothToothSpace(request.getParameter("Tooth_tooth_space"));
        toothEntity.setToothIdea( request.getParameter("Tooth_idea") );
        toothEntity.setToothOther(request.getParameter("Tooth_other"));
        toothEntity.setToothAll( "1" );
        toothEntity.setStuId(Integer.parseInt( request.getParameter("Stu_id") ));
        toothEntity.setToothError( "0" );
        iToothService.insertStuToothInfo(toothEntity);
        JSONObject jsonObject = JSONUtil.CreateJSON(0,"ok",0,null);

        System.out.println("--------------------JSON--------------------\n"+jsonObject);
        JSONUtil.JSONToResponse(response, jsonObject);
    }

}
