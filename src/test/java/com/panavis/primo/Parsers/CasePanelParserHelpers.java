package com.panavis.primo.Parsers;

import com.panavis.primo.Primo;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

import java.util.HashMap;
import java.util.Map;

import static com.panavis.primo.Constants.Keywords.CASE;
import static com.panavis.primo.Constants.Keywords.INTEKO;

class CasePanelParserHelpers {

    private static Map<Integer, JsonArray> allActualCasePanelJsons = new HashMap<>();
    private static Map<Integer, JsonArray> allExpectedCasePanelJsons = new HashMap<>();

    static void setUpAllActualAndExpectedJsons() {
        setUpActualJsons();
        setUpExpectedJsons();
    }

    private static void setUpActualJsons() {
        for (int i = 0; i < TestsSetup.wordFilePaths.size(); i++) {
            allActualCasePanelJsons.put(i, parseAndReturnCasePanelArray(i));
        }
    }

    private static void setUpExpectedJsons() {
        for (int i = 0; i < TestsSetup.expectedJsonContent.size(); i++) {
            allExpectedCasePanelJsons.put(i, getExpectedCasePanel(i));
        }
    }

    static JsonArray parseAndReturnCasePanelArray(int caseIndex) {
        JsonObject casePanel = getActualCasePanelObject(caseIndex);
        return casePanel.getArrayByKey(INTEKO);
    }

    static JsonArray getActualCasePanelArray(int caseIndex) {
        return allActualCasePanelJsons.get(caseIndex);
    }

    private static JsonObject getActualCasePanelObject(int caseIndex) {
        String wordFilePath = TestsSetup.wordFilePaths.get(caseIndex);
        Primo primo = TestsSetup.getConverterObject(wordFilePath, INTEKO);
        primo.parseCaseSections();
        return primo.getParsedCaseSection(INTEKO).getSectionContent();
    }

    static JsonObject getActualPanelistObject(int caseIndex, int panelistIndex) {
        return allActualCasePanelJsons.get(caseIndex).getJsonByIndex(panelistIndex);
    }


    private static JsonArray getExpectedCasePanel(int caseIndex) {
        JsonObject expectedCase = TestsSetup.expectedJsonContent.get(caseIndex);
        JsonObject expectedCasePanel = expectedCase.getArrayByKey(CASE).getJsonByIndex(6);
        return expectedCasePanel.getArrayByKey(INTEKO);
    }

    static JsonObject getExpectedPanelistObject(int caseIndex, int panelistIndex) {
        return allExpectedCasePanelJsons.get(caseIndex).getJsonByIndex(panelistIndex);
    }
}
