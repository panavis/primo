package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Keywords.*;
import static org.junit.Assert.*;

import com.panavis.primo.Primo;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
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
        for (int i = 0; i < TestsSetup.wordDocxData.size(); i++) {
            SectionResult partiesSectionResult = parseOneCaseAndReturnPartiesSection(i);
            allActualPartiesSections.put(i, partiesSectionResult);
        }
    }

    static SectionResult parseOneCaseAndReturnPartiesSection(int i) {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(i);
        Primo primo = TestsSetup.getConverterObject(wordDocument, PARTIES);
        primo.parseCaseSections();
        return primo.getParsedCaseSection(PARTIES);
    }

    private static void setUpExpectedJsons() {
        for (int i = 0; i < TestsSetup.expectedJsonContent.size(); i++) {
            JsonObject jsonCase = TestsSetup.expectedJsonContent.get(i);
            JsonArray expectedCase = jsonCase.getArrayByKey(CASE);
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
