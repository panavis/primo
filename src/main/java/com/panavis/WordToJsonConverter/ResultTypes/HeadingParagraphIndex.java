package com.panavis.WordToJsonConverter.ResultTypes;

public class HeadingParagraphIndex {

    private String headingName;
    private int paragraphIndex;

    public HeadingParagraphIndex(String headingName, int paragraphIndex) {
        this.headingName = headingName;
        this.paragraphIndex = paragraphIndex;
    }

    public String getHeadingName() {
        return this.headingName;
    }

    public int getParagraphIndex() {
        return this.paragraphIndex;
    }
}
