package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.Utils.JsonCreator;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

public class CaseTitleParser implements ICaseTitle {

    private WordParagraph wordParagraph;

    public CaseTitleParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    public SectionResult parse() {
        String caseTitle = "";
        int paragraphIndex;
        for (paragraphIndex = 0; paragraphIndex < this.wordParagraph.numberOfParagraphs(); paragraphIndex++) {

            String firstWord = this.wordParagraph.getParagraphFirstWord(paragraphIndex);
            if (firstWord.equals(Headings.URUKIKO)) {
                caseTitle = this.wordParagraph.getParagraphText(paragraphIndex);
                break;
            }
        }
        JsonObject titleObject = JsonCreator.getJsonObject(Keywords.TITLE, caseTitle);
        return new SectionResult(titleObject, paragraphIndex + 1);
    }
}
