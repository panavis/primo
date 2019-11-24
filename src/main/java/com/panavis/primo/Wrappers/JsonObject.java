package com.panavis.primo.Wrappers;

import org.json.JSONObject;

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
}
