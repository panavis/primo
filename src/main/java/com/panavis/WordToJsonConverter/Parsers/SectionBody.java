package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Style.UnitNumbering;
import com.panavis.WordToJsonConverter.Style.WordParagraph;

public class SectionBody implements ISection {

    private WordParagraph wordParagraph;
    UnitNumbering currentNumbering;

    SectionBody(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    void setCurrentNumbering(UnitNumbering unitNumbering) {
        this.currentNumbering = unitNumbering;
    }

    public boolean isStillInOneSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraphText(paragraphIndex);
        String nextNumbering = currentNumbering.next;
        return !(text.startsWith(nextNumbering));
    }
}
