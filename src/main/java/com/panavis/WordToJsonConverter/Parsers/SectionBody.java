package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Style.UnitNumbering;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;

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
        return !isTextCapitalizedAndHasSameStyle(text, currentStyle);

    }

    private boolean nextNumberingIsAvailable(String nextNumbering) {
        return !nextNumbering.trim().isEmpty();
    }

    private boolean paragraphHasNextNumbering(String text, String nextNumbering) {
        return text.startsWith(nextNumbering);
    }

    private boolean isTextCapitalizedAndHasSameStyle(String text, String currentStyle) {
        return StringFormatting.isTextCapitalized(text) &&
                currentStyle.equals(currentNumbering.style);
    }

    Subsection setCurrentNumbering(UnitNumbering unitNumbering) {
        this.currentNumbering = unitNumbering;
        return this;
    }
}
