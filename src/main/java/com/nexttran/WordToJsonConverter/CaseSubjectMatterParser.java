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
        return wordParagraph.getHeadingFromParagraph(startParagraph);
    }

    private String getSubjectMatterBody(int startParagraph) {
        String[] bodyArray = wordParagraph.getParagraph(startParagraph).getText().split(":");
        String[] bodyNoHeading = Arrays.copyOfRange(bodyArray, 1, bodyArray.length);
        String body = String.join(" ", bodyNoHeading).trim();

        int paragraphIndex = startParagraph + 1;
        StringBuilder bodyContent = new StringBuilder();
        bodyContent.append(body).append(bodyContent.length() == 0 ?
                "" : wordParagraph.getBlankLinesAfterParagraph(startParagraph));
        while(isStillSubjectMatterSection(paragraphIndex)) {
            bodyContent.append(wordParagraph.getParagraphWithNumbering(paragraphIndex))
                        .append(wordParagraph.getBlankLinesAfterParagraph(paragraphIndex));
            paragraphIndex++;
        }
        return bodyContent.toString().trim();
    }

    private boolean isStillSubjectMatterSection(int paragraphIndex) {
        String text = wordParagraph.getParagraph(paragraphIndex).getText().toLowerCase();
        return !(text.contains("imiterere") && text.contains("y") && text.contains("urubanza"));
    }

    private JsonObject getSectionContent(String heading, String body) {
        JsonObject nestedJson = new JsonObject();
        JsonArray sectionNestedArray = new JsonArray();
        for (String paragraph : body.split(Format.DOUBLE_BLANK)) {
            sectionNestedArray.putValue(paragraph);
        }
        nestedJson.addNameValuePair(heading, sectionNestedArray);
        JsonArray sectionArray = new JsonArray();
        sectionArray.putValue(nestedJson);
        JsonObject sectionContent = new JsonObject();
        sectionContent.addNameValuePair(Keywords.SUBJECT_MATTER, sectionArray);
        return sectionContent;
    }
}
