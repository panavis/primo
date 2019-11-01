package com.panavis.primo.Parsers;

import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;

public class SectionBodyOldFormat extends SectionCaseBody {

    private CaseBodyFormat caseBodyFormat;

    public SectionBodyOldFormat(CaseParagraph caseParagraph, CaseBodyFormat caseBodyFormat) {
        super(caseParagraph);
        this.caseBodyFormat = caseBodyFormat;
    }

    @Override
    boolean isInNextSubsection(int paragraphIndex) {
        return caseParagraph.isSectionHeading(paragraphIndex) || caseBodyFormat.isCaseClosing(paragraphIndex);
    }

    @Override
    boolean isHeadingTooLong(int paragraphIndex) {
        int MAX_HEADING_LENGTH = 75; // ~ 55 seen in case data
        String text = caseParagraph.getParagraphText(paragraphIndex);
        return text.length() >= MAX_HEADING_LENGTH;
    }

    @Override
    String getNextNumbering() {
        return StringFormatting.EMPTY_STRING;
    }
}