package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Style.WordParagraph;

public class SectionParties implements ISection {

    private WordParagraph wordParagraph;

    SectionParties(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    public boolean isStillInOneSubsection(int paragraphIndex) {
        return !(wordParagraph.isSectionHeading(paragraphIndex)) &&
                !wordParagraph.startsProsecutorSubsection(paragraphIndex);
    }
}
