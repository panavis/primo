package com.panavis.primo.Parsers;

import com.panavis.primo.Primo;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

import java.util.HashMap;
import java.util.Map;

import static com.panavis.primo.Constants.Keywords.CASE;
import static com.panavis.primo.Constants.Keywords.CASE_CLOSING;

class CaseClosingParserHelpers {

    private static Map<Integer, JsonArray> allActualCaseClosingJsons = new HashMap<>();
    private static Map<Integer, JsonArray> allExpectedCaseClosingJsons = new HashMap<>();

    static void setUpAllActualAndExpectedJsons() {
        setUpActualJsons();
        setUpExpectedJsons();
    }

    private static void setUpActualJsons() {
        for (int i = 0; i < TestsSetup.wordFilePaths.size(); i++) {
            allActualCaseClosingJsons.put(i, getActualCaseClosingArray(i));
        }
    }

    static JsonArray getActualCaseClosingArray(int caseIndex) {
        String wordFilePath = TestsSetup.wordFilePaths.get(caseIndex);
        Primo primo = TestsSetup.getConverterObject(wordFilePath, CASE_CLOSING);
        primo.parseCaseSections();
        JsonObject actualCaseClosing = primo.getParsedCaseSection(CASE_CLOSING).getSectionContent();
        return actualCaseClosing.getArrayByKey(CASE_CLOSING);
    }

    static String getActualClosingText(int caseIndex) {
        return allActualCaseClosingJsons.get(caseIndex).toString();
    }

    private static void setUpExpectedJsons() {
        for (int i = 0; i < TestsSetup.expectedJsonContent.size(); i++) {
            allExpectedCaseClosingJsons.put(i, getExpectedCaseClosingArray(i));
        }
    }

    private static JsonArray getExpectedCaseClosingArray(int caseIndex) {
        JsonObject expectedCaseObject = TestsSetup.expectedJsonContent.get(caseIndex);
        JsonArray caseArray = expectedCaseObject.getArrayByKey(CASE);
        JsonObject expectedCaseClosing = caseArray.getJsonByIndex(5);
        return expectedCaseClosing.getArrayByKey(CASE_CLOSING);
    }

    static String getExpectedClosingText(int caseIndex) {
        return allExpectedCaseClosingJsons.get(caseIndex).toString();
    }
}
