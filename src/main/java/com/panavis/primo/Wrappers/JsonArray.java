package com.panavis.primo.Wrappers;

import com.panavis.primo.Utils.StringFormatting;
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

    public void putValueAtIndex(int index, String value) {
        this.jsonArray.put(index, value);
    }

    public void putValueAtIndex(int index, JsonObject jsonObject) {
        this.jsonArray.put(index, jsonObject);
    }

    public static JsonArray escapeSubsections(JsonArray subsections) {
        JsonArray escapedSubsections = new JsonArray();
        for (int i = 0; i < subsections.getSize(); i++) {
            JsonObject subsection = subsections.getJsonByIndex(i);
            JsonObject escaped = getEscapedSubsection(subsection);
            escapedSubsections.putValue(escaped);
        }
        return escapedSubsections;
    }

    private static JsonObject getEscapedSubsection(JsonObject subsection) {
        String heading = (String) subsection.getKeys().toArray()[0];
        JsonArray content = subsection.getArrayByKey(heading);
        JsonArray escapedContent = new JsonArray();
        for (int i = 0; i < content.getSize(); i++) {
            String paragraph = content.getStringByIndex(i);
            escapedContent.putValue(StringFormatting.getJsonString(paragraph));
        }
        String escapedHeading = StringFormatting.getJsonString(heading);
        JsonObject escapedSubsection = new JsonObject();
        escapedSubsection.addNameValuePair(escapedHeading, escapedContent);
        return escapedSubsection;
    }


    @Override
    public String toString() {
        return this.jsonArray.toString();
    }
}
