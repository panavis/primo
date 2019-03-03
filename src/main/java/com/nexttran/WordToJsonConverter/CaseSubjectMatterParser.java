package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Format;
import com.nexttran.WordToJsonConverter.Constants.Keywords;
import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;
import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;

import java.util.Arrays;

class CaseSubjectMatterParser implements ICaseSubjectMatter {

    private WordParagraph wordParagraph;

    CaseSubjectMatterParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    public SectionResult parse(int startParagraph) {
        String heading = getSubjectMatterHeading(startParagraph);
        String body = getSubjectMatterBody(startParagraph);
        JsonObject sectionContent = getSectionContent(heading, body);
        return new SectionResult(sectionContent, startParagraph);
    }

    private String getSubjectMatterHeading(int startParagraph) {
        return this.wordParagraph.getHeadingFromParagraph(startParagraph);
    }

    private String getSubjectMatterBody(int startParagraph) {
        String[] bodyArray = this.wordParagraph.getParagraph(startParagraph).getText().split(":");
        String[] bodyNoHeading = Arrays.copyOfRange(bodyArray, 1, bodyArray.length);
        String body = String.join(" ", bodyNoHeading).trim();

        int paragraphIndex = startParagraph + 1;
        StringBuilder bodyContent = new StringBuilder(body);
        while(isStillSubjectMatterSection(paragraphIndex)) {
            bodyContent.append(bodyContent.length() == 0 ? "" : Format.LINE_SEPARATOR)
                        .append(this.wordParagraph.getParagraphWithNumbering(paragraphIndex));
            paragraphIndex++;
        }
        return bodyContent.toString();
    }

    private boolean isStillSubjectMatterSection(int paragraphIndex) {
        String text = this.wordParagraph.getParagraph(paragraphIndex).getText().toLowerCase();
        return !(text.contains("imiterere") && text.contains("y") && text.contains("urubanza"));
    }

    private JsonObject getSectionContent(String heading, String body) {
        JsonObject nestedJson = new JsonObject();
        JsonArray sectionNestedArray = new JsonArray();
        sectionNestedArray.putValue(body);
        nestedJson.addNameValuePair(heading, sectionNestedArray);
        JsonArray sectionArray = new JsonArray();
        sectionArray.putValue(nestedJson);
        JsonObject sectionContent = new JsonObject();
        sectionContent.addNameValuePair(Keywords.SUBJECT_MATTER, sectionArray);
        return sectionContent;
    }
}
