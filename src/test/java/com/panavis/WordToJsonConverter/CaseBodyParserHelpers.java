package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.HashMap;
import java.util.Map;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;

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
        for (int i = 0; i < TestsSetup.wordDocxData.size(); i++) {
            SectionResult caseBodyResult = getCaseBodyResult(i);
            JsonObject caseBody = caseBodyResult.getSectionContent();
            JsonArray caseBodyArray = caseBody.getArrayByKey(CASE_BODY);
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

    private static SectionResult getCaseBodyResult(int caseIndex) {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(caseIndex);
        Converter converter = TestsSetup.getConverterObject(wordDocument, CASE_BODY);
        converter.parseCaseSections();
        return converter.getParsedCaseSection(CASE_BODY);
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
}
