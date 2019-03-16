package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.UnitNumbering;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

public class CaseBodyParser implements ICaseBodyParser {

    private WordParagraph wordParagraph;
    private JsonArray bodySubsections;

    public CaseBodyParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.bodySubsections = new JsonArray();
    }

    public SectionResult parse(int startParagraph) {
        if (wordParagraph.startsCaseBackgroundSection(startParagraph)) {
            UnitNumbering currentUnitNumbering = wordParagraph.getUnitNumbering(startParagraph);
            String heading = wordParagraph.getParagraphText(startParagraph);
            heading = StringFormatting.trimColons(heading);
            Subsection subsection = new SectionBody(wordParagraph, startParagraph)
                    .setCurrentNumbering(currentUnitNumbering)
                    .parse();
            addCaseBodySubsection(heading, subsection);
        }
        JsonObject caseBody = getCaseBody();
        return new SectionResult(caseBody, 0);
    }

    private void addCaseBodySubsection(String heading, Subsection subsection) {
        JsonObject sectionContent = new JsonObject();
        sectionContent.addNameValuePair(heading, subsection.getBody());
        bodySubsections.putValue(sectionContent);
    }

    private JsonObject getCaseBody() {
        JsonObject caseBody = new JsonObject();
        caseBody.addNameValuePair(Keywords.CASE_BODY, bodySubsections);
        return caseBody;
    }
}
