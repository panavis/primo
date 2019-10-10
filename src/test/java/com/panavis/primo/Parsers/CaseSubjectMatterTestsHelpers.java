package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Keywords.*;

import com.panavis.primo.Primo;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.*;

import java.util.*;

class CaseSubjectMatterTestsHelpers {

    private static Map<Integer, JsonArray> allActualSubjectMatterJsons = new HashMap<>();
    private static Map<Integer, JsonArray> allExpectedSubjectMatterJsons = new HashMap<>();

    static void setUpAllActualAndExpectedJsons() {
        setUpActualJsons();
        setUpExpectedJsons();
    }

    private static void setUpExpectedJsons() {
        for (int i = 0; i < TestsSetup.expectedJsonContent.size(); i++) {
            JsonObject caseJson = TestsSetup.expectedJsonContent.get(i);
            JsonArray expectedCase = caseJson.getArrayByKey(CASE);
            JsonObject expectedSection = expectedCase.getJsonByIndex(2);
            JsonArray expectedSubjectMatterArray = expectedSection.getArrayByKey(SUBJECT_MATTER);
            allExpectedSubjectMatterJsons.put(i, expectedSubjectMatterArray);
        }
    }

    private static void setUpActualJsons() {
        for (int i = 0; i < TestsSetup.wordFilePaths.size(); i++) {
            SectionResult subjectMatterResult = parseOneCaseAndReturnSubjectMatterSection(i);
            JsonObject subjectMatterContent = subjectMatterResult.getSectionContent();
            JsonArray subjectMatterArray = subjectMatterContent.getArrayByKey(SUBJECT_MATTER);
            allActualSubjectMatterJsons.put(i, subjectMatterArray);
        }
    }

    static SectionResult parseOneCaseAndReturnSubjectMatterSection(int i) {
        String wordFilePath = TestsSetup.wordFilePaths.get(i);
        Primo primo = TestsSetup.getConverterObject(wordFilePath, SUBJECT_MATTER);
        primo.parseCaseSections();
        return primo.getParsedCaseSection(SUBJECT_MATTER);
    }

    static JsonArray getActualSubjectMatterSectionArray(int wordDocIndex) {
        return allActualSubjectMatterJsons.get(wordDocIndex);
    }

    static JsonObject getActualSubjectMatterSubsection(int wordDocIndex, int subsectionIndex) {
        return getActualSubjectMatterSectionArray(wordDocIndex).getJsonByIndex(subsectionIndex);
    }

    static String getActualSubsectionContent(int docIndex, String heading, int subsectionIndex) {
        JsonObject subsection = getActualSubjectMatterSubsection(docIndex, subsectionIndex);
        return subsection.getArrayByKey(heading).toString();
    }

    static String getExpectedSubsectionContent(int docIndex, String heading, int subsectionIndex) {
        JsonArray expectedSubjectMatterArray = allExpectedSubjectMatterJsons.get(docIndex);
        JsonObject subsection = expectedSubjectMatterArray.getJsonByIndex(subsectionIndex);
        return subsection.getArrayByKey(heading).toString();
    }
}