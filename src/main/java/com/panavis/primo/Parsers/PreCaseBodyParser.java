package com.panavis.primo.Parsers;

import com.panavis.primo.Constants.Keywords;
import com.panavis.primo.ResultTypes.Result;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

public class PreCaseBodyParser implements ICaseSectionParser {

    private CaseParagraph caseParagraph;
    private CaseBodyFormat caseBodyFormat;
    private JsonArray sectionContent;

    public PreCaseBodyParser(CaseParagraph caseParagraph, CaseBodyFormat caseBodyFormat) {
        this.caseParagraph = caseParagraph;
        this.sectionContent = new JsonArray();
        this.caseBodyFormat = caseBodyFormat;
    }

    @Override
    public SectionResult parse(int startParagraph) {
        int nextParagraph = startParagraph;
        while (caseParagraph.paragraphExists(nextParagraph) &&
                !hasNewCaseBodyFormat(nextParagraph) &&
                !caseBodyFormat.isOldFormatCaseBody(nextParagraph).isValid) {

                String paragraphText = caseParagraph.getParagraphText(nextParagraph);
                this.sectionContent.putValue(paragraphText);
                nextParagraph++;

        }
        JsonObject preCaseBody = new JsonObject();
        preCaseBody.addNameValuePair(Keywords.PRE_CASE_BODY, this.sectionContent);
        return new SectionResult(preCaseBody, nextParagraph);
    }

    private boolean hasNewCaseBodyFormat(int paragraphIndex) {
        Result hasNewFormat = caseBodyFormat.isNewFormatCaseBody(paragraphIndex);
        return hasNewFormat.isValid;
    }



    @Override
    public boolean skippedParagraphs() {
        return false;
    }
}
