package com.company.project.controller;

import com.company.project.common.utils.JSONUtil;
import com.company.project.entity.AssayEntity;
import com.company.project.service.HttpSessionService;
import com.company.project.service.IAssayService;
import com.company.project.service.IStudentService;
import com.company.project.service.StuTestService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 化验 前端控制器
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/dv")
public class DVController {

    @Resource
    private IStudentService iStudentService;

    @Resource
    private StuTestService stuTestService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getStuCount")
    public void getStuCount(HttpServletResponse response) throws JSONException{
        System.out.println("--------------------In getStuCount Controller--------------------");

        List<Map<String, Object>> DataList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("StuCount", iStudentService.count());
        DataList.add(map);

        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getTestedStuCount")
    public void getTestedStuCount(HttpServletResponse response) throws JSONException{
        System.out.println("--------------------In getTestedStuCount Controller--------------------");

        List<Map<String, Object>> DataList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("TestedStuCount", iStudentService.getTestedStuCount());
        DataList.add(map);

        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getTestingStuCount")
    public void getTestingStuCount(HttpServletResponse response) throws JSONException{
        System.out.println("--------------------In getTestedStuCount Controller--------------------");

        List<Map<String, Object>> DataList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("TestingStuCount", stuTestService.getTestingStuCount());
        DataList.add(map);

        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

    @RequestMapping("/getErrorStuCount")
    public void getErrorStuCount(HttpServletResponse response) throws JSONException{
        System.out.println("--------------------In getTestedStuCount Controller--------------------");

        String sql;
        String[] TablesName = {"Eye", "EBH", "Tooth", "Surgery", "Blood", "Internal", "Assay", "Chest", "Other", "Manage"};
        Set<Integer> dataSet = new HashSet<>();
        for (String tableName : TablesName) {
            sql = "select Stu_id from " + tableName + " where " + tableName + "_error = '1'";
            dataSet.addAll(jdbcTemplate.queryForList(sql, Integer.class));
        }

        List<Map<String, Object>> DataList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("ErrorStuCount", dataSet.size());
        DataList.add(map);

        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);

        System.out.println("--------------------JSON--------------------\n"+ResultJSON);

        JSONUtil.JSONToResponse(response, ResultJSON);
    }

}
