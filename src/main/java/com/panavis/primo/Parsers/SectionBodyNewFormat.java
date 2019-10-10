package com.panavis.primo.Parsers;

import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.core.Numbering.UnitNumbering;

import static com.panavis.primo.Utils.StringFormatting.*;

class SectionBodyNewFormat extends Section {

    private UnitNumbering currentNumbering;

    SectionBodyNewFormat(CaseParagraph caseParagraph) {
        super(caseParagraph);
    }

    @Override
    boolean isStillInOneSubsection(int paragraphIndex) {
        if (super.closingLogic.isCaseClosing(paragraphIndex)) return false;

        String text = caseParagraph.getParagraphText(paragraphIndex);
        UnitNumbering paragraphNumbering = caseParagraph.getUnitNumbering(paragraphIndex);

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
        String currentStyle = caseParagraph.getUnitNumbering(paragraphIndex).style;
        boolean hasSameStyle = isTextCapitalizedAndHasSameStyle(text, currentStyle);
        boolean nonDynamicNumbering = hasNonDynamicNumbering(paragraphIndex, text, hasSameStyle);
        boolean noNumbering = hasSameStyle &&
                caseParagraph.isBeginningUnderlined(paragraphIndex);
        return nonDynamicNumbering || noNumbering;
    }

    private boolean hasNonDynamicNumbering(int paragraphIndex, String text, boolean hasSameStyle) {
        boolean hasNextHeadingStart = false;
        if (!currentNumbering.logicalNext.equals(EMPTY_STRING))
            hasNextHeadingStart = text.startsWith(currentNumbering.logicalNext);
        String firstWord = text.split(" ")[0];
        boolean hasNextHeadingInBold = firstWord.endsWith(".") &&
                hasSameStyle && caseParagraph.isFirstRunBold(paragraphIndex);
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
