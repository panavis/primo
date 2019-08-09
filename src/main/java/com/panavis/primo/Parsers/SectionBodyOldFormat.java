package com.panavis.primo.Parsers;

import com.panavis.primo.Style.WordParagraph;

class SectionBodyOldFormat extends Section {

    SectionBodyOldFormat(WordParagraph wordParagraph) {
        super(wordParagraph);
    }

    @Override
    boolean isStillInOneSubsection(int paragraphIndex) {
        return !wordParagraph.isSectionHeading(paragraphIndex) && !super.closingLogic.isCaseClosing(paragraphIndex);
    }
}