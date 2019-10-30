package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Headings.*;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;

public class SectionSubjectMatter extends Section {

    SectionSubjectMatter(CaseParagraph caseParagraph) {
        super(caseParagraph);
    }

    @Override
    public boolean isInNextSubsection(int paragraphIndex) {
        if (isHeadingTooLong(paragraphIndex)) return false;

        return hasAnotherSubsection(paragraphIndex) ||
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
        return hasNewCaseBodyFormat(paragraphIndex).value || hasOldCaseBodyFormat(paragraphIndex);
    }

    boolean hasAnotherSubsection(int paragraphIndex) {
        String text = caseParagraph.getParagraphTextWithoutNumbering(paragraphIndex).toLowerCase();
        for (String heading: SUBJECT_MATTER_HEADINGS) {
            if (text.startsWith(heading.toLowerCase())) return true;
        }
        return false;
    }
}
