package com.panavis.WordToJsonConverter.Parsers;

import static com.panavis.WordToJsonConverter.Constants.Headings.*;
import com.panavis.WordToJsonConverter.Style.WordParagraph;

public class SectionSubjectMatter extends Section {

    SectionSubjectMatter(WordParagraph wordParagraph) {
        super(wordParagraph);
    }

    @Override
    public boolean isStillInOneSubsection(int paragraphIndex) {
        if (hasAnotherSubsection(paragraphIndex)) return false;
        if (wordParagraph.isSectionHeading(paragraphIndex)) return false;
        return !startsCaseBodySection(paragraphIndex);
    }

    boolean hasAnotherSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraphText(paragraphIndex).toLowerCase();
        for (String heading: SUBJECT_MATTER_HEADINGS) {
            if (text.startsWith(heading.toLowerCase())) return true;
        }
        return false;
    }
}
