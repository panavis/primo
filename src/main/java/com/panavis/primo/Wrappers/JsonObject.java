package com.panavis.primo.Wrappers;

import static com.panavis.primo.Constants.Keywords.*;
import com.panavis.primo.ResultTypes.SectionResult;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.Map;
import java.util.Set;

public class JsonObject {

    private JSONObject jsonObject;

    public JsonObject() {
        this.jsonObject = new JSONObject();
    }

    public void addNameValuePair(String key, String value) {
        this.jsonObject.put(key, value);
    }

    public void addNameValuePair(String key, JsonArray value) {
        this.jsonObject.put(key, value);
    }

    public boolean hasKey(String key) {
        return this.jsonObject.has(key);
    }

    public String getStringByKey(String key) {
        return (String) this.jsonObject.get(key);
    }

    public JsonArray getArrayByKey(String key) {
        return (JsonArray) this.jsonObject.get(key);
    }

    public Set<String> getKeys() {
        return this.jsonObject.keySet();
    }

    @Override
    public String toString() {
        return this.jsonObject.toString();
    }

    public static org.json.simple.JSONObject toParsedGson(Map<String, SectionResult> parsedCase) {
        org.json.simple.JSONObject title = getTitle(parsedCase.get(TITLE).getSectionContent());
        org.json.simple.JSONObject parties = getParties(parsedCase.get(PARTIES).getSectionContent());
        org.json.simple.JSONObject subjectMatter = getSubjectMatter(parsedCase.get(SUBJECT_MATTER).getSectionContent());
        org.json.simple.JSONObject body = getBody(parsedCase.get(CASE_BODY).getSectionContent());
        org.json.simple.JSONObject wholeCase = new org.json.simple.JSONObject();
        JSONArray caseArray = new JSONArray();
        caseArray.add(title);
        caseArray.add(parties);
        caseArray.add(subjectMatter);
        caseArray.add(body);
        wholeCase.put(CASE, caseArray);
        return wholeCase;
    }

    private static org.json.simple.JSONObject getTitle(JsonObject title) {
        String titleString = title.getStringByKey(TITLE);
        org.json.simple.JSONObject newObject = new org.json.simple.JSONObject();
        newObject.put(TITLE, titleString);
        return newObject;
    }

    private static org.json.simple.JSONObject getParties(JsonObject parties) {
        return JsonObject.getGsonObject(parties);
    }

    private static org.json.simple.JSONObject getSubjectMatter(JsonObject subjectMatter) {
        return JsonObject.getGsonObject(subjectMatter);
    }

    private static org.json.simple.JSONObject getBody(JsonObject body) {
        return JsonObject.getGsonObject(body);
    }

    static org.json.simple.JSONObject getGsonObject(JsonObject json) {
        org.json.simple.JSONObject gson = new  org.json.simple.JSONObject();
        String jsonKey = (String) json.getKeys().toArray()[0];

        try {
            JSONArray gsonArray = new JSONArray();
            JsonArray jsonArray = json.getArrayByKey(jsonKey);
            for (Object value : jsonArray.toList()) {
                try {
                    org.json.simple.JSONObject nestGson = JsonObject.getGsonObject((JsonObject) value);
                    gsonArray.add(nestGson);
                }
                catch (ClassCastException e) {
                    gsonArray.add(value.toString());
                }
            }
            gson.put(jsonKey, gsonArray);
        } catch (ClassCastException e) {
            String value = json.getStringByKey(jsonKey);
            gson.put(jsonKey, value);
        }
        return gson;
    }

}
