package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.ArrayList;
import java.util.List;

class CaseSubjectMatterTestsHelpers {

    private static List<JsonArray> allActualSubjectMatterJsons = new ArrayList<>();
    private static List<JsonArray> allExpectedSubjectMatterJsons = new ArrayList<>();

    static void setUpAllActualSubjectMatterJsons() {
        setUpActualJsons();
        setUpExpectedJsons();
    }

    private static void setUpExpectedJsons() {
        for (JsonObject caseJson : TestsSetup.expectedJsonContent) {
            JsonArray expectedCase = caseJson.getArrayByKey(Keywords.CASE);
            JsonObject expectedSection = expectedCase.getJsonObjectByIndex(2);
            JsonArray expectedSubjectMatterArray = expectedSection.getArrayByKey(Keywords.SUBJECT_MATTER);
            allExpectedSubjectMatterJsons.add(expectedSubjectMatterArray);
        }
    }

    private static void setUpActualJsons() {
        for (XWPFDocument wordDocument : TestsSetup.wordDocxData) {
            Converter converter = TestsSetup.getConverterObject(wordDocument, Keywords.SUBJECT_MATTER);
            converter.parseCaseSections();
            SectionResult subjectMatterResult = converter.getParsedCaseSection(Keywords.SUBJECT_MATTER);
            JsonObject subjectMatterContent = subjectMatterResult.getSectionContent();
            JsonArray subjectMatterArray = subjectMatterContent.getArrayByKey(Keywords.SUBJECT_MATTER);
            allActualSubjectMatterJsons.add(subjectMatterArray);
        }
    }

    static JsonArray getActualSubjectMatterSectionArray(int wordDocIndex) {
        return allActualSubjectMatterJsons.get(wordDocIndex);
    }

    static JsonObject getActualSubjectMatterSubsection(int wordDocIndex, int subsectionIndex) {
        return getActualSubjectMatterSectionArray(wordDocIndex).getJsonObjectByIndex(subsectionIndex);
    }

    static String getActualSubsectionContent(int docIndex, String heading, int subsectionIndex) {
        JsonObject subsection = getActualSubjectMatterSubsection(docIndex, subsectionIndex);
        return subsection.getArrayByKey(heading).toString();
    }

    static String getExpectedSubsectionContent(int docIndex, String heading, int subsectionIndex) {
        JsonArray expectedSubjectMatterArray = allExpectedSubjectMatterJsons.get(docIndex);
        JsonObject subsection = expectedSubjectMatterArray.getJsonObjectByIndex(subsectionIndex);
        return subsection.getArrayByKey(heading).toString();
    }
}