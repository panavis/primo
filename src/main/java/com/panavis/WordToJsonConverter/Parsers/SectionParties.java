package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.Style.WordParagraph;

public class SectionParties extends Section {

    SectionParties(WordParagraph wordParagraph) {
        super(wordParagraph);
    }

    @Override
    public boolean isStillInOneSubsection(int paragraphIndex) {
        return !(wordParagraph.isSectionHeading(paragraphIndex)) &&
                !startsProsecutorSubsection(paragraphIndex);
    }

    private boolean startsProsecutorSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraphText(paragraphIndex);
        return text.toLowerCase().contains(Keywords.UBUSHINJACYAHA) &&
                previousParagraphIsNotSubsectionHeading(paragraphIndex);
    }

    private boolean previousParagraphIsNotSubsectionHeading(int paragraphIndex) {
        if (paragraphIndex - 1 < 0) return true;
        String pP = wordParagraph.getHeadingFromParagraph(paragraphIndex - 1).toUpperCase();
        return !wordParagraph.isSectionHeading(paragraphIndex - 1) ||
                Headings.PARTIES_HEADINGS.contains(pP);
    }

    boolean startsSubjectMatterSection(int paragraphIndex) {
        String paragraphText = wordParagraph.getParagraphText(paragraphIndex);
        boolean subjectMatterStart = false;
        for (String heading : Headings.SUBJECT_MATTER_HEADINGS) {
            if (paragraphText.toUpperCase().startsWith(heading))
                subjectMatterStart = true;
        }
        return subjectMatterStart;
    }
}
