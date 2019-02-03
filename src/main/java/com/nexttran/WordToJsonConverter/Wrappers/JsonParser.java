package com.nexttran.WordToJsonConverter.Wrappers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
            JSONParser jsonParser = new JSONParser();
            unwrappedJsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return parseJsonObject(unwrappedJsonObject);
    }

    static JsonObject parseJsonObject(JSONObject unwrappedJsonObject) {
        JsonParser wrappedJsonObject = new JsonParser();
        String jsonKey = (String) unwrappedJsonObject.keySet().toArray()[0];

        try {
            wrappedJsonObject.addNameStringPairToJsonObject(jsonKey, unwrappedJsonObject);
        }
        catch (ClassCastException e) {
            wrappedJsonObject.parseNestedUnwrappedJsonArray(jsonKey, unwrappedJsonObject);
        }

        return wrappedJsonObject.jsonObject;
    }

    private void addNameStringPairToJsonObject(String jsonKey,
                                              JSONObject unwrappedJsonObject) {
        String value = (String) unwrappedJsonObject.get(jsonKey);
        this.jsonObject.addNameValuePair(jsonKey, value);
    }

    private void parseNestedUnwrappedJsonArray(String jsonKey,
                                               JSONObject unwrappedJsonObject) {
        JsonArray wrappedJsonArray = new JsonArray();
        JSONArray jsonArray = (JSONArray) unwrappedJsonObject.get(jsonKey);
        Object[] regularArray = jsonArray.toArray();
        for (Object value : regularArray) {
            parseJsonObjectOrStringValue(wrappedJsonArray, value);
        }
        this.jsonObject.addNameValuePair(jsonKey, wrappedJsonArray);
    }

    private void parseJsonObjectOrStringValue(JsonArray wrappedJsonArray, Object value) {
        try {
            JsonObject nestedUnwrappedJsonObject = JsonParser.parseJsonObject((JSONObject) value);
            wrappedJsonArray.addValue(nestedUnwrappedJsonObject);
        }
        catch (ClassCastException e) {
            wrappedJsonArray.addValue(value.toString());
        }
    }
}