package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Format;
import com.nexttran.WordToJsonConverter.Constants.Keywords;
import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;
import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;

import java.util.Arrays;

class SubjectMatterParser {

    private WordParagraph wordParagraph;

    SubjectMatterParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
    }

    SectionResult parse(int startParagraph) {
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
        body = body.equals(Format.EMPTY_STRING) ?
                this.wordParagraph.getParagraph(++startParagraph).getText() : body;
        return body;
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
