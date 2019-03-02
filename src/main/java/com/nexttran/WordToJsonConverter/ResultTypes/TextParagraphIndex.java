package com.nexttran.WordToJsonConverter.ResultTypes;

import java.util.List;

public class TextParagraphIndex {

    private String subsectionParagraphs;
    private int paragraphIndex;

    public TextParagraphIndex(String subsectionParagraphs, int paragraphIndex) {
        this.subsectionParagraphs = subsectionParagraphs;
        this.paragraphIndex = paragraphIndex;
    }

    public String getSubsectionParagraphs() {
        return this.subsectionParagraphs;
    }

    public int getParagraphIndex() {
        return this.paragraphIndex;
    }
}
