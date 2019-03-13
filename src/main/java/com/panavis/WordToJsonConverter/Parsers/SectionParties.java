package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Style.WordParagraph;

public class SectionParties extends Subsection {

    SectionParties(WordParagraph wordParagraph, int startParagraph) {
        super(wordParagraph, startParagraph);
    }

    @Override
    public boolean isStillInOneSubsection(int paragraphIndex) {
        return !(wordParagraph.isSectionHeading(paragraphIndex)) &&
                !wordParagraph.startsProsecutorSubsection(paragraphIndex);
    }
}
