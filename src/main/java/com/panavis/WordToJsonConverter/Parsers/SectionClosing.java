package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Style.WordParagraph;

public class SectionClosing extends Section {

    SectionClosing(WordParagraph wordParagraph) {
        super(wordParagraph);
    }

    @Override
    boolean isStillInOneSubsection(int paragraphIndex) {
        return false;
    }
}
