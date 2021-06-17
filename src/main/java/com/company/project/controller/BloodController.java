package com.company.project.controller;


import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.BloodEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.entity.SysUser;
import com.company.project.mapper.SysUserMapper;
import com.company.project.service.HttpSessionService;
import com.company.project.service.IBloodService;
import com.company.project.service.UserService;
import com.company.project.vo.resp.UserInfoRespVO;
import com.google.common.reflect.TypeToken;
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
 * 血压脉搏 前端控制器
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */

@ResponseBody
@RestController
@RequestMapping("/blood")
public class BloodController {

    @Resource
    private IBloodService iBloodService;

    @Resource
    private HttpSessionService httpSessionService;

    @RequestMapping("/getStuList")
    public void getList(HttpServletResponse response, @RequestParam(name = "Stu_id", required = false, defaultValue = "-1") int Stu_id) throws JSONException{
        List<Map<String, Object>> DataList = iBloodService.getStuInfoList(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getBloodInfo")
    public void getBloodInfo(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        System.out.println("--------------------In getBloodInfo Controller--------------------");
        int Stu_id = Integer.parseInt(request.getParameter("Stu_id"));
        List<Map<String, Object>> DataList = iBloodService.getStuBloodInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------");
        System.out.println(ResultJSON);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertBloodInfo")
    public void insertBloodInfo(HttpServletResponse response, HttpServletRequest request,BloodEntity bloodEntity) throws JSONException{
        System.out.println("--------------------In insertBloodInfo Controller--------------------");

        int Stu_id = Integer.parseInt( request.getParameter("Stu_id") );
        List<Map<String, Object>> DataList = iBloodService.getStuBloodInfo(Stu_id);
        String DataListStr = splitDownLineandAfterToUpperCase(DataList.toString());
        System.out.println("DataList1:"+DataList);
        System.out.println("DataStr:"+DataListStr);
        System.out.println("BeforeBloodEntity:"+bloodEntity);

        bloodEntity.setBloodDoctorId(Integer.parseInt( httpSessionService.getCurrentUserId() ));
        bloodEntity.setBloodOperationTime( LocalDateTime.now() );
        bloodEntity.setBloodPressure( request.getParameter("Blood_pressure") );
        bloodEntity.setBloodPulse( request.getParameter("Blood_pulse") );
        bloodEntity.setBloodIdea( request.getParameter("Blood_idea") );
        bloodEntity.setBloodAll( "1" );
        bloodEntity.setStuId(Integer.parseInt( request.getParameter("Stu_id") ));
        bloodEntity.setBloodError( "0" );
        iBloodService.insertStuBloodInfo(bloodEntity);
        System.out.println("AfterBloodEntity:"+bloodEntity);
        JSONObject jsonObject = JSONUtil.CreateJSON(0,"ok",0,null);

        System.out.println("--------------------JSON--------------------");
        System.out.println(jsonObject);
        JSONUtil.JSONToResponse(response, jsonObject);
    }

    public String splitDownLineandAfterToUpperCase(String str) {
        String s[] = str.toLowerCase().split("_");
        String newA="";
        for(int i=1;i<s.length;i++){
            newA += s[i].substring(0, 1).toUpperCase() + s[i].substring(1);
        }
        return newA =s[0].toLowerCase()+newA;
    }

}
