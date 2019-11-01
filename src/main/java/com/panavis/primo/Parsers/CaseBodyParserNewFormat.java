package com.panavis.primo.Parsers;

import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;


public class CaseBodyParserNewFormat extends CaseBodyParser implements ICaseSectionParser {

    private CaseParagraph caseParagraph;
    private JsonArray bodySubsections;

    public CaseBodyParserNewFormat(CaseParagraph caseParagraph,
                                   CaseBodyFormat caseBodyFormat,
                                   SectionCaseBody sectionCaseBody) {
        super(caseParagraph, caseBodyFormat, sectionCaseBody);
        this.caseParagraph = caseParagraph;
        this.bodySubsections = new JsonArray();
    }

    public SectionResult parse(int startParagraph) {
        return parseBody(startParagraph);
    }

    @Override
    public boolean skippedParagraphs() {
        return false;
    }

    void addCaseBodySubsection(Section section, int startParagraph, String extractedHeading) {
        String heading = caseParagraph.getHeadingFromParagraph(startParagraph);
        heading = extractedHeading.isEmpty() ? heading : extractedHeading;
        heading = StringFormatting.trimColonsAndSemicolons(heading);

        JsonObject sectionContent = new JsonObject();
        sectionContent.addNameValuePair(heading, section.getBody());
        bodySubsections.putValue(sectionContent);
    }

    @Override
    JsonArray getBodySubsections() {
        return bodySubsections;
    }
}
