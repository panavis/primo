package com.panavis.primo.Parsers;

import com.panavis.primo.ResultTypes.SectionResult;

import java.util.HashMap;

public class ParsedCase {

    private final HashMap<String, SectionResult> parsedCase;
    private boolean missedSomeParagraphs;

    public ParsedCase() {
        this.parsedCase = new HashMap<>();
        this.missedSomeParagraphs = false;
    }

    public void set(String sectionName, SectionResult sectionResult) {
        this.parsedCase.put(sectionName, sectionResult);
    }

    public SectionResult get(String sectionName) {
        return this.parsedCase.get(sectionName);
    }

    public boolean hasSection(String sectionName) {
        return this.parsedCase.containsKey(sectionName);
    }

    public void setSkippedParagraphs(boolean skipped) {
        this.missedSomeParagraphs = skipped;
    }

    public boolean didSkipParagraphs() {
        return this.missedSomeParagraphs;
    }
}
