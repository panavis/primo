package com.nexttran.WordToJsonConverter.ResultTypes;

public class TextParagraphIndex {

    private String paragraphText;
    private int paragraphIndex;

    public TextParagraphIndex(String paragraphText, int paragraphIndex) {
        this.paragraphText = paragraphText;
        this.paragraphIndex = paragraphIndex;
    }

    public String getParagraphText() {
        return this.paragraphText;
    }

    public int getParagraphIndex() {
        return this.paragraphIndex;
    }
}
