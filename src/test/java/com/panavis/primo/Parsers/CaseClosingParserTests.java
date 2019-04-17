package com.panavis.primo.Parsers;

import static junit.framework.TestCase.assertEquals;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.JsonArray;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CaseClosingParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
        CaseClosingParserHelpers.setUpAllActualAndExpectedJsons();
    }

    @Ignore("Ignore: Closing section troubleshooter.")
    @Test
    public void troubleshootOneCaseSeparately() {
        JsonArray caseArray = CaseClosingParserHelpers.getActualCaseClosingArray(0);
    }

    @Test
    public void case_000_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(0);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(0);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_001_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(1);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(1);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_002_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(2);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(2);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_004_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(4);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(4);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_005_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(5);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(5);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_006_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(6);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(6);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_007_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(7);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(7);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_008_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(8);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(8);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_011_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(11);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(11);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_013_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(13);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(13);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_014_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(14);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(14);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_015_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(15);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(15);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_016_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(16);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(16);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_017_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(17);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(17);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_018_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(18);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(18);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_019_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(19);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(19);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_020_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(20);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(20);
        assertEquals(expectedClosingText, actualClosingText);
    }

    @Test
    public void case_021_hasExpectedClosingText() {
        String actualClosingText = CaseClosingParserHelpers.getActualClosingText(21);
        String expectedClosingText = CaseClosingParserHelpers.getExpectedClosingText(21);
        assertEquals(expectedClosingText, actualClosingText);
    }
}
