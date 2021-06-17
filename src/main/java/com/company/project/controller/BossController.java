package com.company.project.controller;

import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.BossEntity;
import com.company.project.service.HttpSessionService;
import com.company.project.service.IBossService;
import com.company.project.service.StuTestService;
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
 * 医院领导信息录入 前端控制器
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/boss")
public class BossController {
    @Resource
    private IBossService iBossService;

    @Resource
    private StuTestService stuTestService;

    @Resource
    private HttpSessionService httpSessionService;

    @RequestMapping("/getStuList")
    public void getList(HttpServletResponse response, @RequestParam(name = "Stu_id", required = false, defaultValue = "-1") int Stu_id) throws JSONException {
        System.out.println("--------------------In getStuList Controller--------------------");

        List<Map<String, Object>> DataList = iBossService.getStuInfoList(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getStuTestInfo")
    public void getStuTestInfo(HttpServletResponse response, @RequestParam(name = "Stu_id") int Stu_id) throws JSONException, IllegalAccessException {
        System.out.println("--------------------In getStuTestInfo Controller--------------------");

        List<Map<String, Object>> DataList = stuTestService.getStuTestInfo(Stu_id, true);
        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getBossInfo")
    public void getBossInfo(HttpServletResponse response, @RequestParam(name = "Stu_id") int Stu_id) throws JSONException{
        System.out.println("--------------------In getBossInfo Controller--------------------");

        List<Map<String, Object>> DataList = iBossService.getStuBossInfo(Stu_id);
        JSONObject ResultJSON = JSONUtil.CreateJSON(DataList.get(0).get("code") == null ? 0 : -1,
                DataList.get(0).get("message") == null ? "ok" : "存在已驳回状态的表单", DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/insertBossInfo")
    public void insertBossInfo(HttpServletResponse response, BossEntity bossEntity) throws JSONException{
        System.out.println("--------------------In insertBossInfo Controller--------------------");

        bossEntity.setBossDoctorId(Integer.parseInt(httpSessionService.getCurrentUserId()));
        bossEntity.setBossAll("1");
        bossEntity.setBossError("0");
        bossEntity.setBossOperationTime(LocalDateTime.now());
        iBossService.insertStuBossInfo(bossEntity);

        JSONObject jsonObject = JSONUtil.CreateJSON(0,"ok",0,null);

        System.out.println("--------------------JSON--------------------\n"+jsonObject);

        JSONUtil.JSONToResponse(response, jsonObject);
    }

    @RequestMapping("/rejectTest")
    public void rejectTest(HttpServletResponse response,
                           @RequestParam(name = "Stu_id") int Stu_id,
                           @RequestParam(name = "Table_index") int Table_index) throws JSONException{
        System.out.println("--------------------In rejectTest Controller--------------------");
        iBossService.rejectTestReport(Stu_id, Table_index);

        JSONObject jsonObject = JSONUtil.CreateJSON(0,"ok",0,null);

        System.out.println("--------------------JSON--------------------\n"+jsonObject);

        JSONUtil.JSONToResponse(response, jsonObject);
    }
}
