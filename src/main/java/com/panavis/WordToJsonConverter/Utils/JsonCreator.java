package com.panavis.WordToJsonConverter.Utils;

import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

public class JsonCreator {
    public static JsonObject getJsonObject(String name, String value){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addNameValuePair(name, StringFormatting.removeStartingOrTrailingColons(value));
        return jsonObject;
    }

    public static JsonObject getJsonObject(String name, JsonArray value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addNameValuePair(name, value);
        return jsonObject;
    }

    public static JsonArray getJsonArrayWithString(String value) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.putValue(StringFormatting.removeStartingOrTrailingColons(value).trim());
        return jsonArray;
    }

    public static JsonArray getJsonArrayFromStringArray(String[] content) {
        JsonArray jsonArray = new JsonArray();
        for (String subContent : content)
            jsonArray.putValue(StringFormatting.removeStartingOrTrailingColons(subContent).trim());
        return jsonArray;
    }
}
