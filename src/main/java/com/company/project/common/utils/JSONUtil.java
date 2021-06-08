package com.company.project.common.utils;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class JSONUtil {

    //将返回数据整合为JSON格式
    public static JSONObject CreateJSON(int Code, String Message,
                                            int Count, List<Map<String, Object>> DataList) throws JSONException{

        JSONObject JsonObj=new JSONObject();

        JsonObj.put("code", Code);
        JsonObj.put("msg", Message);
        JsonObj.put("count", Count);
        JsonObj.put("data", DataList);

        return JsonObj;

    }

    public static void JSONToResponse(HttpServletResponse response, JSONObject ResultJSON){
        response.setContentType("text/html; charset=UTF-8");
        try {
            response.getWriter().print(ResultJSON);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
