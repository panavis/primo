package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Style.UnitNumbering;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

import java.util.regex.Pattern;

public class CaseBodyParser implements ICaseBodyParser {

    private WordParagraph wordParagraph;
    private JsonArray bodySubsections;

    public CaseBodyParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.bodySubsections = new JsonArray();
    }

    public SectionResult parse(int startParagraph) {
        if (wordParagraph.startsCaseBackgroundSection(startParagraph)) {
            int nextParagraph = startParagraph;
            while(!isCaseClosing(nextParagraph)) {
                UnitNumbering currentUnitNumbering = wordParagraph.getUnitNumbering(nextParagraph);
                String heading = wordParagraph.getParagraphText(nextParagraph);
                heading = StringFormatting.trimColons(heading);
                Subsection subsection = new SectionBody(wordParagraph, nextParagraph)
                        .setCurrentNumbering(currentUnitNumbering)
                        .parse();
                addCaseBodySubsection(heading, subsection);
                nextParagraph = subsection.getLastParagraph();
            }
        }
        JsonObject caseBody = getCaseBody();
        return new SectionResult(caseBody, 0);
    }

   private boolean isCaseClosing(int nextParagraph) {
        String paragraphText = wordParagraph.getParagraphText(nextParagraph).toLowerCase();

        return isClosingSentence(nextParagraph, paragraphText) ||
                isClosingHeading(nextParagraph, paragraphText);
    }

    private boolean isClosingHeading(int nextParagraph, String paragraphText) {
        return wordParagraph.isIndentedAndCapitalized(nextParagraph) && paragraphText.contains("inteko");
    }

    private boolean isClosingSentence(int paragraphIndex, String text) {
        String firstWord = text.split(" ")[0];
        String style = wordParagraph.getUnitNumbering(paragraphIndex).style;
        Pattern dateAllDigits = Pattern.compile("\\b\\d{1,2}\\/\\d{1,2}\\/\\d{4}");
        Pattern dateMonthSpelled = Pattern.compile("\\b\\d{1,2}\\b.+\\b\\d{4}");
        return (dateAllDigits.matcher(text).find() ||
                    dateMonthSpelled.matcher(text).find())
                &&  StringFormatting.isCaseSensitive(firstWord) &&
                !(style.equals("ListParagraph"));
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
