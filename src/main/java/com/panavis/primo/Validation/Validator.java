package com.panavis.primo.Validation;

import com.panavis.primo.Parsers.ParsedCase;

public abstract class Validator {

    protected ParsedCase parsedCase;

    Validator(ParsedCase parsedCase) {
        this.parsedCase = parsedCase;
    }

    public boolean isParsedCaseValid() {
        return isTitleValid() && arePartiesValid() && isSubjectMatterValid() &&
                isCaseBodyValid() && isCasePanelValid() && !skippedParagraphs();
    }

    public boolean skippedParagraphs() {
        return this.parsedCase.didSkipParagraphs();
    }

    public abstract boolean isTitleValid();
    public abstract boolean arePartiesValid();
    public abstract boolean isSubjectMatterValid();
    public abstract boolean isCaseBodyValid();
    public abstract boolean isCasePanelValid();
}
