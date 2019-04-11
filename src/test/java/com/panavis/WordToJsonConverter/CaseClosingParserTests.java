package com.panavis.WordToJsonConverter;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import static junit.framework.TestCase.assertEquals;

import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.BeforeClass;
import org.junit.Test;

public class CaseClosingParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    private String getExpectedClosingText(int caseIndex) {
        JsonObject expectedCaseObject = TestsSetup.expectedJsonContent.get(caseIndex);
        JsonArray caseArray = expectedCaseObject.getArrayByKey(CASE);
        JsonObject expectedCaseClosing = caseArray.getJsonByIndex(5);
        return expectedCaseClosing.getArrayByKey(CASE_CLOSING).toString();
    }

    private String getActualClosingText(int caseIndex) {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(caseIndex);
        Converter converter = TestsSetup.getConverterObject(wordDocument, CASE_CLOSING);
        converter.parseCaseSections();
        JsonObject actualCaseClosing = converter.getParsedCaseSection(CASE_CLOSING).getSectionContent();
        return actualCaseClosing.getArrayByKey(CASE_CLOSING).toString();
    }

    @Test
    public void case_000_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(0);
        String expectedClosingText = getExpectedClosingText(0);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_001_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(1);
        String expectedClosingText = getExpectedClosingText(1);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_002_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(2);
        String expectedClosingText = getExpectedClosingText(2);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_004_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(4);
        String expectedClosingText = getExpectedClosingText(4);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_005_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(5);
        String expectedClosingText = getExpectedClosingText(5);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_006_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(6);
        String expectedClosingText = getExpectedClosingText(6);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_007_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(7);
        String expectedClosingText = getExpectedClosingText(7);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_008_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(8);
        String expectedClosingText = getExpectedClosingText(8);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_011_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(11);
        String expectedClosingText = getExpectedClosingText(11);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_013_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(13);
        String expectedClosingText = getExpectedClosingText(13);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_015_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(15);
        String expectedClosingText = getExpectedClosingText(15);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_016_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(16);
        String expectedClosingText = getExpectedClosingText(16);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_017_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(17);
        String expectedClosingText = getExpectedClosingText(17);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_018_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(18);
        String expectedClosingText = getExpectedClosingText(18);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_019_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(19);
        String expectedClosingText = getExpectedClosingText(19);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_020_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(20);
        String expectedClosingText = getExpectedClosingText(20);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_021_hasExpectedClosingText() {
        String actualClosingText = getActualClosingText(21);
        String expectedClosingText = getExpectedClosingText(21);
        assertEquals(expectedClosingText, actualClosingText);
    }
}
