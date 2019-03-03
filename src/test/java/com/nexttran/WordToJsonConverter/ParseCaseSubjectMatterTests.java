package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Keywords;
import com.nexttran.WordToJsonConverter.ResultTypes.SectionResult;
import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ParseCaseSubjectMatterTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    private JsonArray getSubjectMatterSectionJsonArray(int wordDocIndex) {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(wordDocIndex);
        WordToJsonConverter converter = TestsSetup.getConverterObject(wordDocument);
        converter.parseCaseSections();
        SectionResult subjectMatterResult = converter.getParsedCaseSection(Keywords.SUBJECT_MATTER);
        JsonObject subjectMatterContent = subjectMatterResult.getSectionContent();
        return subjectMatterContent.getArrayByKey(Keywords.SUBJECT_MATTER);
    }

    @Test
    public void interm_court_huye_2018_226_hasSubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(13);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void interm_court_huye_2018_226_has_Icyaha_Aregwa_subheading() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(13);
        JsonObject subsection = subjectMatterArray.getJsonObjectByIndex(0);
        assertTrue(subsection.hasKey("ICYAHA AREGWA"));
    }

    @Test
    public void interm_court_huye_2018_226_subsectionHasANestedArrayWithLengthOne() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(13);
        JsonObject subsection = subjectMatterArray.getJsonObjectByIndex(0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey("ICYAHA AREGWA");
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void interm_court_huye_2018_226_subjectMatterMatchesExpectedContent() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(13);
        JsonObject subsection = subjectMatterArray.getJsonObjectByIndex(0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey("ICYAHA AREGWA");
        JsonObject expectedCaseJsonObject = TestsSetup.expectedJsonContent.get(13);
        JsonArray expectedCase = expectedCaseJsonObject.getArrayByKey(Keywords.CASE);
        JsonObject expectedSection = expectedCase.getJsonObjectByIndex(2);
        JsonArray expectedSubjectMatterArray = expectedSection.getArrayByKey(Keywords.SUBJECT_MATTER);
        JsonArray expectedSubsectionArray = expectedSubjectMatterArray.getJsonObjectByIndex(0)
                                            .getArrayByKey("ICYAHA AREGWA");
        assertEquals(expectedSubsectionArray.toString(), actualSubsectionArray.toString());
    }
}
