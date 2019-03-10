package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.ArrayList;
import java.util.List;

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
            Converter converter = TestsSetup.getConverterObject(wordDoc, Keywords.TITLE);
            converter.parseCaseSections();
            SectionResult titleResult = converter.getParsedCaseSection(Keywords.TITLE);
            JsonObject titleObject = titleResult.getSectionContent();
            String jsonTitle = titleObject.getStringByKey(Keywords.TITLE);
            allActualCaseTitles.add(jsonTitle);
        }
    }

    private static void setUpExpectedCaseTitles() {
        for (JsonObject caseFile : TestsSetup.expectedJsonContent) {
            JsonArray caseContent =  caseFile.getArrayByKey(Keywords.CASE);
            JsonObject titleObject = caseContent.getJsonByIndex(0);
            allExpectedCaseTitles.add(titleObject.getStringByKey(Keywords.TITLE));
        }
    }

    static void assertActualTitleMatchesExactlyExpectedTitle(int docIndex) {
        String actualTitle = allActualCaseTitles.get(docIndex);
        String expectedTitle = allExpectedCaseTitles.get(docIndex);
        assertEquals(expectedTitle, actualTitle);
    }
}
