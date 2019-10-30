package com.panavis.primo.Parsers;

import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;

class SectionBodyOldFormat extends Section {

    SectionBodyOldFormat(CaseParagraph caseParagraph) {
        super(caseParagraph);
    }

    @Override
    boolean isInNextSubsection(int paragraphIndex) {
        return caseParagraph.isSectionHeading(paragraphIndex) || super.closingLogic.isCaseClosing(paragraphIndex);
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