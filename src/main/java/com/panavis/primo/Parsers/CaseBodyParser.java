package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Keywords.*;

import com.panavis.primo.ResultTypes.Result;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.Numbering.Formats.UpperRoman;
import com.panavis.primo.Style.Numbering.UnitNumbering;
import com.panavis.primo.Style.*;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.Wrappers.*;

public class CaseBodyParser implements ICaseSectionParser {

    private WordParagraph wordParagraph;
    private JsonArray bodySubsections;
    private SectionBodyNewFormat bodyNewFormat;
    private SectionBodyOldFormat bodyOldFormat;

    public CaseBodyParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.bodyNewFormat = new SectionBodyNewFormat(wordParagraph);
        this.bodyOldFormat = new SectionBodyOldFormat(wordParagraph);
        this.bodySubsections = new JsonArray();
    }

    public SectionResult parse(int startParagraph) {
        int nextParagraph = startParagraph;
        if (wordParagraph.paragraphExists(nextParagraph)) {
            Result hasNewFormat = bodyNewFormat.hasNewCaseBodyFormat(nextParagraph);

            if (hasNewFormat.value) {
                nextParagraph = hasNewFormat.index;

                while(wordParagraph.paragraphExists(nextParagraph) &&
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
                while(wordParagraph.paragraphExists(nextParagraph) &&
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
        UnitNumbering numbering = wordParagraph.getUnitNumbering(nextParagraph);
        if (numbering.current.isEmpty()) {
            String paragraphText = wordParagraph.getParagraphText(nextParagraph);

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
        String heading = wordParagraph.getHeadingFromParagraph(startParagraph);
        heading = StringFormatting.trimColonsOrSemicolons(heading);
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
