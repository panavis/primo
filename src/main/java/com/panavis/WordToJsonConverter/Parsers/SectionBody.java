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
        if (nextNumberingIsAvailable(nextNumbering))
            return !paragraphHasNextNumbering(text, nextNumbering);
        String currentStyle = wordParagraph.getUnitNumbering(paragraphIndex).style;
        return !(currentStyle.equals(currentNumbering.style));

    }

    private boolean nextNumberingIsAvailable(String nextNumbering) {
        return !nextNumbering.trim().isEmpty();
    }

    private boolean paragraphHasNextNumbering(String text, String nextNumbering) {
        return text.startsWith(nextNumbering);
    }

    Subsection setCurrentNumbering(UnitNumbering unitNumbering) {
        this.currentNumbering = unitNumbering;
        return this;
    }
}
