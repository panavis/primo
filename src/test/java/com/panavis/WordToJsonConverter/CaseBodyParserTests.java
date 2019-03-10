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
    public void comm_high_court_nyarugenge_2014_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBody(4);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void high_court_chamber_nyanza_2014_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBody(7);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void interm_court_huye_2016_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBody(12);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void interm_court_huye_2018_226_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBody(13);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019 URUBANZA"));
    }

    @Test
    public void primary_court_0020_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBody(16);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void comm_court_nyarugenge_2014_BackgroundSectionHasLengthThreeArray() {
        JsonArray caseBackground = getCaseBackgroundSection(4, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void high_court_chamber_nyanza_2014_BackgroundSectionHasLengthFourArray() {
        JsonArray caseBackground = getCaseBackgroundSection(7, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void interm_court_huye_2016_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = getCaseBackgroundSection(12, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void interm_court_huye_2018_226_BackgroundSectionHasLengthThirteenArray() {
        JsonArray caseBackground = getCaseBackgroundSection(13, "I.\tIMITERERE Y\u2019 URUBANZA");
        assertEquals(13, caseBackground.getSize());
    }

    @Test
    public void primary_court_0020_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    private JsonArray getExpectedCaseBackground(int caseIndex, int sectionIndex, String heading) {
        JsonObject caseObject = TestsSetup.expectedJsonContent.get(caseIndex);
        JsonArray caseArray = caseObject.getArrayByKey(Keywords.CASE);
        JsonObject expectedBackground = caseArray.getJsonByIndex(sectionIndex);
        return expectedBackground.getArrayByKey(heading);
    }

    @Test
    public void comm_court_nyarugenge_2014_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(4, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(4, 3, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void high_court_chamber_nyanza_2014_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(7, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(7, 3, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void interm_court_huye_2016_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(12, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(12, 4, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void interm_court_huye_2018_226_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(13, "I.\tIMITERERE Y\u2019 URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(13, 3, "I.\tIMITERERE Y\u2019 URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void primary_court_0020_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.getStringByIndex(0);

        JsonArray expectedArray = getExpectedCaseBackground(16, 3, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.getStringByIndex(0);

        assertEquals(expectedContent, actualContent);
    }

}
