package com.panavis.primo.Parsers;

import com.panavis.primo.Constants.Keywords;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.WordParagraph;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

public class CaseClosingParser implements ICaseSectionParser{

    private WordParagraph wordParagraph;
    private SectionClosing section;

    public CaseClosingParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.section = new SectionClosing(wordParagraph);
    }

    public SectionResult parse(int startParagraph) {
        int nextSectionStart = startParagraph;
        JsonArray closingText = new JsonArray();
        if (wordParagraph.paragraphExists(nextSectionStart) &&
                section.isClosingSentence(startParagraph)) {
            int logicalHeadingIndex = startParagraph -1;
            section.setStartingParagraph(logicalHeadingIndex)
                   .parse();
           closingText = section.getBody();
           nextSectionStart = section.getLastParagraph();
        } else {
            closingText.putValue("");
        }
        JsonObject caseClosing = new JsonObject();
        caseClosing.addNameValuePair(Keywords.CASE_CLOSING, closingText);
        return new SectionResult(caseClosing, nextSectionStart);
    }

    @Override
    public boolean skippedParagraphs() {
        return false;
    }
}
