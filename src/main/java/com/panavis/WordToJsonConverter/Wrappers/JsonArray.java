package com.panavis.WordToJsonConverter.Wrappers;

import org.json.JSONArray;

public class JsonArray {

    private JSONArray jsonArray;

    public JsonArray() {
        this.jsonArray = new JSONArray();
    }

    public void putValue(String value) {
        this.jsonArray.put(value);
    }

    public void putValue(JsonObject jsonObject) {
        this.jsonArray.put(jsonObject);
    }

    public int getSize() {
        return this.jsonArray.length();
    }

    public JsonObject getJsonObjectByIndex(int index) {
        return (JsonObject) this.jsonArray.get(index);
    }

    @Override
    public String toString() {
        return this.jsonArray.toString();
    }
}