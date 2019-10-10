package com.panavis.primo.Parsers;

import com.panavis.primo.Constants.Headings;
import com.panavis.primo.Constants.Keywords;
import com.panavis.primo.Style.CaseParagraph;

public class SectionParties extends Section {

    SectionParties(CaseParagraph caseParagraph) {
        super(caseParagraph);
    }

    @Override
    public boolean isStillInOneSubsection(int paragraphIndex) {
        return !(caseParagraph.isSectionHeading(paragraphIndex)) &&
                !startsProsecutorSubsection(paragraphIndex);
    }

    private boolean startsProsecutorSubsection(int paragraphIndex) {
        String text = caseParagraph.getParagraphText(paragraphIndex);
        return text.toLowerCase().contains(Keywords.UBUSHINJACYAHA) &&
                previousParagraphIsNotSubsectionHeading(paragraphIndex);
    }

    private boolean previousParagraphIsNotSubsectionHeading(int paragraphIndex) {
        if (paragraphIndex - 1 < 0) return true;
        String pP = caseParagraph.getHeadingFromParagraph(paragraphIndex - 1).toUpperCase();
        return !caseParagraph.isSectionHeading(paragraphIndex - 1) ||
                Headings.PARTIES_HEADINGS.contains(pP);
    }

    boolean startsSubjectMatterSection(int paragraphIndex) {
        String paragraphText = caseParagraph.getParagraphTextWithoutNumbering(paragraphIndex);
        boolean subjectMatterStart = false;
        for (String heading : Headings.SUBJECT_MATTER_HEADINGS) {
            if (paragraphText.toUpperCase().startsWith(heading))
                subjectMatterStart = true;
        }
        return subjectMatterStart;
    }
}
