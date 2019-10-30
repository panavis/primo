package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Keywords.*;

import com.panavis.primo.ResultTypes.Result;
import com.panavis.primo.ResultTypes.SectionResult;

import com.panavis.primo.Style.*;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.Wrappers.*;
import com.panavis.primo.core.Numbering.Formats.Decimal;
import com.panavis.primo.core.Numbering.Formats.UpperRoman;
import com.panavis.primo.core.Numbering.UnitNumbering;

import java.util.ArrayList;
import java.util.List;

public class CaseBodyParser implements ICaseSectionParser {

    private CaseParagraph caseParagraph;
    private JsonArray bodySubsections;
    private SectionBodyNewFormat bodyNewFormat;
    private SectionBodyOldFormat bodyOldFormat;
    private List<String> preCaseBody;

    public CaseBodyParser(CaseParagraph caseParagraph) {
        this.caseParagraph = caseParagraph;
        this.bodyNewFormat = new SectionBodyNewFormat(caseParagraph);
        this.bodyOldFormat = new SectionBodyOldFormat(caseParagraph);
        this.bodySubsections = new JsonArray();
        this.preCaseBody = new ArrayList<>();

    }

    public SectionResult parse(int startParagraph) {
        int nextParagraph = startParagraph;
        if (caseParagraph.paragraphExists(nextParagraph)) {
            Result hasNewFormat = bodyNewFormat.hasNewCaseBodyFormat(nextParagraph);

            if (hasNewFormat.value) {
                nextParagraph = parseBodyNewFormat(hasNewFormat);
            }
            else if (bodyNewFormat.hasOldCaseBodyFormat(startParagraph)) {
                nextParagraph = parseBodyOldFormat(nextParagraph);

            }
        }
        JsonObject caseBody = getCaseBody();
        return new SectionResult(caseBody, nextParagraph);
    }

    private int parseBodyNewFormat(Result hasNewFormat) {
        int nextParagraph;
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
        return nextParagraph;
    }

    private int parseBodyOldFormat(int nextParagraph) {
        while(caseParagraph.paragraphExists(nextParagraph) &&
                !bodyOldFormat.closingLogic.isCaseClosing(nextParagraph))
        {
            bodyOldFormat
                    .setStartingParagraph(nextParagraph)
                    .parse();
            addCaseBodySubsection(nextParagraph, bodyOldFormat);
            nextParagraph = bodyOldFormat.getLastParagraph();
        }
        return nextParagraph;
    }

    private UnitNumbering getHeadingUnitNumbering(int nextParagraph) {
        UnitNumbering numbering = caseParagraph.getUnitNumbering(nextParagraph);
        if (numbering.current.isEmpty()) {
            String paragraphText = caseParagraph.getParagraphText(nextParagraph);
            Object[] romanNumbers = UpperRoman.FIRST_ROMAN_NUMBERS.keySet().toArray();
            Object[] decimalNumbers = Decimal.FIRST_DECIMAL_NUMBERS.keySet().toArray();
            int size = romanNumbers.length > decimalNumbers.length ? decimalNumbers.length : romanNumbers.length;

            for (int i=0; i < size; i++) {
                String romanNumber = (String) romanNumbers[i];
                String decimalNumber = (String) decimalNumbers[i];

                if (startsWithRomanNumber(paragraphText, romanNumber)) {
                    int number = UpperRoman.FIRST_ROMAN_NUMBERS.get(romanNumber);

                    numbering = getNumbering(number, "upperRoman");

                    break;
                }
                else if (startsWithDecimalNumber(paragraphText, decimalNumber)) {
                    int number = Decimal.FIRST_DECIMAL_NUMBERS.get(decimalNumber);

                    numbering = getNumbering(number, "decimal");

                    break;
                }
            }
        }
        return numbering;
    }

    private UnitNumbering getNumbering(int number, String upperRoman) {
        return UnitNumbering.builder(upperRoman, "%" + number)
                .setCurrentNumbering(number)
                .setLogicalNextNumbering(number)
                .setNumberingStyle("Heading");
    }

    private boolean startsWithRomanNumber(String text, String romanNumber) {
        return startsWithNumbering(text, romanNumber);
    }

    private boolean startsWithNumbering(String text, String number) {
        return text.startsWith(number + ".") ||
                text.startsWith(number + " .") ||
                text.startsWith(number + " ");
    }

    private boolean startsWithDecimalNumber(String text, String decimalNumber) {
        return startsWithNumbering(text, decimalNumber);
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
