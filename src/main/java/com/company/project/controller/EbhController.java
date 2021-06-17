package com.company.project.controller;


import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.EbhEntity;
import com.company.project.entity.StudentEntity;
import com.company.project.entity.SysUser;
import com.company.project.mapper.SysUserMapper;
import com.company.project.service.IEbhService;
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
 * 化验 前端控制器
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */

@ResponseBody
@RestController
@RequestMapping("/ebh")
public class EbhController {

    @Resource
    private IEbhService iEbhService;

    @RequestMapping("/getStuList")
    public void getList(HttpServletResponse response, @RequestParam(name = "Stu_id", required = false, defaultValue = "-1") int Stu_id) throws JSONException{
        List<Map<String, Object>> DataList = iEbhService.getStuInfoList(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getEbhInfo")
    public void getEbhInfo(HttpServletResponse response, HttpServletRequest request) throws JSONException{
        System.out.println("--------------------In getEbhInfo Controller--------------------");
        int Stu_id = Integer.parseInt(request.getParameter("Stu_id"));
        List<Map<String, Object>> DataList = iEbhService.getStuEbhInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------");
        System.out.println(ResultJSON);
        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertEbhInfo")
    public void insertEbhInfo(HttpServletResponse response, HttpServletRequest request,EbhEntity ebhEntity) throws JSONException{
        System.out.println("--------------------In insertEbhInfo Controller--------------------");
        ebhEntity.setEbhDoctorId(Integer.parseInt(request.getParameter("Ebh_doctor_id")));
        ebhEntity.setEbhOperationTime( LocalDateTime.now() );
        ebhEntity.setEbhStammer(request.getParameter("Ebh_stammer"));
        ebhEntity.setEbhThroat(request.getParameter("Ebh_throat"));
        ebhEntity.setEbhIdea( request.getParameter("Ebh_idea") );
        ebhEntity.setEbhAll( "1" );
        ebhEntity.setStuId( Integer.parseInt( request.getParameter("Stu_id") ));
        ebhEntity.setEbhError( "0" );
        iEbhService.insertStuEbhInfo(ebhEntity);

        JSONObject jsonObject = JSONUtil.CreateJSON(0,"ok",0,null);

        System.out.println("--------------------JSON--------------------");
        System.out.println(jsonObject);
        JSONUtil.JSONToResponse(response, jsonObject);
    }
}
