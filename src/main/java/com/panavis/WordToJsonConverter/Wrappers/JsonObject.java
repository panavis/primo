package com.panavis.WordToJsonConverter.Wrappers;

import org.json.JSONObject;

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

    @Override
    public String toString() {
        return this.jsonObject.toString();
    }
}
