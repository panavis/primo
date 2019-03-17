package com.panavis.WordToJsonConverter.Parsers;

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

}
