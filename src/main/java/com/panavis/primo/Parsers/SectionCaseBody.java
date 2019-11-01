package com.panavis.primo.Parsers;

import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.core.Numbering.UnitNumbering;

public abstract class SectionCaseBody extends Section {

    private UnitNumbering currentNumbering;

    public SectionCaseBody(CaseParagraph caseParagraph) {
        super(caseParagraph);
    }

    SectionCaseBody setCurrentNumbering(UnitNumbering unitNumbering) {
        this.currentNumbering = unitNumbering;
        return this;
    }

    UnitNumbering getCurrentNumbering() {
        return currentNumbering;
    }

}
