package com.company.project.controller;


import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.AssayEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.service.IAssayService;
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
    public void getList(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        List<Map<String, Object>> DataList = iAssayService.getStuInfoList();
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getAssayInfo")
    public void getAssayInfo(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        int Stu_id = Integer.parseInt(request.getParameter("Stu_id"));
        List<Map<String, Object>> DataList = iAssayService.getStuAssayInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertAssayInfo")
    public void insertAssayInfo(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        AssayEntity assayEntity = new AssayEntity();

        assayEntity.setAssayDoctorId( request.getParameter("Assay_doctor_id") );
        assayEntity.setAssayOperationTime( LocalDateTime.parse(request.getParameter("Assay_operation_time")) );
        assayEntity.setAssayTest( request.getParameter("Assay_test") );
        assayEntity.setAssayIdea( request.getParameter("Assay_idea") );
        assayEntity.setAssayAll( request.getParameter("Assay_all") );
        assayEntity.setStuId(Integer.parseInt( request.getParameter("Stu_id") ));
        assayEntity.setAssayError( request.getParameter("Assay_error") );

        iAssayService.insertStuAssayInfo(assayEntity);

        JSONObject jsonObject = JSONUtil.CreateJSON(0,"ok",0,null);

        JSONUtil.JSONToResponse(response, jsonObject);

    }

}
