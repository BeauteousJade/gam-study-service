package com.pby.gamstudy.util.gson;

import com.google.gson.Gson;
import com.pby.gamstudy.util.gson.type.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.List;

public class GsonUtil {

    private static final Gson GSON = new Gson();

    public static <T> T jsonToObject(String json, Class<? extends T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static <T> List<T> jsonToArray(String json, Class<? extends T> clazz) {
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        return GSON.fromJson(json, listType);
    }

    public static String objectToJson(Object object) {
        return GSON.toJson(object);
    }
}
