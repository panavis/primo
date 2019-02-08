package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Keywords;
import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;
import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

class ParseCaseTitleTestsHelpers {

    static ArrayList<String> getActualCaseTitles(ArrayList<XWPFDocument> wordDocxData) {
        ArrayList<String> actualCaseTitles = new ArrayList<>();
        for (XWPFDocument wordDoc : wordDocxData) {
            WordToJsonConverter converter = TestsSetup.getConverterObject(wordDoc);
            converter.parseCaseSections();
            SectionResult titleResult = converter.getParsedCaseSection(Keywords.TITLE);
            JsonObject titleObject = titleResult.getSectionContent();
            String jsonTitle = titleObject.getStringByKey(Keywords.TITLE);
            actualCaseTitles.add(jsonTitle);
        }
        return actualCaseTitles;
    }

    static ArrayList<String> getExpectedCaseTitles(ArrayList<JsonObject> expectedJsonContent) {
        ArrayList<String> expectedCaseTitles = new ArrayList<>();
        for (JsonObject caseFile : expectedJsonContent) {
            JsonArray caseContent =  caseFile.getArrayByKey(Keywords.CASE);
            JsonObject titleObject = caseContent.getJsonObjectByIndex(0);
            expectedCaseTitles.add(titleObject.getStringByKey(Keywords.TITLE));
        }
        return expectedCaseTitles;
    }

    static void compareExpectedToActualTitles(ArrayList<String> actualCaseTitles,
                                              ArrayList<String> expectedCaseTitles) {

        Iterator<String> actualTitles = actualCaseTitles.iterator();
        Iterator<String> expectedTitles = expectedCaseTitles.iterator();

        while (expectedTitles.hasNext() && actualTitles.hasNext())
            numberOfWordsInExpectedEqualsInActualTitle(actualTitles, expectedTitles);
    }

    private static void numberOfWordsInExpectedEqualsInActualTitle(Iterator<String> actualTitles, Iterator<String> expectedTitles) {
        int numberOfWordsInExpectedTitle = expectedTitles.next().split(" ").length;
        int numberOfWordsInActualTitle = actualTitles.next().split(" ").length;
        assertEquals(numberOfWordsInExpectedTitle, numberOfWordsInActualTitle);
    }
}
