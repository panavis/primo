package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
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
        int nextSectionStart = startParagraph;
        JsonArray closingText = new JsonArray();
        String paragraphText = wordParagraph.getParagraphText(startParagraph);
        if (section.isClosingSentence(startParagraph, paragraphText)) {
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
}
