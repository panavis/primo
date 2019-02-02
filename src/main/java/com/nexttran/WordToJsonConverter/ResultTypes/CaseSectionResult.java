package com.nexttran.WordToJsonConverter.ResultTypes;

import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;

public class CaseSectionResult {

    private JsonObject sectionContent;
    private int nextParagraph;

    public CaseSectionResult(JsonObject sectionContent, int nextParagraph) {
        this.sectionContent = sectionContent;
        this.nextParagraph = nextParagraph;
    }

    public JsonObject getSectionContent() {
        return this.sectionContent;
    }

    public int getNextParagraph() {
        return this.nextParagraph;
    }
}
