package com.nexttran.WordToJsonConverter;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class ParseCaseTitleTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    @Test
    public void getCaseTitleShouldReturnTheTitleOfACaseFromItsWordDocument() {
        ArrayList<String> actualCaseTitles = ParseCaseTitleTestsHelpers.getActualCaseTitles(TestsSetup.wordDocxData);
        ArrayList<String> expectedCaseTitles = ParseCaseTitleTestsHelpers.getExpectedCaseTitles(TestsSetup.expectedJsonContent);
        ParseCaseTitleTestsHelpers.compareExpectedToActualTitles(actualCaseTitles, expectedCaseTitles);
    }

}