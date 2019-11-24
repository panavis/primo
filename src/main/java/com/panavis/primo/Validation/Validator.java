package com.panavis.primo.Validation;

import com.panavis.primo.Parsers.ParsedCase;

public abstract class Validator {

    ParsedCase parsedCase;
    private boolean validTitle;
    private boolean validParties;
    private boolean validSubjectMatter;
    private boolean validCaseBody;
    private boolean validPanel;
    private boolean skippedParagraphs;

    Validator(ParsedCase parsedCase) {
        this.parsedCase = parsedCase;
        validTitle = false;
        validParties = false;
        validSubjectMatter = false;
        validCaseBody = false;
        validPanel = false;
        skippedParagraphs = true;

    }

    public boolean isParsedCaseValid() {
        validTitle = isTitleValid();
        validParties = arePartiesValid();
        validSubjectMatter = isSubjectMatterValid();
        validCaseBody = isCaseBodyValid();
        validPanel = isCasePanelValid();
        skippedParagraphs  = hasSkippedParagraphs();

        return validTitle && validParties && validSubjectMatter &&
                validCaseBody && validPanel && !skippedParagraphs;
    }

    public boolean hasSkippedParagraphs() {
        return this.parsedCase.didSkipParagraphs();
    }

    public abstract boolean isTitleValid();
    public abstract boolean arePartiesValid();
    public abstract boolean isSubjectMatterValid();
    public abstract boolean isCaseBodyValid();
    public abstract boolean isCasePanelValid();

    public boolean getSkippedParagraphsStatus() {
        return skippedParagraphs;
    }
}
