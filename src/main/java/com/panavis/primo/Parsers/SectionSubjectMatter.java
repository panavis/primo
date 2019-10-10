package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Headings.*;
import com.panavis.primo.Style.CaseParagraph;

public class SectionSubjectMatter extends Section {

    SectionSubjectMatter(CaseParagraph caseParagraph) {
        super(caseParagraph);
    }

    @Override
    public boolean isStillInOneSubsection(int paragraphIndex) {
        if (hasAnotherSubsection(paragraphIndex)) return false;
        if (caseParagraph.isSectionHeading(paragraphIndex)) return false;
        return !startsCaseBodySection(paragraphIndex);
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
