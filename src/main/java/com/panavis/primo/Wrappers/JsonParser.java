package com.panavis.primo.Wrappers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.IOException;

public class JsonParser {

    private JsonObject jsonObject;

    private JsonParser() {
        this.jsonObject = new JsonObject();
    }

    public static JsonObject parseJsonFile(String filePath) {
        JSONObject unwrappedJsonObject = new JSONObject();
        try {
            JSONTokener jsonTokener = new JSONTokener(new FileReader(filePath));
            unwrappedJsonObject = new JSONObject(jsonTokener);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseJsonObject(unwrappedJsonObject);
    }

    static JsonObject parseJsonObject(JSONObject unwrappedJsonObject) {
        JsonParser wrappedJsonObject = new JsonParser();
        String jsonKey = (String) unwrappedJsonObject.keySet().toArray()[0];

        try {
            wrappedJsonObject.parseNestedUnwrappedJsonArray(jsonKey, unwrappedJsonObject);
        }
        catch (ClassCastException e) {
            wrappedJsonObject.addNameStringPairToJsonObject(jsonKey, unwrappedJsonObject);
        }

        return wrappedJsonObject.jsonObject;
    }

    private void parseNestedUnwrappedJsonArray(String jsonKey,
                                               JSONObject unwrappedJsonObject) {
        JsonArray wrappedJsonArray = new JsonArray();
        JSONArray jsonArray = (JSONArray) unwrappedJsonObject.get(jsonKey);
        for (Object value : jsonArray) {
            parseJsonObjectOrStringValue(wrappedJsonArray, value);
        }
        this.jsonObject.addNameValuePair(jsonKey, wrappedJsonArray);
    }

    private void parseJsonObjectOrStringValue(JsonArray wrappedJsonArray, Object value) {
        try {
            JsonObject nestedUnwrappedJsonObject = JsonParser.parseJsonObject((JSONObject) value);
            wrappedJsonArray.putValue(nestedUnwrappedJsonObject);
        }
        catch (ClassCastException e) {
            wrappedJsonArray.putValue(value.toString());
        }
    }

    private void addNameStringPairToJsonObject(String jsonKey,
                                               JSONObject unwrappedJsonObject) {
        String value = unwrappedJsonObject.get(jsonKey).toString();
        this.jsonObject.addNameValuePair(jsonKey, value);
    }
}