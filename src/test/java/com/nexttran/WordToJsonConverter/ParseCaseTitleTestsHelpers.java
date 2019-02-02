package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Keywords;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ParseCaseTitleTestsHelpers {

    static ArrayList<String> getActualCaseTitles(ArrayList<XWPFDocument> wordDocxData) {
        ArrayList<String> actualCaseTitles = new ArrayList<>();
        for (XWPFDocument wordDoc : wordDocxData) {
            WordToJsonConverter converter = new WordToJsonConverter(wordDoc);
            String jsonTitle = converter.getCaseTitle().getSectionContent().getStringByKey(Keywords.TITLE);
            actualCaseTitles.add(jsonTitle);
        }
        return actualCaseTitles;
    }

    static ArrayList<String> getExpectedCaseTitles(ArrayList<JSONObject> expectedJsonContent) {
        ArrayList<String> expectedCaseTitles = new ArrayList<>();
        for (JSONObject caseFile : expectedJsonContent) {
            JSONArray caseContent = (JSONArray) caseFile.get(Keywords.CASE);
            JSONObject titleObject = (JSONObject) caseContent.get(0);
            expectedCaseTitles.add((String) titleObject.get(Keywords.TITLE));
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
