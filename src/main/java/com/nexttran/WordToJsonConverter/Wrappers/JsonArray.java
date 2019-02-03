package com.nexttran.WordToJsonConverter.Wrappers;

import org.json.simple.JSONArray;

public class JsonArray {

    private JSONArray jsonArray;

    public JsonArray() {
        this.jsonArray = new JSONArray();
    }

    public void addValue(String value) {
        this.jsonArray.add(value);
    }

    public void addValue(JsonObject jsonObject) {
        this.jsonArray.add(jsonObject);
    }

    public int getSize() {
        return this.jsonArray.size();
    }

    public JsonObject getJsonObjectByIndex(int index) {
        return (JsonObject) this.jsonArray.get(index);
    }

    @Override
    public String toString() {
        return this.jsonArray.toString();
    }
}
