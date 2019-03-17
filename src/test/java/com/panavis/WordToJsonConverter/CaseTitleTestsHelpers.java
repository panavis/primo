package com.panavis.WordToJsonConverter;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

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
        for (XWPFDocument wordDoc : TestsSetup.wordDocxData) {
            Converter converter = TestsSetup.getConverterObject(wordDoc, TITLE);
            converter.parseCaseSections();
            SectionResult titleResult = converter.getParsedCaseSection(TITLE);
            JsonObject titleObject = titleResult.getSectionContent();
            String jsonTitle = titleObject.getStringByKey(TITLE);
            allActualCaseTitles.add(jsonTitle);
        }
    }

    private static void setUpExpectedCaseTitles() {
        for (JsonObject caseFile : TestsSetup.expectedJsonContent) {
            JsonArray caseContent =  caseFile.getArrayByKey(CASE);
            JsonObject titleObject = caseContent.getJsonByIndex(0);
            allExpectedCaseTitles.add(titleObject.getStringByKey(TITLE));
        }
    }

    static void assertActualTitleMatchesExactlyExpectedTitle(int docIndex) {
        String actualTitle = allActualCaseTitles.get(docIndex);
        String expectedTitle = allExpectedCaseTitles.get(docIndex);
        assertEquals(expectedTitle, actualTitle);
    }
}
