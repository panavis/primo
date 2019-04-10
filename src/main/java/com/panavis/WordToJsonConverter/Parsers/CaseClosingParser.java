package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

public class CaseClosingParser implements ICaseSectionParser{

    private WordParagraph wordParagraph;
    private SectionClosing section;

    public CaseClosingParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.section = new SectionClosing(wordParagraph);
    }

    @Override
    public SectionResult parse(int startParagraph) {
        String closingText = wordParagraph.getParagraphText(startParagraph);
        JsonObject caseClosing = new JsonObject();
        caseClosing.addNameValuePair(Keywords.CASE_CLOSING, closingText);
        return new SectionResult(caseClosing, 0);
    }
}
