package com.panavis.primo.Parsers;

import com.panavis.primo.Constants.Keywords;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

public class CaseClosingParser implements ICaseSectionParser{

    private CaseParagraph caseParagraph;
    private CaseBodyFormat caseBodyFormat;
    private Section section;

    public CaseClosingParser(CaseParagraph caseParagraph, CaseBodyFormat caseBodyFormat, Section sectionCaseClosing) {
        this.caseParagraph = caseParagraph;
        this.caseBodyFormat = caseBodyFormat;
        this.section = sectionCaseClosing;
    }

    public SectionResult parse(int startParagraph) {
        int nextSectionStart = startParagraph;
        JsonArray closingText = new JsonArray();

        if (caseParagraph.paragraphExists(nextSectionStart) &&
                caseBodyFormat.isClosingSentence(startParagraph)) {

            int logicalHeadingIndex = startParagraph -1;
            section.setStartingParagraph(logicalHeadingIndex)
                   .parse();
           closingText = section.getBody();
           nextSectionStart = section.getLastParagraph();
        }
        else {
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
