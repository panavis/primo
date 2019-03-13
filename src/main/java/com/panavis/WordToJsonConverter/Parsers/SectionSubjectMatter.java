package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.Style.WordParagraph;

public class SectionSubjectMatter extends Subsection {

    SectionSubjectMatter(WordParagraph wordParagraph, int startParagraph) {
        super(wordParagraph, startParagraph);
    }

    @Override
    public boolean isStillInOneSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraphText(paragraphIndex).toLowerCase();
        if (hasAnotherSubjectMatterSubsection(paragraphIndex)) return false;
        return !(text.contains("imiterere") && text.contains("y"));
    }

    boolean hasAnotherSubjectMatterSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraphText(paragraphIndex).toLowerCase();
        for (String heading: Headings.SUBJECT_MATTER_HEADINGS) {
            if (text.startsWith(heading.toLowerCase())) return true;
        }
        return false;
    }
}
