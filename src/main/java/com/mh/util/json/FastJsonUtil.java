package com.mh.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

public class FastJsonUtil {

    private static final SerializeConfig CONFIG;
    private static final SerializerFeature[] FEATURES = {SerializerFeature.WriteMapNullValue};

    static {
        CONFIG = SerializeConfig.getGlobalInstance();
    }

    private FastJsonUtil() {}

    public static String toJson(Object object){
        return JSON.toJSONString(object, CONFIG);
    }

    public static String toJsonFeatures(Object object){
        return JSON.toJSONString(object, CONFIG, FEATURES);
    }

    public static <T> T toBean(String text, Class<T> clasz){
        return JSON.parseObject(text, clasz);
    }

    public static JSONObject toBean(String text){
        return (JSONObject) JSON.parse(text);
    }

    public static <T> List<T> toList(String text, Class<T> clasz){
        return JSON.parseArray(text, clasz);
    }
}
