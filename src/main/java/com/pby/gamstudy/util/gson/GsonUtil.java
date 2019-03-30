package com.pby.gamstudy.util.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    public static <T> T getValues(String json, String key, Class<? extends T> clazz) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        if (getCode(jsonObject) == 200) {
            JsonElement jsonElement = jsonObject.get(key);
            if (jsonElement != null) {
                return GSON.fromJson(jsonElement, clazz);
            }
        }
        return null;
    }

    public static int getCode(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        return getCode(jsonObject);
    }

    public static int getCode(JsonObject jsonObject) {
        return jsonObject.get("code").getAsInt();
    }
}
