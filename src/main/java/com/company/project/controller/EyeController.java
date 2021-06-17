package com.company.project.controller;


import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.EyeEntity;
import com.company.project.service.IEyeService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
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
@RequestMapping("/eye")
public class EyeController {

    @Resource
    private IEyeService iEyeService;

    @RequestMapping("/getStuList")
    public void getList(HttpServletResponse response,@RequestParam(name = "Stu_id", required = false, defaultValue = "-1") int Stu_id) throws JSONException{
        List<Map<String, Object>> DataList = iEyeService.getStuInfoList(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getEyeInfo")
    public void getEyeInfo(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        System.out.println("--------------------In getEyeInfo Controller--------------------");
        int Stu_id = Integer.parseInt(request.getParameter("Stu_id"));
        List<Map<String, Object>> DataList = iEyeService.getStuEyeInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------");
        System.out.println(ResultJSON);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertEyeInfo")
    public void insertEyeInfo(HttpServletResponse response, HttpServletRequest request,EyeEntity eyeEntity) throws JSONException{
        System.out.println("--------------------In insertEyeInfo Controller--------------------");
        eyeEntity.setEyeDoctorId(Integer.parseInt(request.getParameter("Eye_doctor_id")));
        eyeEntity.setEyeOperationTime( LocalDateTime.now());
        BigDecimal Eye_insight_left=new BigDecimal((request.getParameter("Eye_insight_left")));
        eyeEntity.setEyeInsightLeft(Eye_insight_left);
        BigDecimal Eye_insight_right=new BigDecimal((request.getParameter("Eye_insight_right")));
        eyeEntity.setEyeInsightRight(Eye_insight_right);
        BigDecimal Eye_insight_left_correct=new BigDecimal((request.getParameter("Eye_insight_left_correct")));
        eyeEntity.setEyeInsightLeftCorrect(Eye_insight_left_correct);
        BigDecimal Eye_insight_right_correct=new BigDecimal((request.getParameter("Eye_insight_right_correct")));
        eyeEntity.setEyeInsightRightCorrect(Eye_insight_right_correct);
        eyeEntity.setEyeRed(request.getParameter("Eye_red"));
        eyeEntity.setEyeBlue(request.getParameter("Eye_blue"));
        eyeEntity.setEyeGreen(request.getParameter("Eye_green"));
        eyeEntity.setEyePurple(request.getParameter("Eye_purple"));
        eyeEntity.setEyeYellow(request.getParameter("Eye_yellow"));
        eyeEntity.setEyeIdea(request.getParameter("Eye_idea"));
        eyeEntity.setEyeOther(request.getParameter("Eye_other"));
        eyeEntity.setEyeAll( "1" );
        eyeEntity.setStuId(Integer.parseInt( request.getParameter("Stu_id") ));
        eyeEntity.setEyeError( "0" );
        iEyeService.insertStuEyeInfo(eyeEntity);
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
