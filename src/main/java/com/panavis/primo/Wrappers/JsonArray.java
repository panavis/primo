package com.panavis.primo.Wrappers;

import org.json.JSONArray;

import java.util.List;

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

    public String getStringByIndex(int index) {
        return (String) this.jsonArray.get(index);
    }

    public JsonObject getJsonByIndex(int index) {
        return (JsonObject) this.jsonArray.get(index);
    }

    public List<Object> toList() {
        return this.jsonArray.toList();
    }

    public void putValueAtIndex(int index, JsonObject jsonObject) {
        this.jsonArray.put(index, jsonObject);
    }

    @Override
    public String toString() {
        return this.jsonArray.toString();
    }
}
