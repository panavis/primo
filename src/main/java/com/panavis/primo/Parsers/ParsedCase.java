package com.panavis.primo.Parsers;

import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.Map;

import static com.panavis.primo.Constants.Keywords.*;
import static com.panavis.primo.Constants.Keywords.CASE_TITLE;

public class ParsedCase {

    private final HashMap<String, SectionResult> parsedCase;
    private boolean missedSomeParagraphs;

    public ParsedCase() {
        this.parsedCase = new HashMap<>();
        this.missedSomeParagraphs = false;
    }

    public void set(String sectionName, SectionResult sectionResult) {
        this.parsedCase.put(sectionName, sectionResult);
    }

    public SectionResult get(String sectionName) {
        return this.parsedCase.get(sectionName);
    }

    public boolean hasSection(String sectionName) {
        return this.parsedCase.containsKey(sectionName);
    }

    public void setSkippedParagraphs(boolean skipped) {
        boolean hasAlreadySkipped = this.missedSomeParagraphs;
        if (!hasAlreadySkipped) {
            this.missedSomeParagraphs = skipped;
        }
    }

    public boolean didSkipParagraphs() {
        return this.missedSomeParagraphs;
    }

    public Map<String, SectionResult> getParsedCaseAsMap() {
        return this.parsedCase;
    }

    public static String toJsonString(Map<String, SectionResult> parsedCase) {
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
        caseArray.add(parties);
        caseArray.add(subjectMatter);
        caseArray.add(preBody);
        caseArray.add(body);
        caseArray.add(closing);
        caseArray.add(panel);
        wholeCase.put(CASE, caseArray);
        return wholeCase.toJSONString();
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
        return ParsedCase.getGsonObject(parties);
    }

    private static org.json.simple.JSONObject getSubjectMatter(JsonObject subjectMatter) {
        return ParsedCase.getGsonObject(subjectMatter);
    }
    private static org.json.simple.JSONObject getPreBody(JsonObject preBody) {
        return ParsedCase.getGsonObject(preBody);
    }

    private static org.json.simple.JSONObject getBody(JsonObject body) {
        return ParsedCase.getGsonObject(body);
    }

    private static org.json.simple.JSONObject getCaseClosing(JsonObject closing) {
        return ParsedCase.getGsonObject(closing);
    }

    private static org.json.simple.JSONObject getCasePanel(JsonObject panel) {
        return ParsedCase.getGsonObject(panel);
    }

    private static org.json.simple.JSONObject getGsonObject(JsonObject json) {
        org.json.simple.JSONObject gson = new  org.json.simple.JSONObject();
        String jsonKey = (String) json.getKeys().toArray()[0];

        try {
            JSONArray gsonArray = new JSONArray();
            JsonArray jsonArray = json.getArrayByKey(jsonKey);
            for (Object value : jsonArray.toList()) {
                try {
                    org.json.simple.JSONObject nestGson = ParsedCase.getGsonObject((JsonObject) value);
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
