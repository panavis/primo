package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Keywords.*;
import static org.junit.Assert.*;

import com.panavis.primo.Primo;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.*;

import java.util.*;

class ExpectedActualContent {

    String expectedContent;
    String actualContent;

    ExpectedActualContent(String expectedContent, String actualContent) {
        this.expectedContent = expectedContent;
        this.actualContent = actualContent;
    }
}

class CasePartiesTestsHelpers {

    private static Map<Integer, SectionResult> allActualPartiesSections = new HashMap<>();
    private static Map<Integer, JsonObject> allExpectedPartiesSections = new HashMap<>();

    static void setUpExpectedAndActualJsons() {
        setUpExpectedJsons();
        setUpActualJsons();
    }

    private static void setUpActualJsons() {
        for (int i = 0; i < TestsSetup.wordFilePaths.size(); i++) {
            SectionResult partiesSectionResult = parseOneCaseAndReturnPartiesSection(i);
            allActualPartiesSections.put(i, partiesSectionResult);
        }
    }

    static SectionResult parseOneCaseAndReturnPartiesSection(int i) {
        String wordFilePath = TestsSetup.wordFilePaths.get(i);
        Primo primo = TestsSetup.getConverterObject(wordFilePath, CASE_PARTIES);
        primo.parseCaseSections();
        return primo.getParsedCaseSection(CASE_PARTIES);
    }

    private static void setUpExpectedJsons() {
        for (int i = 0; i < TestsSetup.expectedJsonContent.size(); i++) {
            JsonObject jsonCase = TestsSetup.expectedJsonContent.get(i);
            JsonArray expectedCase = jsonCase.getArrayByKey(CASE);
            JsonObject expectedPartiesSection = expectedCase.getJsonByIndex(1);
            allExpectedPartiesSections.put(i, expectedPartiesSection);
        }
    }

    static void assertCorrectNumberOfSubsections(int caseIndex, int numberOfSubsections) {
        JsonObject partiesSectionContent = getActualPartiesWholeSection(caseIndex);
        JsonArray partiesSubSections = partiesSectionContent.getArrayByKey(CASE_PARTIES);
        assertEquals(numberOfSubsections, partiesSubSections.getSize());
    }

    private static JsonObject getActualPartiesWholeSection(int wordDocIndex) {
        return allActualPartiesSections.get(wordDocIndex).getSectionContent();
    }

    static ExpectedActualContent getExpectedAndActualContentForSubsection(int caseIndex, int subsectionIndex,
                                                                          String subheading) {
        JsonObject partiesActualContent = getActualPartiesWholeSection(caseIndex);
        String actualPartyContent = getActualContentForSubsection(
                partiesActualContent, subsectionIndex, subheading).toString();
        JsonObject expectedParty = getExpectedSubsection(caseIndex, subsectionIndex, CASE_PARTIES);
        String expectedPartyContent = expectedParty.getArrayByKey(subheading).toString();
        return new ExpectedActualContent(expectedPartyContent,
                actualPartyContent);
    }

    static JsonObject getActualPartiesSubsection(int caseIndex, int subsectionIndex) {
        JsonObject partiesSectionContent = getActualPartiesWholeSection(caseIndex);
        JsonArray partiesSubSections = partiesSectionContent.getArrayByKey(CASE_PARTIES);
        return partiesSubSections.getJsonByIndex(subsectionIndex);
    }

    private static JsonArray getActualContentForSubsection(JsonObject partiesSectionContent,
                                                           int subsectionIndex, String subsectionName) {

        JsonArray partiesSubSections = partiesSectionContent.getArrayByKey(CASE_PARTIES);
        JsonObject actualPlaintiff = partiesSubSections.getJsonByIndex(subsectionIndex);
        return actualPlaintiff.getArrayByKey(subsectionName);
    }

    static JsonObject getExpectedSubsection(int caseIndex, int subsectionIndex, String sectionName) {
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
