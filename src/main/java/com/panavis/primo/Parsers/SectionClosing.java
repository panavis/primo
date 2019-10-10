package com.panavis.primo.Parsers;

import com.panavis.primo.Style.CaseParagraph;

class SectionClosing extends Section {

    private boolean endingReached;

    SectionClosing(CaseParagraph caseParagraph) {
        super(caseParagraph);
        this.endingReached = false;
    }

    @Override
    boolean isStillInOneSubsection(int paragraphIndex) {
        if (endingReached) return false;
        if (caseParagraph.getNumberOfPostParagraphBlanks(paragraphIndex) > 1)
            endingReached = true;
        return !super.closingLogic.isClosingHeading(paragraphIndex);
    }
}
