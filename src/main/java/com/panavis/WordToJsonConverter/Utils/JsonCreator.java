package com.panavis.WordToJsonConverter.Utils;

import com.panavis.WordToJsonConverter.Constants.Format;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

public class JsonCreator {
    public static JsonObject getJsonObject(String name, String value){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addNameValuePair(name, value.trim());
        return jsonObject;
    }

    public static JsonObject getJsonObject(String name, JsonArray value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addNameValuePair(name, value);
        return jsonObject;
    }

    public static JsonArray getJsonArrayFromStringArray(String[] content) {
        JsonArray jsonArray = new JsonArray();
        for (String subContent : content)
            if (!subContent.equals(Format.EMPTY_STRING))
                jsonArray.putValue(subContent.trim());
        return jsonArray;
    }
}
