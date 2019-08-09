package com.panavis.primo.Parsers;

import com.panavis.primo.Style.Numbering.UnitNumbering;
import com.panavis.primo.Style.WordParagraph;
import com.panavis.primo.Utils.StringFormatting;
import static com.panavis.primo.Utils.StringFormatting.*;

class SectionBodyNewFormat extends Section {

    private UnitNumbering currentNumbering;

    SectionBodyNewFormat(WordParagraph wordParagraph) {
        super(wordParagraph);
    }

    @Override
    boolean isStillInOneSubsection(int paragraphIndex) {
        if (super.closingLogic.isCaseClosing(paragraphIndex)) return false;

        String text = wordParagraph.getParagraphText(paragraphIndex);
        UnitNumbering paragraphNumbering = wordParagraph.getUnitNumbering(paragraphIndex);

        if (!currentNumbering.realNext.equals(EMPTY_STRING) &&
                paragraphHasNumbering(paragraphNumbering.current)) {
            return !matchesNextNumbering(text, currentNumbering.realNext);
        }

        if (!currentNumbering.logicalNext.isEmpty() &&
                (text.startsWith(currentNumbering.logicalNext + ".") ||
                        text.startsWith(currentNumbering.logicalNext + " ."))) {
            return false;
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

    Section setCurrentNumbering(UnitNumbering unitNumbering) {
        this.currentNumbering = unitNumbering;
        return this;
    }
}
