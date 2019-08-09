package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Headings.*;
import static com.panavis.primo.Constants.Keywords.*;
import com.panavis.primo.Utils.*;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.WordParagraph;
import com.panavis.primo.Wrappers.JsonObject;

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
        caseTitle = StringFormatting.trimColonsOrSemicolons(caseTitle);
        JsonObject titleObject = JsonCreator.getJsonObject(TITLE, caseTitle);
        return new SectionResult(titleObject, paragraphIndex + 1);
    }

    @Override
    public boolean skippedParagraphs() {
        return false;
    }
}
