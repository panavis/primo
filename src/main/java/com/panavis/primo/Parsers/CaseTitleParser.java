package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Headings.*;
import static com.panavis.primo.Constants.Keywords.*;
import com.panavis.primo.Utils.*;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Wrappers.JsonObject;

public class CaseTitleParser implements ICaseSectionParser {

    private CaseParagraph caseParagraph;

    public CaseTitleParser(CaseParagraph caseParagraph) {
        this.caseParagraph = caseParagraph;
    }

    public SectionResult parse(int startParagraph) {
        String caseTitle = "";
        int paragraphIndex;
        for (paragraphIndex = startParagraph;
             paragraphIndex < this.caseParagraph.getNumberOfParagraphs();
             paragraphIndex++) {

            String firstWord = this.caseParagraph.getParagraphFirstWord(paragraphIndex);
            if (firstWord.equals(URUKIKO)) {
                caseTitle = this.caseParagraph.getParagraphText(paragraphIndex);
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
