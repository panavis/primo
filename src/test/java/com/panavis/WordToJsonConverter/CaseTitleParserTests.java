package com.panavis.WordToJsonConverter;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class CaseTitleParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    @Test
    public void getCaseTitleShouldReturnTheTitleOfACaseFromItsWordDocument() {
        ArrayList<String> actualCaseTitles = CaseTitleParserTestsHelpers.getActualCaseTitles(TestsSetup.wordDocxData);
        ArrayList<String> expectedCaseTitles = CaseTitleParserTestsHelpers.getExpectedCaseTitles(TestsSetup.expectedJsonContent);
        CaseTitleParserTestsHelpers.compareExpectedToActualTitles(actualCaseTitles, expectedCaseTitles);
    }

}
