package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Keywords;
import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;
import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;

import java.util.Arrays;

class SubjectMatterParser {

    private WordParagraph wordParagraph;
    private JsonObject sectionContent;

    SubjectMatterParser(WordParagraph wordParagraph) {
        this.wordParagraph = wordParagraph;
        this.sectionContent = new JsonObject();
    }

    SectionResult parse(int startParagraph) {
        String heading = this.wordParagraph.getHeadingFromParagraph(startParagraph);
        String[] bodyArray = this.wordParagraph.getParagraph(startParagraph).getText().split(":");
        String[] bodyNoHeading = Arrays.copyOfRange(bodyArray, 1, bodyArray.length);
        String body = String.join(" ", bodyNoHeading).trim();
        JsonObject nestedJson = new JsonObject();
        JsonArray sectionNestedArray = new JsonArray();
        sectionNestedArray.putValue(body);
        nestedJson.addNameValuePair(heading, sectionNestedArray);
        JsonArray sectionArray = new JsonArray();
        sectionArray.putValue(nestedJson);
        this.sectionContent.addNameValuePair(Keywords.SUBJECT_MATTER, sectionArray);
        return new SectionResult(this.sectionContent, startParagraph);
    }
}
