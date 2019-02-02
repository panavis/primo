package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Keywords;
import com.nexttran.WordToJsonConverter.ResultTypes.CaseSectionResult;
import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class ParseCasePartiesTestsHelpers {

    static void assertJsonContainsPartiesSection(int caseIndex, String heading) {
        JsonObject partiesSectionContent = getActualPartiesWholeSection(caseIndex);
        assertTrue(partiesSectionContent.hasKey(heading));
    }

    static void assertPartiesHasCorrectNumberOfSubsections(int caseIndex, String heading, int numberOfSubsections) {
        JsonObject partiesSectionContent = getActualPartiesWholeSection(caseIndex);
        JsonArray partiesSubSections = partiesSectionContent.getArrayByKey(heading);
        assertEquals(numberOfSubsections, partiesSubSections.getSize());
    }

    private static JsonObject getActualPartiesWholeSection(int wordDocIndex) {
        XWPFDocument commCourtHuye2011Docx = WordToJsonTestsSetup.wordDocxData.get(wordDocIndex);
        WordToJsonConverter converter = new WordToJsonConverter(commCourtHuye2011Docx);
        int partiesSectionBeginningParagraph = converter.getCaseTitle().getNextParagraph();
        CaseSectionResult partiesSectionResult = converter.getCaseParties(partiesSectionBeginningParagraph);
        return partiesSectionResult.getSectionContent();
    }

    static ExpectedActualContent getExpectedAndActualContentForSubsection(int caseIndex, int subsectionIndex,
                                                                          String heading, String subheading) {
        JsonObject partiesActualContent = getActualPartiesWholeSection(caseIndex);
        String actualPartyContent = getActualContentForSubsection(
                partiesActualContent, subsectionIndex, subheading, heading).toString();

        JSONObject expectedParty = getExpectedSubsection(caseIndex, subsectionIndex, heading);
        String expectedPartyContent = expectedParty.get(subheading).toString();
        return new ExpectedActualContent(expectedPartyContent,
                actualPartyContent);
    }

    static JsonObject getActualPartiesSubsection(int caseIndex, String heading, int subsectionIndex) {
        JsonObject partiesSectionContent = getActualPartiesWholeSection(caseIndex);
        JsonArray partiesSubSections = partiesSectionContent.getArrayByKey(heading);
        return partiesSubSections.getJsonObjectByIndex(subsectionIndex);
    }

    private static JsonArray getActualContentForSubsection(JsonObject partiesSectionContent,
                                                   int subsectionIndex, String subsectionName, String sectionName) {

        JsonArray partiesSubSections = partiesSectionContent.getArrayByKey(sectionName);
        JsonObject actualPlaintiff = partiesSubSections.getJsonObjectByIndex(subsectionIndex);
        return actualPlaintiff.getArrayByKey(subsectionName);
    }

    private static JSONObject getExpectedSubsection(int caseIndex, int subsectionIndex, String sectionName) {
        JSONObject commCourtHuye2011Json = WordToJsonTestsSetup.expectedJsonContent.get(caseIndex);
        JSONArray expectedCase = (JSONArray) commCourtHuye2011Json.get(Keywords.CASE);
        JSONObject expectedPartiesSection = (JSONObject) expectedCase.get(1);
        JSONArray expectedPartiesSubSections = (JSONArray) expectedPartiesSection.get(sectionName);
        return (JSONObject) expectedPartiesSubSections.get(subsectionIndex);
    }

    static void assertExactContentEquals(String expected, String actual) {
        assertEquals(expected, actual);
    }

    static void assertContentLengthEquals(String expected, String actual) {
        assertEquals(expected.split(" ").length, actual.split(" ").length);
    }
}
