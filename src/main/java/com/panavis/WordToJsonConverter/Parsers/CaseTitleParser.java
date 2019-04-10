package com.panavis.WordToJsonConverter.Parsers;

import static com.panavis.WordToJsonConverter.Constants.Headings.*;
import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import com.panavis.WordToJsonConverter.Utils.*;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

public class CaseTitleParser implements ICaseSectionParser {

    private WordParagraph wordParagraph;

    public CaseTitleParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    public SectionResult parse(int startParagraph) {
        String caseTitle = "";
        int paragraphIndex;
        for (paragraphIndex = startParagraph;
             paragraphIndex < this.wordParagraph.numberOfParagraphs();
             paragraphIndex++) {

            String firstWord = this.wordParagraph.getParagraphFirstWord(paragraphIndex);
            if (firstWord.equals(URUKIKO)) {
                caseTitle = this.wordParagraph.getParagraphText(paragraphIndex);
                break;
            }
        }
        caseTitle = StringFormatting.trimColons(caseTitle);
        JsonObject titleObject = JsonCreator.getJsonObject(TITLE, caseTitle);
        return new SectionResult(titleObject, paragraphIndex + 1);
    }
}
