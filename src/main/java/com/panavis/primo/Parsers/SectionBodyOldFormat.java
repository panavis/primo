package com.panavis.primo.Parsers;

import com.panavis.primo.Style.CaseParagraph;

class SectionBodyOldFormat extends Section {

    SectionBodyOldFormat(CaseParagraph caseParagraph) {
        super(caseParagraph);
    }

    @Override
    boolean isInNextSubsection(int paragraphIndex) {
        return caseParagraph.isSectionHeading(paragraphIndex) || super.closingLogic.isCaseClosing(paragraphIndex);
    }
}