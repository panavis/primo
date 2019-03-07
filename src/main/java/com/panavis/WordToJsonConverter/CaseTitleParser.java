package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

class CaseTitleParser implements ICaseTitle {

    private WordParagraph wordParagraph;

    CaseTitleParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    public SectionResult parse() {
        String caseTitle = "";
        int paragraphIndex;
        for (paragraphIndex = 0; paragraphIndex < this.wordParagraph.numberOfParagraphs(); paragraphIndex++) {

            String firstWord = this.wordParagraph.getParagraphFirstWord(paragraphIndex);
            if (firstWord.equals(Headings.URUKIKO)) {
                XWPFParagraph currentParagraph = this.wordParagraph.getParagraph(paragraphIndex);
                caseTitle = currentParagraph.getText();
                break;
            }
        }
        JsonObject titleObject = JsonCreator.getJsonObject(Keywords.TITLE, caseTitle);
        return new SectionResult(titleObject, paragraphIndex + 1);
    }
}
