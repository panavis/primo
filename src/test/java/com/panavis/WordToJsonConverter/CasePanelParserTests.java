package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CasePanelParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    private JsonArray getCasePanelArray(int caseIndex) {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(caseIndex);
        Converter converter = TestsSetup.getConverterObject(wordDocument, INTEKO);
        converter.parseCaseSections();
        JsonObject actualCasePanel = converter.getParsedCaseSection(INTEKO).getSectionContent();
        return actualCasePanel.getArrayByKey(INTEKO);
    }

    private JsonArray getExpectedCasePanel(int caseIndex) {
        JsonObject expectedCase = TestsSetup.expectedJsonContent.get(caseIndex);
        JsonObject expectedCasePanel = expectedCase.getArrayByKey(CASE).getJsonByIndex(6);
        return expectedCasePanel.getArrayByKey(INTEKO);
    }

    @Test
    public void case_000_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = getCasePanelArray(0);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_000_firstPanelistShouldBeJudge() {
        JsonObject firstPanelist = getCasePanelArray(0).getJsonByIndex(0);
        assertTrue(firstPanelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_000_firstPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(0).getJsonByIndex(0);
        String actualName = firstPanelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = getExpectedCasePanel(0).getJsonByIndex(0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_000_secondPanelistShouldBeWriter() {
        JsonObject firstPanelist = getCasePanelArray(0).getJsonByIndex(1);
        assertTrue(firstPanelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_000_secondPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(0).getJsonByIndex(1);
        String actualName = firstPanelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = getExpectedCasePanel(0).getJsonByIndex(1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_001_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = getCasePanelArray(1);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_001_firstPanelistShouldBeJudge() {
        JsonObject firstPanelist = getCasePanelArray(1).getJsonByIndex(0);
        assertTrue(firstPanelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_001_firstPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(1).getJsonByIndex(0);
        String actualName = firstPanelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = getExpectedCasePanel(1).getJsonByIndex(0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_001_secondPanelistShouldBeWriter() {
        JsonObject firstPanelist = getCasePanelArray(1).getJsonByIndex(1);
        assertTrue(firstPanelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_001_secondPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(1).getJsonByIndex(1);
        String actualName = firstPanelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = getExpectedCasePanel(1).getJsonByIndex(1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_002_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = getCasePanelArray(2);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_002_firstPanelistShouldBeJudge() {
        JsonObject firstPanelist = getCasePanelArray(2).getJsonByIndex(0);
        assertTrue(firstPanelist.hasKey("UMUCAMANZA:"));
    }

    @Test
    public void case_002_firstPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(2).getJsonByIndex(0);
        String actualName = firstPanelist.getStringByKey("UMUCAMANZA:");

        JsonObject expectedPanelist = getExpectedCasePanel(2).getJsonByIndex(0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA:");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_002_secondPanelistShouldBeWriter() {
        JsonObject firstPanelist = getCasePanelArray(2).getJsonByIndex(1);
        assertTrue(firstPanelist.hasKey("UMWANDITSI:"));
    }

    @Test
    public void case_002_secondPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(2).getJsonByIndex(1);
        String actualName = firstPanelist.getStringByKey("UMWANDITSI:");

        JsonObject expectedPanelist = getExpectedCasePanel(2).getJsonByIndex(1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI:");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_004_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = getCasePanelArray(4);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_004_firstPanelistShouldBeJudge() {
        JsonObject firstPanelist = getCasePanelArray(4).getJsonByIndex(0);
        assertTrue(firstPanelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_004_firstPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(4).getJsonByIndex(0);
        String actualName = firstPanelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = getExpectedCasePanel(4).getJsonByIndex(0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_004_secondPanelistShouldBeWriter() {
        JsonObject firstPanelist = getCasePanelArray(4).getJsonByIndex(1);
        assertTrue(firstPanelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_004_secondPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(4).getJsonByIndex(1);
        String actualName = firstPanelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = getExpectedCasePanel(4).getJsonByIndex(1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_005_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = getCasePanelArray(5);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_005_firstPanelistShouldBeJudge() {
        JsonObject firstPanelist = getCasePanelArray(5).getJsonByIndex(0);
        assertTrue(firstPanelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_005_firstPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(5).getJsonByIndex(0);
        String actualName = firstPanelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = getExpectedCasePanel(5).getJsonByIndex(0);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_005_secondPanelistShouldBeWriter() {
        JsonObject firstPanelist = getCasePanelArray(5).getJsonByIndex(1);
        assertTrue(firstPanelist.hasKey("Umwanditsi"));
    }

    @Test
    public void case_005_secondPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(5).getJsonByIndex(1);
        String actualName = firstPanelist.getStringByKey("Umwanditsi");

        JsonObject expectedPanelist = getExpectedCasePanel(5).getJsonByIndex(1);
        String expectedName = expectedPanelist.getStringByKey("Umwanditsi");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_006_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = getCasePanelArray(6);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_007_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = getCasePanelArray(7);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_007_firstPanelistShouldBeJudge() {
        JsonObject firstPanelist = getCasePanelArray(7).getJsonByIndex(0);
        assertTrue(firstPanelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_007_firstPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(7).getJsonByIndex(0);
        String actualName = firstPanelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = getExpectedCasePanel(7).getJsonByIndex(0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_007_secondPanelistShouldBeWriter() {
        JsonObject firstPanelist = getCasePanelArray(7).getJsonByIndex(1);
        assertTrue(firstPanelist.hasKey("UMWANDITSI W\u2019URUKIKO"));
    }

    @Test
    public void case_007_secondPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(7).getJsonByIndex(1);
        String actualName = firstPanelist.getStringByKey("UMWANDITSI W\u2019URUKIKO");

        JsonObject expectedPanelist = getExpectedCasePanel(7).getJsonByIndex(1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI W\u2019URUKIKO");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_008_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = getCasePanelArray(8);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_008_firstPanelistShouldBeJudge() {
        JsonObject firstPanelist = getCasePanelArray(8).getJsonByIndex(0);
        assertTrue(firstPanelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_008_firstPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(8).getJsonByIndex(0);
        String actualName = firstPanelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = getExpectedCasePanel(8).getJsonByIndex(0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_008_secondPanelistShouldBeWriter() {
        JsonObject firstPanelist = getCasePanelArray(8).getJsonByIndex(1);
        assertTrue(firstPanelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_008_secondPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(8).getJsonByIndex(1);
        String actualName = firstPanelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = getExpectedCasePanel(8).getJsonByIndex(1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_011_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = getCasePanelArray(11);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_011_firstPanelistShouldBeJudge() {
        JsonObject firstPanelist = getCasePanelArray(11).getJsonByIndex(0);
        assertTrue(firstPanelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_011_firstPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(11).getJsonByIndex(0);
        String actualName = firstPanelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = getExpectedCasePanel(11).getJsonByIndex(0);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_011_secondPanelistShouldBeWriter() {
        JsonObject firstPanelist = getCasePanelArray(11).getJsonByIndex(1);
        assertTrue(firstPanelist.hasKey("Umwanditsi"));
    }

    @Test
    public void case_011_secondPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(11).getJsonByIndex(1);
        String actualName = firstPanelist.getStringByKey("Umwanditsi");

        JsonObject expectedPanelist = getExpectedCasePanel(11).getJsonByIndex(1);
        String expectedName = expectedPanelist.getStringByKey("Umwanditsi");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_013_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = getCasePanelArray(13);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_013_firstPanelistShouldBeJudge() {
        JsonObject firstPanelist = getCasePanelArray(13).getJsonByIndex(0);
        assertTrue(firstPanelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_013_firstPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(13).getJsonByIndex(0);
        String actualName = firstPanelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = getExpectedCasePanel(13).getJsonByIndex(0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_013_secondPanelistShouldBeWriter() {
        JsonObject firstPanelist = getCasePanelArray(13).getJsonByIndex(1);
        assertTrue(firstPanelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_013_secondPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray(13).getJsonByIndex(1);
        String actualName = firstPanelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = getExpectedCasePanel(13).getJsonByIndex(1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }
}
