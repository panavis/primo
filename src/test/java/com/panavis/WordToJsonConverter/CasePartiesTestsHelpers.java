package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class CasePartiesTestsHelpers {

    private static Map<Integer, SectionResult> allActualPartiesSections = new HashMap<>();
    private static Map<Integer, JsonObject> allExpectedPartiesSections = new HashMap<>();

    static void setUpExpectedAndActualJsons() {
        setUpExpectedJsons();
        setUpActualJsons();
    }

    private static void setUpActualJsons() {
        for (int i = 0; i < TestsSetup.wordDocxData.size(); i++) {
            SectionResult partiesSectionResult = parseOneCaseAndReturnPartiesSection(i);
            allActualPartiesSections.put(i, partiesSectionResult);
        }
    }

    static SectionResult parseOneCaseAndReturnPartiesSection(int i) {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(i);
        Converter converter = TestsSetup.getConverterObject(wordDocument, Keywords.PARTIES);
        converter.parseCaseSections();
        return converter.getParsedCaseSection(Keywords.PARTIES);
    }

    private static void setUpExpectedJsons() {
        for (int i = 0; i < TestsSetup.expectedJsonContent.size(); i++) {
            JsonObject jsonCase = TestsSetup.expectedJsonContent.get(i);
            JsonArray expectedCase = jsonCase.getArrayByKey(Keywords.CASE);
            JsonObject expectedPartiesSection = expectedCase.getJsonByIndex(1);
            allExpectedPartiesSections.put(i, expectedPartiesSection);
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
        return allActualPartiesSections.get(wordDocIndex).getSectionContent();
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
        return partiesSubSections.getJsonByIndex(subsectionIndex);
    }

    private static JsonArray getActualContentForSubsection(JsonObject partiesSectionContent,
                                                   int subsectionIndex, String subsectionName, String sectionName) {

        JsonArray partiesSubSections = partiesSectionContent.getArrayByKey(sectionName);
        JsonObject actualPlaintiff = partiesSubSections.getJsonByIndex(subsectionIndex);
        return actualPlaintiff.getArrayByKey(subsectionName);
    }

    private static JsonObject getExpectedSubsection(int caseIndex, int subsectionIndex, String sectionName) {
        JsonObject expectedPartiesSection = allExpectedPartiesSections.get(caseIndex);
        JsonArray expectedPartiesSubSections = expectedPartiesSection.getArrayByKey(sectionName);
        return  expectedPartiesSubSections.getJsonByIndex(subsectionIndex);
    }

    static void assertExactContentEquals(String expected, String actual) {
        assertEquals(expected, actual);
    }

    static void assertContentLengthEquals(String expected, String actual) {
        assertEquals(expected.split(" ").length, actual.split(" ").length);
    }
}
