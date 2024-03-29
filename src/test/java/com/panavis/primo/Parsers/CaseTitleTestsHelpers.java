package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Keywords.*;

import com.panavis.primo.Primo;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.*;
import org.json.JSONArray;

import java.util.*;

import static org.junit.Assert.assertEquals;

class CaseTitleTestsHelpers {

    private static List<String> allActualCaseTitles = new ArrayList<>();
    private static List<String> allExpectedCaseTitles = new ArrayList<>();

    static void setupActualAndExpectedCaseTitles() {
        setupActualCaseTitles();
        setUpExpectedCaseTitles();
    }

    private static void setupActualCaseTitles() {
        for (String wordFilePath : TestsSetup.wordFilePaths) {
            Primo primo = TestsSetup.getConverterObject(wordFilePath, CASE_TITLE);
            primo.parseCaseSections();
            SectionResult titleResult = primo.getParsedCaseSection(CASE_TITLE);
            JsonObject titleObject = titleResult.getSectionContent();
            String jsonTitle = titleObject.getStringByKey(CASE_TITLE);
            allActualCaseTitles.add(jsonTitle);
        }
    }

    private static void setUpExpectedCaseTitles() {
        for (int i = 0; i < TestsSetup.expectedJsonContent.size(); i++) {
            allExpectedCaseTitles.add(getExpectedCaseTitle(i));
        }
    }

    static String getExpectedCaseTitle(int caseIndex) {
        JsonObject caseFile = TestsSetup.expectedJsonContent.get(caseIndex);
        JsonArray caseContent =  caseFile.getArrayByKey(CASE);
        JsonObject titleObject = caseContent.getJsonByIndex(0);
        return titleObject.getStringByKey(CASE_TITLE);
    }

    static void assertActualTitleMatchesExactlyExpectedTitle(int docIndex) {
        String actualTitle = allActualCaseTitles.get(docIndex);
        String expectedTitle = allExpectedCaseTitles.get(docIndex);
        assertEquals(expectedTitle, actualTitle);
    }
}
