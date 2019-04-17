package com.panavis.primo.ResultTypes;

import com.panavis.primo.Wrappers.JsonObject;

public class SectionResult {

    private JsonObject sectionContent;
    private int nextParagraph;

    public SectionResult(JsonObject sectionContent, int nextParagraph) {
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
