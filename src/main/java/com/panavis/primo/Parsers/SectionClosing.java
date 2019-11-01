package com.panavis.primo.Parsers;

import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;

public class SectionClosing extends Section {

    private boolean endingReached;
    private CaseBodyFormat caseBodyFormat;

    public SectionClosing(CaseParagraph caseParagraph, CaseBodyFormat caseBodyFormat) {
        super(caseParagraph);
        this.caseBodyFormat = caseBodyFormat;
        this.endingReached = false;
    }

    @Override
    boolean isInNextSubsection(int paragraphIndex) {
        if (endingReached) return true;
        if (caseParagraph.getNumberOfPostParagraphBlanks(paragraphIndex) > 1)
            endingReached = true;
        return caseBodyFormat.isClosingHeading(paragraphIndex);
    }

    @Override
    boolean isHeadingTooLong(int paragraphIndex) {
        return false;
    }

    @Override
    String getNextNumbering() {
        return StringFormatting.EMPTY_STRING;
    }
}
