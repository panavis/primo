package com.nexttran.WordToJsonConverter;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class ParseCaseTitleTests {

    @BeforeClass
    public static void setUp() {
        WordToJsonTestsSetup.setUp();
    }

    @Test
    public void getCaseTitleShouldReturnTheTitleOfACaseFromItsWordDocument() {
        ArrayList<String> actualCaseTitles = ParseCaseTitleTestsHelpers.getActualCaseTitles(WordToJsonTestsSetup.wordDocxData);
        ArrayList<String> expectedCaseTitles = ParseCaseTitleTestsHelpers.getExpectedCaseTitles(WordToJsonTestsSetup.expectedJsonContent);
        ParseCaseTitleTestsHelpers.compareExpectedToActualTitles(actualCaseTitles, expectedCaseTitles);
    }

}
