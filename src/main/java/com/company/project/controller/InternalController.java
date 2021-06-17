package com.company.project.controller;


import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.InternalEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.entity.SysUser;
import com.company.project.mapper.SysUserMapper;
import com.company.project.service.IInternalService;
import com.company.project.service.UserService;
import com.company.project.vo.resp.UserInfoRespVO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 内科 前端控制器
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */

@ResponseBody
@RestController
@RequestMapping("/internal")
public class InternalController {

    @Resource
    private IInternalService iInternalService;

    @RequestMapping("/getStuList")
    public void getList(HttpServletResponse response, @RequestParam(name = "Stu_id", required = false, defaultValue = "-1") int Stu_id) throws JSONException{
        List<Map<String, Object>> DataList = iInternalService.getStuInfoList(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getInternalInfo")
    public void getInternalInfo(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        System.out.println("--------------------In getInternalInfo Controller--------------------");
        int Stu_id = Integer.parseInt(request.getParameter("Stu_id"));
        List<Map<String, Object>> DataList = iInternalService.getStuInternalInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------");
        System.out.println(ResultJSON);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertInternalInfo")
    public void insertInternalInfo(HttpServletResponse response, HttpServletRequest request,InternalEntity internalEntity) throws JSONException{
        System.out.println("--------------------In insertInternalInfo Controller--------------------");
        internalEntity.setInternalDoctorId(Integer.parseInt(request.getParameter("Internal_doctor_id")));
        internalEntity.setInternalOperationTime(LocalDateTime.now());
        internalEntity.setInternalHeartBlood(request.getParameter("Internal_heart_blood"));
        internalEntity.setInternalLiver(request.getParameter("Internal_liver"));
        internalEntity.setInternalLungRespiratory(request.getParameter("Internal_lung_respiratory"));
        internalEntity.setInternalNutrition(request.getParameter("Internal_nutrition"));
        internalEntity.setInternalSpleen(request.getParameter("Internal_spleen"));
        internalEntity.setInternalOther(request.getParameter("Internal_other"));
        internalEntity.setInternalIdea( request.getParameter("Internal_idea") );
        internalEntity.setInternalNerveSpirit(request.getParameter("Internal_nerve_spirit"));
        internalEntity.setInternalAll( "1" );
        internalEntity.setStuId( Integer.parseInt( request.getParameter("Stu_id") ));
        internalEntity.setInternalError( "0" );
        iInternalService.insertStuInternalInfo(internalEntity);

        JSONObject jsonObject = JSONUtil.CreateJSON(0,"ok",0,null);

        System.out.println("--------------------JSON--------------------");
        System.out.println(jsonObject);
        JSONUtil.JSONToResponse(response, jsonObject);
    }
}
