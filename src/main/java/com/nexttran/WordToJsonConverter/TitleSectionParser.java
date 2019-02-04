package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Headings;
import com.nexttran.WordToJsonConverter.Constants.Keywords;
import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

class TitleSectionParser {

    private WordParagraph wordParagraph;

    TitleSectionParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    SectionResult parseCaseTitle() {
        String caseTitle = "";

        int paragraphIndex;
        for (paragraphIndex = 0; paragraphIndex < this.wordParagraph.paragraphs.size(); paragraphIndex++) {

            String firstWord = this.wordParagraph.getParagraphFirstWord(paragraphIndex);
            if (firstWord.equals(Headings.URUKIKO)) {
                XWPFParagraph currentParagraph = this.wordParagraph.paragraphs.get(paragraphIndex);
                caseTitle = currentParagraph.getText();
                break;
            }
        }
        JsonObject titleObject = JsonCreator.getJsonObject(Keywords.TITLE, caseTitle);
        return new SectionResult(titleObject, paragraphIndex + 1);
    }
}