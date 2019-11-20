package com.panavis.primo.Parsers;

import com.panavis.primo.Constants.Keywords;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;
import com.panavis.primo.core.Numbering.Formats.Decimal;
import com.panavis.primo.core.Numbering.Formats.UpperRoman;
import com.panavis.primo.core.Numbering.UnitNumbering;

abstract class CaseBodyParser {

    private CaseParagraph caseParagraph;
    private  CaseBodyFormat caseBodyFormat;
    private SectionCaseBody section;

    CaseBodyParser(CaseParagraph caseParagraph,
                   CaseBodyFormat caseBodyFormat,
                   SectionCaseBody sectionCaseBody) {
        this.caseParagraph = caseParagraph;
        this.caseBodyFormat = caseBodyFormat;
        this.section = sectionCaseBody;
    }


    SectionResult parseBody(int startParagraph) {
        int nextParagraph = startParagraph;
        String nestedNextHeading = StringFormatting.EMPTY_STRING;
        while(caseParagraph.paragraphExists(nextParagraph) &&
                !caseBodyFormat.isCaseClosing(nextParagraph)) {
            UnitNumbering numbering = getHeadingUnitNumbering(nextParagraph);
            section.setCurrentNumbering(numbering)
                    .setStartingParagraph(nextParagraph)
                    .parse();

            addCaseBodySubsection(section, nextParagraph, nestedNextHeading);
            nextParagraph = section.getLastParagraph();
            nestedNextHeading = section.getNextHeading();
            nextParagraph = nestedNextHeading.isEmpty() ? nextParagraph : nextParagraph - 1;
        }
        JsonObject caseBody = getCaseBody();
        return new SectionResult(caseBody, nextParagraph);
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

    private UnitNumbering getNumbering(int number, String format) {
        return UnitNumbering.builder(format, "%" + number)
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


    abstract void addCaseBodySubsection(Section section,
                                        int startParagraph,
                                        String extractedHeading);

    abstract JsonArray getBodySubsections();

    private JsonObject getCaseBody() {
        JsonObject caseBody = new JsonObject();
        JsonArray escapedSubsections = JsonArray.escapeSubsections(getBodySubsections());
        caseBody.addNameValuePair(Keywords.CASE_BODY, escapedSubsections);
        return caseBody;
    }

}
