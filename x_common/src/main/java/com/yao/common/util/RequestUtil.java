package com.yao.common.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 妖妖
 * @date 10:37 2021/3/5
 */


public class RequestUtil {

    public static Map<String,String> getSignMap(HttpServletRequest request){
        Map<String,String> param = new HashMap<>();
        Enumeration penum = (Enumeration) request.getParameterNames();
        while(penum.hasMoreElements()){
            String key=(String) penum.nextElement();
            String value=request.getParameter(key);
            param.put(key,value);
            //sign和uploadFile不参与 值为空也不参与
//            if(!pKey.equals("sign")&&!pKey.equals("uploadFile")
//                    && StringUtils.isNotBlank(value)){
//                param.put(pKey,value);
//            }
        }
        return param;
    }



}
