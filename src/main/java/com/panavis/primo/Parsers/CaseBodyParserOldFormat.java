package com.panavis.primo.Parsers;

import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

import static com.panavis.primo.Constants.Keywords.*;


public class CaseBodyParserOldFormat extends CaseBodyParser implements ICaseSectionParser {

    private CaseParagraph caseParagraph;
    private JsonArray bodySubsections;

    public CaseBodyParserOldFormat(CaseParagraph caseParagraph,
                                   CaseBodyFormat caseBodyFormat,
                                   SectionCaseBody sectionCaseBody) {
        super(caseParagraph, caseBodyFormat,sectionCaseBody);
        this.caseParagraph = caseParagraph;
        this.bodySubsections = new JsonArray();
    }

    @Override
    public SectionResult parse(int startParagraph) {
       return parseBody(startParagraph);
    }

    @Override
    void addCaseBodySubsection(Section section, int startParagraph, String extractedHeading) {
        String headingLineText = caseParagraph.getParagraphText(startParagraph);
        String heading = caseParagraph.getHeadingFromParagraph(startParagraph);
        heading = extractedHeading.isEmpty() ? heading : extractedHeading;
        heading = StringFormatting.trimColonsAndSemicolons(heading);
        JsonArray sectionBody = section.getBody();

        String firstParagraph = sectionBody.getSize() > 0 ? sectionBody.getStringByIndex(0) : "";
        String firstWordOfParagraph = firstParagraph.split(" ")[0];


        if (firstWordOfParagraph.equalsIgnoreCase(RUSHINGIYE)) {
            heading = "URUKIKO RUSHINGIYE";
        }
        else if (heading.toLowerCase().startsWith(RUMAZE)) {
            heading = "URUKIKO RUMAZE";
            sectionBody = getFullSectionBody(headingLineText, sectionBody);
        }
        else if (heading.toLowerCase().startsWith(RUSANZE)) {
            heading = "URUKIKO RUSANZE";
            sectionBody = getFullSectionBody(headingLineText, sectionBody);
        }

        JsonObject sectionContent = new JsonObject();
        sectionContent.addNameValuePair(heading, sectionBody);
        bodySubsections.putValue(sectionContent);
    }

    private JsonArray getFullSectionBody(String heading, JsonArray sectionBody) {
        JsonArray fullSectionBody = new JsonArray();
        fullSectionBody.putValue(heading);
        for (int i=0; i < sectionBody.getSize(); i++) {
            fullSectionBody.putValue(sectionBody.getStringByIndex(i));
        }
        sectionBody = fullSectionBody;
        return sectionBody;
    }

    @Override
    public boolean skippedParagraphs() {
        return false;
    }

    @Override
    JsonArray getBodySubsections() {
        return bodySubsections;
    }
}
