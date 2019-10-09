package com.panavis.primo.Parsers;

import com.panavis.primo.Style.WordParagraph;

class SectionClosing extends Section {

    private boolean endingReached;

    SectionClosing(WordParagraph wordParagraph) {
        super(wordParagraph);
        this.endingReached = false;
    }

    @Override
    boolean isStillInOneSubsection(int paragraphIndex) {
        if (endingReached) return false;
        if (wordParagraph.getNumberOfPostParagraphBlanks(paragraphIndex) > 1)
            endingReached = true;
        return !super.closingLogic.isClosingHeading(paragraphIndex);
    }
}
