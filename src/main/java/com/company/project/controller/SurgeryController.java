package com.company.project.controller;


import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.SurgeryEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.entity.SysUser;
import com.company.project.mapper.SysUserMapper;
import com.company.project.service.ISurgeryService;
import com.company.project.service.UserService;
import com.company.project.vo.resp.UserInfoRespVO;
import com.google.common.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/surgery")
public class SurgeryController {

    @Resource
    private ISurgeryService iSurgeryService;

    @RequestMapping("/getStuList")
    public void getList(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        List<Map<String, Object>> DataList = iSurgeryService.getStuInfoList();
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getSurgeryInfo")
    public void getSurgeryInfo(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        System.out.println("--------------------In getSurgeryInfo Controller--------------------");
        int Stu_id = Integer.parseInt(request.getParameter("Stu_id"));
        List<Map<String, Object>> DataList = iSurgeryService.getStuSurgeryInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------");
        System.out.println(ResultJSON);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertSurgeryInfo")
    public void insertSurgeryInfo(HttpServletResponse response, HttpServletRequest request,SurgeryEntity surgeryEntity) throws JSONException{
        System.out.println("--------------------In insertSurgeryInfo Controller--------------------");

        surgeryEntity.setSurgeryDoctorId(request.getParameter("Surgery_doctor_id"));
        surgeryEntity.setSurgeryOperationTime( LocalDateTime.now() );
        surgeryEntity.setSurgeryIdea( request.getParameter("Surgery_idea") );
        surgeryEntity.setSurgeryFlatExtensionFoot(request.getParameter("Surgery_flat_extension_foot") );
        surgeryEntity.setSurgeryFours(request.getParameter("Surgery_fours"));
        BigDecimal Surgery_height=new BigDecimal((request.getParameter("Surgery_height")));
        surgeryEntity.setSurgeryHeight(Surgery_height);
        surgeryEntity.setSurgeryJoint(request.getParameter("Surgery_joint"));
        surgeryEntity.setSurgeryLymph(request.getParameter("Surgery_lymph"));
        surgeryEntity.setSurgerySkin(request.getParameter("Surgery_skin"));
        surgeryEntity.setSurgerySpine(request.getParameter("Surgery_spine"));
        surgeryEntity.setSurgeryThyroid(request.getParameter("Surgery_thyroid"));
        BigDecimal Surgery_waistline=new BigDecimal((request.getParameter("Surgery_waistline")));
        surgeryEntity.setSurgeryWaistline(Surgery_waistline);
        BigDecimal Surgery_weight=new BigDecimal((request.getParameter("Surgery_weight")));
        surgeryEntity.setSurgeryWeight(Surgery_weight);
        surgeryEntity.setSurgeryOther(request.getParameter("Surgery_other"));
        surgeryEntity.setSurgeryAll( "1" );
        surgeryEntity.setStuId(Integer.parseInt( request.getParameter("Stu_id") ));
        surgeryEntity.setSurgeryError( "0" );
        iSurgeryService.insertStuSurgeryInfo(surgeryEntity);
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
