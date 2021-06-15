package com.company.project.controller;

import com.company.project.common.utils.JSONUtil;
import com.company.project.common.utils.PDFHelper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * PDFController
 * </p>
 *
 * @author wyf
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/PDF")
public class PDFController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getPDF")
//    public void getPDF(HttpServletResponse response, @RequestParam(name = "Stu_id") int Stu_id,
//                            @RequestParam(name = "OutputPath") String OutputPath) throws JSONException{
    public void getPDF(HttpServletResponse response) throws JSONException{
        System.out.println("--------------------In getPDF Controller--------------------");
        int Stu_id = 1;
        String OutputPath = "D:\\Program Files (x86)\\Project\\WebProject\\zwens-springboot-manager-simple\\springboot-manager\\src\\main\\resources\\PDF\\Output.pdf";

        if(jdbcTemplate.queryForObject("select Stu_test_all from Student where Stu_id = "+Stu_id,String.class).equals("0"))
        {
            JSONObject ResultJSON = JSONUtil.CreateJSON(-1,"体检表未填写完毕",0,null);
            System.out.println("--------------------JSON--------------------\n"+ResultJSON);
            JSONUtil.JSONToResponse(response, ResultJSON);
        }
        String sql;
        String[] TablesName = {"Student", "Eye", "EBH", "Tooth", "Surgery", "Blood", "Internal", "Assay",
                "Chest", "Other", "Manage", "Boss"};
        Map<String, Object> EntitiesMap = new HashMap<>();
        for(int i=0;i<TablesName.length;++i)
        {
            sql = "select * from "+TablesName[i]+" where Stu_id = "+Stu_id;
            EntitiesMap.putAll(jdbcTemplate.queryForMap(sql));
        }
        PDFHelper.fillPDFTemplate(PDFHelper.preProcess(EntitiesMap), OutputPath);

//        JSONObject ResultJSON = JSONUtil.CreateJSON(0,"ok",DataList.size(),DataList);
//        System.out.println("--------------------JSON--------------------\n"+ResultJSON);
//
//        JSONUtil.JSONToResponse(response, ResultJSON);
    }

}
