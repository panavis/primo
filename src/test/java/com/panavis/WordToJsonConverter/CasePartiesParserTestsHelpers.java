package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class CasePartiesParserTestsHelpers {

    static List<JsonObject> allActualPartiesSections = new ArrayList<>();
    static List<JsonObject> allExpectedPartiesSections = new ArrayList<>();

    static void setUpExpectedAndActualJsons() {
        setUpExpectedJsons();
        setUpActualJsons();
    }

    private static void setUpActualJsons() {
        for (XWPFDocument wordDocument : TestsSetup.wordDocxData) {
            Converter converter = TestsSetup.getConverterObject(wordDocument, Keywords.PARTIES);
            converter.parseCaseSections();
            SectionResult partiesSectionResult = converter.getParsedCaseSection(Keywords.PARTIES);
            allActualPartiesSections.add(partiesSectionResult.getSectionContent());
        }
    }

    private static void setUpExpectedJsons() {
        for (JsonObject jsonCase : TestsSetup.expectedJsonContent) {
            JsonArray expectedCase = jsonCase.getArrayByKey(Keywords.CASE);
            JsonObject expectedPartiesSection = expectedCase.getJsonObjectByIndex(1);
            allExpectedPartiesSections.add(expectedPartiesSection);
        }
    }

    static void assertJsonContainsPartiesSection(int caseIndex, String heading) {
        JsonObject partiesSectionContent = getActualPartiesWholeSection(caseIndex);
        assertTrue(partiesSectionContent.hasKey(heading));
    }

    static void assertCorrectNumberOfSubsections(int caseIndex, String heading, int numberOfSubsections) {
        JsonObject partiesSectionContent = getActualPartiesWholeSection(caseIndex);
        JsonArray partiesSubSections = partiesSectionContent.getArrayByKey(heading);
        assertEquals(numberOfSubsections, partiesSubSections.getSize());
    }

    private static JsonObject getActualPartiesWholeSection(int wordDocIndex) {
        return allActualPartiesSections.get(wordDocIndex);
    }

    static ExpectedActualContent getExpectedAndActualContentForSubsection(int caseIndex, int subsectionIndex,
                                                                          String heading, String subheading) {
        JsonObject partiesActualContent = getActualPartiesWholeSection(caseIndex);
        String actualPartyContent = getActualContentForSubsection(
                partiesActualContent, subsectionIndex, subheading, heading).toString();

        JsonObject expectedParty = getExpectedSubsection(caseIndex, subsectionIndex, heading);
        String expectedPartyContent = expectedParty.getArrayByKey(subheading).toString();
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

    private static JsonObject getExpectedSubsection(int caseIndex, int subsectionIndex, String sectionName) {
        JsonObject expectedPartiesSection = allExpectedPartiesSections.get(caseIndex);
        JsonArray expectedPartiesSubSections = expectedPartiesSection.getArrayByKey(sectionName);
        return  expectedPartiesSubSections.getJsonObjectByIndex(subsectionIndex);
    }

    static void assertExactContentEquals(String expected, String actual) {
        assertEquals(expected, actual);
    }

    static void assertContentLengthEquals(String expected, String actual) {
        assertEquals(expected.split(" ").length, actual.split(" ").length);
    }
}
