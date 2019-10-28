package com.panavis.primo.Parsers;

import com.panavis.primo.Style.CaseParagraph;

class SectionClosing extends Section {

    private boolean endingReached;

    SectionClosing(CaseParagraph caseParagraph) {
        super(caseParagraph);
        this.endingReached = false;
    }

    @Override
    boolean isInNextSubsection(int paragraphIndex) {
        if (endingReached) return true;
        if (caseParagraph.getNumberOfPostParagraphBlanks(paragraphIndex) > 1)
            endingReached = true;
        return super.closingLogic.isClosingHeading(paragraphIndex);
    }
}
