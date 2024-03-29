package com.panavis.primo.Parsers;

import com.panavis.primo.Primo;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

import java.util.HashMap;
import java.util.Map;

import static com.panavis.primo.Constants.Keywords.*;

class CaseBodyParserHelpers {

    private static Map<Integer, JsonArray> allActualCaseBodyJsons = new HashMap<>();
    private static Map<Integer, JsonArray> allExpectedCaseBodyJsons = new HashMap<>();

    static void setUpAllActualAndExpectedJsons() {
        setUpActualJsons();
        setUpExpectedJsons();
    }

    private static void setUpExpectedJsons() {
        for (int i = 0; i < TestsSetup.expectedJsonContent.size(); i++) {
            allExpectedCaseBodyJsons.put(i, getExpectedCaseBodyArray(i));
        }
    }

    private static JsonArray getExpectedCaseBodyArray(int caseIndex) {
        JsonObject caseObject = TestsSetup.expectedJsonContent.get(caseIndex);
        JsonArray caseArray = caseObject.getArrayByKey(CASE);
        JsonObject caseBody = caseArray.getJsonByIndex(4);
        return caseBody.getArrayByKey(CASE_BODY);
    }

    private static void setUpActualJsons() {
        for (int i = 0; i < TestsSetup.wordFilePaths.size(); i++) {
            JsonArray caseBodyArray = getActualCaseBody(i);
            allActualCaseBodyJsons.put(i, caseBodyArray);
        }
    }

    static JsonObject getCaseBackground(int caseIndex) {
        return getCaseBodySubsection(caseIndex, 0);
    }

    static JsonObject getCaseBodySubsection(int caseIndex, int subsectionIndex) {
        JsonArray caseBodyArray = allActualCaseBodyJsons.get(caseIndex);
        return caseBodyArray.getJsonByIndex(subsectionIndex);
    }

    static SectionResult parseOneCaseAndReturnCaseBodySection(int caseIndex) {
        String wordFilePath = TestsSetup.wordFilePaths.get(caseIndex);
        Primo primo = TestsSetup.getConverterObject(wordFilePath, CASE_BODY);
        primo.parseCaseSections();
        return primo.getParsedCaseSection(CASE_BODY);
    }

    static JsonArray getCaseBackgroundSection(int caseIndex, String heading) {
        JsonObject caseBody = getCaseBackground(caseIndex);
        return caseBody.getArrayByKey(heading);
    }

    static JsonArray getExpectedCaseSubsection(int caseIndex,
                                               int subsectionIndex, String heading) {
        JsonArray caseBodyArray = allExpectedCaseBodyJsons.get(caseIndex);
        JsonObject caseBackground = caseBodyArray.getJsonByIndex(subsectionIndex);
        return caseBackground.getArrayByKey(heading);
    }

    static JsonArray getActualCaseBody(int caseIndex) {
        SectionResult caseBodyResult = parseOneCaseAndReturnCaseBodySection(caseIndex);
        JsonObject caseBody = caseBodyResult.getSectionContent();
        return caseBody.getArrayByKey(CASE_BODY);
    }
}
