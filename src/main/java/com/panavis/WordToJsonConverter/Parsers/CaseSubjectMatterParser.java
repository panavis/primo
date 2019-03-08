package com.panavis.WordToJsonConverter.Parsers;

import com.panavis.WordToJsonConverter.Constants.Format;
import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Utils.StringFormatting;
import com.panavis.WordToJsonConverter.Style.WordParagraph;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

import java.util.Arrays;

public class CaseSubjectMatterParser implements ICaseSubjectMatter {

    private WordParagraph wordParagraph;
    private int numberOfSubsections;
    private int subsectionStart;
    private JsonObject sectionContent;
    private JsonArray sectionArray;

    public CaseSubjectMatterParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.numberOfSubsections = 1;
        this.sectionContent = new JsonObject();
        this.sectionArray = new JsonArray();
    }

    public SectionResult parse(int startParagraph) {
        subsectionStart = startParagraph;
        while(numberOfSubsections > 0) {
            String heading = getSubjectMatterHeading(subsectionStart);
            String body = getSubjectMatterBody(subsectionStart);
            addSubsectionContent(heading, body);
            numberOfSubsections--;
        }
        return new SectionResult(sectionContent, startParagraph);
    }

    private String getSubjectMatterHeading(int startParagraph) {
        return wordParagraph.getHeadingFromParagraph(startParagraph);
    }

    private String getSubjectMatterBody(int startParagraph) {
        String body = getFirstParagraphOfBody(startParagraph);
        int paragraphIndex = startParagraph + 1;
        StringBuilder bodyContent = new StringBuilder();
        bodyContent.append(body).append(bodyContent.length() == 0 ?
                "" : wordParagraph.getBlankLinesAfterParagraph(startParagraph));
        while(isStillSubjectMatterSubsection(paragraphIndex)) {
            addParagraphIfCaseSensitive(paragraphIndex, bodyContent);
            paragraphIndex++;
        }
        if (hasAnotherSubjectMatterSubsection(paragraphIndex))
            updateSubsectionStartAndNumber(paragraphIndex);
        return bodyContent.toString().trim();
    }

    private void updateSubsectionStartAndNumber(int paragraphIndex) {
        numberOfSubsections++;
        subsectionStart = paragraphIndex;
    }

    private void addParagraphIfCaseSensitive(int paragraphIndex, StringBuilder bodyContent) {
        String paragraphText = wordParagraph.getParagraphWithNumbering(paragraphIndex);
        if (StringFormatting.isCaseSensitive(paragraphText))
            bodyContent.append(paragraphText)
                    .append(wordParagraph.getBlankLinesAfterParagraph(paragraphIndex));
    }

    private String getFirstParagraphOfBody(int startParagraph) {
        String[] bodyArray = wordParagraph.getParagraph(startParagraph).getText().split(":");
        String[] bodyNoHeading = Arrays.copyOfRange(bodyArray, 1, bodyArray.length);
        return String.join(" ", bodyNoHeading).trim();
    }

    private boolean isStillSubjectMatterSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraph(paragraphIndex).getText().toLowerCase();
        if (hasAnotherSubjectMatterSubsection(paragraphIndex)) return false;
        return !(text.contains("imiterere") && text.contains("y"));
    }

    private boolean hasAnotherSubjectMatterSubsection(int paragraphIndex) {
        String text = wordParagraph.getParagraph(paragraphIndex).getText().toLowerCase();
        for (String heading: Headings.SUBJECT_MATTER_HEADINGS) {
            if (text.startsWith(heading.toLowerCase())) return true;
        }
        return false;
    }

    private void addSubsectionContent(String heading, String body) {
        JsonObject nestedJson = new JsonObject();
        JsonArray sectionNestedArray = new JsonArray();
        for (String paragraph : body.split(Format.DOUBLE_BLANK)) {
            sectionNestedArray.putValue(paragraph);
        }
        nestedJson.addNameValuePair(heading, sectionNestedArray);
        sectionArray.putValue(nestedJson);
        sectionContent.addNameValuePair(Keywords.SUBJECT_MATTER, sectionArray);
    }
}
