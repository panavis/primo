package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Style.UnitNumbering;
import com.panavis.WordToJsonConverter.Style.WordParagraph;

class SectionBody extends Subsection {

    private UnitNumbering currentNumbering;

    SectionBody(WordParagraph wordParagraph, int startParagraph) {
        super(wordParagraph, startParagraph);
    }

    @Override
    boolean isStillInOneSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraphText(paragraphIndex);
        String nextNumbering = currentNumbering.next;
        return !(text.startsWith(nextNumbering));
    }

    Subsection setCurrentNumbering(UnitNumbering unitNumbering) {
        this.currentNumbering = unitNumbering;
        return this;
    }
}
