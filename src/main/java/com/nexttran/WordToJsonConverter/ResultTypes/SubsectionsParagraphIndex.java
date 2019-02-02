package com.nexttran.WordToJsonConverter.ResultTypes;

import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;

public class SubsectionsParagraphIndex {
    private JsonArray subsections;
    private int paragraphIndex;

    SubsectionsParagraphIndex(JsonArray subsections, int paragraphIndex) {
        this.subsections = subsections;
        this.paragraphIndex = paragraphIndex;
    }

    public JsonArray getSubsections() {
        return this.subsections;
    }

    public int getParagraphIndex() {
        return this.paragraphIndex;
    }
}
