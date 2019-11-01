package com.panavis.primo.Parsers;

import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.core.Numbering.UnitNumbering;

import static com.panavis.primo.Utils.StringFormatting.*;

public class SectionBodyNewFormat extends SectionCaseBody {

    private CaseBodyFormat caseBodyFormat;

    public SectionBodyNewFormat(CaseParagraph caseParagraph, CaseBodyFormat caseBodyFormat) {
        super(caseParagraph);
        this.caseBodyFormat = caseBodyFormat;
    }

    @Override
    boolean isInNextSubsection(int paragraphIndex) {
        if (caseBodyFormat.isCaseClosing(paragraphIndex)) return true;
        if (isHeadingTooLong(paragraphIndex)) return false;

        String text = caseParagraph.getParagraphText(paragraphIndex);
        UnitNumbering paragraphNumbering = caseParagraph.getUnitNumbering(paragraphIndex);

        if (hasNextRealNumbering() &&
                paragraphHasNumbering(paragraphNumbering.current)) {
            return matchesNextNumbering(text, getCurrentNumbering().realNext);
        }
        return hasSameBodyHeadingFormat(paragraphIndex, text);
    }

    @Override
    boolean isHeadingTooLong(int paragraphIndex) {
        int MAX_HEADING_LENGTH = 75; // ~ 55 seen in case data
        String text = caseParagraph.getParagraphText(paragraphIndex);
        return text.length() >= MAX_HEADING_LENGTH;
    }

    @Override
    String getNextNumbering() {
        return getCurrentNumbering().logicalNext;
    }

    private boolean hasNextRealNumbering() {
        return !getCurrentNumbering().realNext.equals(EMPTY_STRING);
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
        if (!getCurrentNumbering().logicalNext.equals(EMPTY_STRING))
            hasNextHeadingStart = text.startsWith(getCurrentNumbering().logicalNext);

        String firstWord = text.split(" ")[0];
        boolean hasNextHeadingInBold = firstWord.endsWith(".") &&
                hasSameStyle && caseParagraph.isFirstRunBold(paragraphIndex);

        return hasNextHeadingStart || hasNextHeadingInBold;
    }

    private boolean isTextCapitalizedAndHasSameStyle(String text, String currentStyle) {
        return StringFormatting.isTextCapitalized(text) &&
                currentStyle.equals(getCurrentNumbering().style);
    }
}
