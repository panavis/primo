package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.Style.WordParagraph;

public class SectionParties extends Section {

    SectionParties(WordParagraph wordParagraph) {
        super(wordParagraph);
    }

    @Override
    public boolean isStillInOneSubsection(int paragraphIndex) {
        return isNotSectionHeading(paragraphIndex) &&
                doesNotStartProsecutorSubsection(paragraphIndex);
    }

    private boolean isNotSectionHeading(int paragraphIndex) {
        return !(wordParagraph.isSectionHeading(paragraphIndex));
    }

    private boolean doesNotStartProsecutorSubsection(int paragraphIndex) {
        return !wordParagraph.startsProsecutorSubsection(paragraphIndex);
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
