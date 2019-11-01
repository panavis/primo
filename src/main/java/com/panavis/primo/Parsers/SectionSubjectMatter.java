package com.panavis.primo.Parsers;

import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;

public class SectionSubjectMatter extends Section {

    private CaseBodyFormat caseBodyFormat;

    public SectionSubjectMatter(CaseParagraph caseParagraph, CaseBodyFormat caseBodyFormat) {
        super(caseParagraph);
        this.caseBodyFormat = caseBodyFormat;
    }

    @Override
    public boolean isInNextSubsection(int paragraphIndex) {
        if (isHeadingTooLong(paragraphIndex)) return false;

        return subjectMatterHasAnotherSubsection(paragraphIndex) ||
                caseParagraph.isSectionHeading(paragraphIndex) ||
                startsCaseBodySection(paragraphIndex);
    }

    @Override
    boolean isHeadingTooLong(int paragraphIndex) {
        int MAX_HEADING_LENGTH = 40; // ~ 30 seen in case data
        String text = caseParagraph.getHeadingFromParagraph(paragraphIndex);
        return !StringFormatting.isTextCapitalized(text) && text.length() >= MAX_HEADING_LENGTH;
    }

    @Override
    String getNextNumbering() {
        return StringFormatting.EMPTY_STRING;
    }

    private boolean startsCaseBodySection(int paragraphIndex) {
        return caseBodyFormat.isNewFormatCaseBody(paragraphIndex).isValid || caseBodyFormat.isOldFormatCaseBody(paragraphIndex).isValid;
    }
}
