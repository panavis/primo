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
        org.json.simple.JSONObject wordPath = getWordDocPath(parsedCase.get(WORD_DOC_PATH).getSectionContent());
        org.json.simple.JSONObject title = getTitle(parsedCase.get(CASE_TITLE).getSectionContent());
        org.json.simple.JSONObject parties = getParties(parsedCase.get(CASE_PARTIES).getSectionContent());
        org.json.simple.JSONObject subjectMatter = getSubjectMatter(parsedCase.get(SUBJECT_MATTER).getSectionContent());
        org.json.simple.JSONObject preBody = getPreBody(parsedCase.get(PRE_CASE_BODY).getSectionContent());
        org.json.simple.JSONObject body = getBody(parsedCase.get(CASE_BODY).getSectionContent());
        org.json.simple.JSONObject closing = getCaseClosing(parsedCase.get(CASE_CLOSING).getSectionContent());
        org.json.simple.JSONObject panel = getCasePanel(parsedCase.get(CASE_PANEL).getSectionContent());
        org.json.simple.JSONObject wholeCase = new org.json.simple.JSONObject();
        JSONArray caseArray = new JSONArray();
        caseArray.add(wordPath);
        caseArray.add(title);
        caseArray.add(parties.toJSONString());
        caseArray.add(subjectMatter.toJSONString());
        caseArray.add(preBody.toJSONString());
        caseArray.add(body.toJSONString());
        caseArray.add(closing.toJSONString());
        caseArray.add(panel.toJSONString());
        wholeCase.put(CASE, caseArray);
        return wholeCase;
    }

    private static org.json.simple.JSONObject getWordDocPath(JsonObject docPath) {
        String formattedPath = docPath.getStringByKey(WORD_DOC_PATH);
        org.json.simple.JSONObject newObject = new org.json.simple.JSONObject();
        newObject.put(WORD_DOC_PATH, formattedPath);
        return newObject;
    }


    private static org.json.simple.JSONObject getTitle(JsonObject title) {
        String titleString = title.getStringByKey(CASE_TITLE);
        org.json.simple.JSONObject newObject = new org.json.simple.JSONObject();
        newObject.put(CASE_TITLE, titleString);
        return newObject;
    }

    private static org.json.simple.JSONObject getParties(JsonObject parties) {
        return JsonObject.getGsonObject(parties);
    }

    private static org.json.simple.JSONObject getSubjectMatter(JsonObject subjectMatter) {
        return JsonObject.getGsonObject(subjectMatter);
    }
    private static org.json.simple.JSONObject getPreBody(JsonObject preBody) {
        return JsonObject.getGsonObject(preBody);
    }

    private static org.json.simple.JSONObject getBody(JsonObject body) {
        return JsonObject.getGsonObject(body);
    }

    private static org.json.simple.JSONObject getCaseClosing(JsonObject closing) {
        return JsonObject.getGsonObject(closing);
    }

    private static org.json.simple.JSONObject getCasePanel(JsonObject panel) {
        return JsonObject.getGsonObject(panel);
    }

    private static org.json.simple.JSONObject getGsonObject(JsonObject json) {
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
