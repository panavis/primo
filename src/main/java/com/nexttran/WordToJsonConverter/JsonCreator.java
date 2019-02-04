package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;

class JsonCreator {
    static JsonObject getJsonObject(String name, String value){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addNameValuePair(name, StringFormatting.removeStartingOrTrailingColons(value));
        return jsonObject;
    }

    static JsonObject getJsonObject(String name, JsonArray value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addNameValuePair(name, value);
        return jsonObject;
    }

    static JsonArray getJsonArrayWithString(String value) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.addValue(StringFormatting.removeStartingOrTrailingColons(value).trim());
        return jsonArray;
    }

    static JsonArray getJsonArrayFromStringArray(String[] content) {
        JsonArray jsonArray = new JsonArray();
        for (String subContent : content)
            jsonArray.addValue(StringFormatting.removeStartingOrTrailingColons(subContent).trim());
        return jsonArray;
    }
}
