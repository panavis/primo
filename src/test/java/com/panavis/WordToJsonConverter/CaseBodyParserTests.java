package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CaseBodyParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    private JsonObject getCaseBody(int caseIndex) {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(caseIndex);
        Converter converter = TestsSetup.getConverterObject(wordDocument, Keywords.CASE_BODY);
        converter.parseCaseSections();
        SectionResult caseBodyResult = converter.getParsedCaseSection(Keywords.CASE_BODY);
        return caseBodyResult.getSectionContent();
    }

    private JsonArray getCaseBackgroundSection(int caseIndex, String heading) {
        JsonObject caseBody = getCaseBody(caseIndex);
        return caseBody.getArrayByKey(heading);
    }

    @Test
    public void primary_court_0020_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBody(16);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void primary_court_0020_CaseBackgroundSectionHasAnArrayOfLengthOne() {
        JsonArray caseBackground = getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void primary_court_0020_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.getStringByIndex(0);

        JsonObject caseObject = TestsSetup.expectedJsonContent.get(16);
        JsonArray caseArray = caseObject.getArrayByKey(Keywords.CASE);
        JsonObject expectedBackground = caseArray.getJsonByIndex(3);
        JsonArray expectedArray = expectedBackground.getArrayByKey("I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.getStringByIndex(0);

        assertEquals(expectedContent, actualContent);
    }

}
