package com.company.project.controller;


import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.AssayEntity;
import com.company.project.entity.ChestEntity;
import com.company.project.service.IChestService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 胸部 前端控制器
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/chest")
public class ChestController {

    @Resource
    IChestService iChestService;

    @RequestMapping("/getStuList")
    public void getList(HttpServletResponse response) throws JSONException {
        System.out.println("--------------------In getStuList Controller--------------------");

        List<Map<String, Object>> DataList = iChestService.getStuInfoList();
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------");
        System.out.println(ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getChestInfo")
    public void getAssayInfo(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        System.out.println("--------------------In getChestInfo Controller--------------------");
        int Stu_id = Integer.parseInt(request.getParameter("Stu_id"));
        List<Map<String, Object>> DataList = iChestService.getStuChestInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------");
        System.out.println(ResultJSON);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertChestInfo")
    public void insertAssayInfo(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        System.out.println("--------------------In insertChestInfo Controller--------------------");
        ChestEntity chestEntity = new ChestEntity();

        chestEntity.setChestDoctorId( request.getParameter("Assay_doctor_id") );
        chestEntity.setChestOperationTime( LocalDateTime.now() );
        chestEntity.setChestTest( request.getParameter("Assay_test") );
        chestEntity.setChestIdea( request.getParameter("Assay_idea") );
        chestEntity.setChestAll( "1" );
        chestEntity.setStuId( Integer.parseInt( request.getParameter("Stu_id") ));
        chestEntity.setChestError( "0" );

        iChestService.insertStuChestInfo(chestEntity);

        JSONObject jsonObject = JSONUtil.CreateJSON(0,"ok",0,null);

        System.out.println("--------------------JSON--------------------");
        System.out.println(jsonObject);
        JSONUtil.JSONToResponse(response, jsonObject);
    }

}
