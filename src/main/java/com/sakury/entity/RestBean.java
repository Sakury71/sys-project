package com.sakury.entity;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

/**
 * @author Zhang
 * @package com.sakury.entity
 * @date 2024/7/19 17:34
 */
public record RestBean <T>(int code,T data,String message){
    public static <T>RestBean<T> success(T data){
        return new RestBean<>(200,data,"请求成功！");
    }
    public static <T>RestBean<T>success(){
        return success(null);
    }
    public static <T> RestBean<T> failure(int code,String message){
        return new RestBean<>(code,null,message);
    }
    public String asJsonString(){
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}
