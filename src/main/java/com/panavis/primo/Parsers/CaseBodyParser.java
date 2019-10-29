package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Keywords.*;

import com.panavis.primo.ResultTypes.Result;
import com.panavis.primo.ResultTypes.SectionResult;

import com.panavis.primo.Style.*;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.Wrappers.*;
import com.panavis.primo.core.Numbering.Formats.UpperRoman;
import com.panavis.primo.core.Numbering.UnitNumbering;

public class CaseBodyParser implements ICaseSectionParser {

    private CaseParagraph caseParagraph;
    private JsonArray bodySubsections;
    private SectionBodyNewFormat bodyNewFormat;
    private SectionBodyOldFormat bodyOldFormat;

    public CaseBodyParser(CaseParagraph caseParagraph) {
        this.caseParagraph = caseParagraph;
        this.bodyNewFormat = new SectionBodyNewFormat(caseParagraph);
        this.bodyOldFormat = new SectionBodyOldFormat(caseParagraph);
        this.bodySubsections = new JsonArray();
    }

    public SectionResult parse(int startParagraph) {
        int nextParagraph = startParagraph;
        if (caseParagraph.paragraphExists(nextParagraph)) {
            Result hasNewFormat = bodyNewFormat.hasNewCaseBodyFormat(nextParagraph);

            if (hasNewFormat.value) {
                nextParagraph = hasNewFormat.index;

                while(caseParagraph.paragraphExists(nextParagraph) &&
                            !bodyNewFormat.closingLogic.isCaseClosing(nextParagraph))
                    {
                        UnitNumbering numbering = getHeadingUnitNumbering(nextParagraph);
                        bodyNewFormat.setCurrentNumbering(numbering)
                                .setStartingParagraph(nextParagraph)
                                .parse();
                        addCaseBodySubsection(nextParagraph, bodyNewFormat);
                        nextParagraph = bodyNewFormat.getLastParagraph();
                    }
            }
            else if (bodyNewFormat.hasOldCaseBodyFormat(startParagraph)) {
                while(caseParagraph.paragraphExists(nextParagraph) &&
                        !bodyOldFormat.closingLogic.isCaseClosing(nextParagraph))
                {
                    bodyOldFormat
                            .setStartingParagraph(nextParagraph)
                            .parse();
                    addCaseBodySubsection(nextParagraph, bodyOldFormat);
                    nextParagraph = bodyOldFormat.getLastParagraph();
                }
            }
        }
        JsonObject caseBody = getCaseBody();
        return new SectionResult(caseBody, nextParagraph);
    }

    private UnitNumbering getHeadingUnitNumbering(int nextParagraph) {
        UnitNumbering numbering = caseParagraph.getUnitNumbering(nextParagraph);
        if (numbering.current.isEmpty()) {
            String paragraphText = caseParagraph.getParagraphText(nextParagraph);

            for (String romanNumber : UpperRoman.FIRST_ROMAN_NUMBERS.keySet()) {

                String current = "";
                if (paragraphText.startsWith(romanNumber + ".")) {
                    current = romanNumber + ".";
                }
                if (paragraphText.startsWith(romanNumber + " .")) {
                    current = romanNumber + " .";
                }
                if (!current.isEmpty()) {
                    int number = UpperRoman.FIRST_ROMAN_NUMBERS.get(romanNumber);

                    numbering = UnitNumbering.builder("upperRoman", "%" + number)
                            .setCurrentNumbering(number)
                            .setLogicalNextNumbering(number)
                            .setNumberingStyle("Heading");

                    break;
                }
            }
        }
        return numbering;
    }

    @Override
    public boolean skippedParagraphs() {
        return false;
    }

    private void addCaseBodySubsection(int startParagraph, Section section) {
        String heading = caseParagraph.getHeadingFromParagraph(startParagraph);
        heading = StringFormatting.trimColonsAndSemicolons(heading);
        JsonObject sectionContent = new JsonObject();
        sectionContent.addNameValuePair(heading, section.getBody());
        bodySubsections.putValue(sectionContent);
    }

    private JsonObject getCaseBody() {
        JsonObject caseBody = new JsonObject();
        caseBody.addNameValuePair(CASE_BODY, bodySubsections);
        return caseBody;
    }
}
