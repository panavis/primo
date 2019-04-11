package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Style.Numbering.UnitNumbering;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import static com.panavis.WordToJsonConverter.Utils.StringFormatting.*;

class SectionBody extends Section {

    private UnitNumbering currentNumbering;
    private SectionClosing sectionClosing;

    SectionBody(WordParagraph wordParagraph) {
        super(wordParagraph);
        this.sectionClosing = new SectionClosing(wordParagraph);
    }

    @Override
    boolean isStillInOneSubsection(int paragraphIndex) {
        if (isCaseClosing(paragraphIndex)) return false;
        String text = wordParagraph.getParagraphText(paragraphIndex);
        UnitNumbering paragraphNumbering = wordParagraph.getUnitNumbering(paragraphIndex);
        if (!currentNumbering.realNext.equals(EMPTY_STRING) &&
                paragraphHasNumbering(paragraphNumbering.current)) {
            return !matchesNextNumbering(text, currentNumbering.realNext);
        }
        return !hasSameBodyHeadingFormat(paragraphIndex, text);
    }

    private boolean paragraphHasNumbering(String currentNumbering) {
        return !currentNumbering.trim().isEmpty();
    }

    private boolean matchesNextNumbering(String text, String nextNumbering) {
        return text.startsWith(nextNumbering);
    }

    private boolean hasSameBodyHeadingFormat(int paragraphIndex, String text) {
        String currentStyle = wordParagraph.getUnitNumbering(paragraphIndex).style;
        boolean hasSameStyle = isTextCapitalizedAndHasSameStyle(text, currentStyle);
        boolean nonDynamicNumbering = hasNonDynamicNumbering(paragraphIndex, text, hasSameStyle);
        boolean noNumbering = hasSameStyle &&
                wordParagraph.isBeginningUnderlined(paragraphIndex);
        return nonDynamicNumbering || noNumbering;
    }

    private boolean hasNonDynamicNumbering(int paragraphIndex, String text, boolean hasSameStyle) {
        boolean hasNextHeadingStart = false;
        if (!currentNumbering.logicalNext.equals(EMPTY_STRING))
            hasNextHeadingStart = text.startsWith(currentNumbering.logicalNext);
        String firstWord = text.split(" ")[0];
        boolean hasNextHeadingInBold = firstWord.endsWith(".") &&
                hasSameStyle && wordParagraph.isFirstRunBold(paragraphIndex);
        return hasNextHeadingStart || hasNextHeadingInBold;
    }

    private boolean isTextCapitalizedAndHasSameStyle(String text, String currentStyle) {
        return StringFormatting.isTextCapitalized(text) &&
                currentStyle.equals(currentNumbering.style);
    }

    boolean isCaseClosing(int nextParagraph) {
        String paragraphText = wordParagraph.getParagraphText(nextParagraph);
        return sectionClosing.isClosingSentence(nextParagraph, paragraphText) ||
                sectionClosing.isClosingHeading(nextParagraph, paragraphText);
    }

    Section setCurrentNumbering(UnitNumbering unitNumbering) {
        this.currentNumbering = unitNumbering;
        return this;
    }
}
