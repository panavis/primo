package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CaseBodyParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    private JsonObject getCaseBackground(int caseIndex) {
        JsonObject caseBackground = getCaseBodySubsection(caseIndex, 0);
        return caseBackground;
    }

    private JsonObject getCaseBodySubsection(int caseIndex, int subsectionIndex) {
        SectionResult caseBodyResult = getCaseBodyResult(caseIndex);
        JsonObject caseBody = caseBodyResult.getSectionContent();
        JsonArray caseBodyArray = caseBody.getArrayByKey(Keywords.CASE_BODY);
        return caseBodyArray.getJsonByIndex(subsectionIndex);
    }

    private SectionResult getCaseBodyResult(int caseIndex) {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(caseIndex);
        Converter converter = TestsSetup.getConverterObject(wordDocument, Keywords.CASE_BODY);
        converter.parseCaseSections();
        return converter.getParsedCaseSection(Keywords.CASE_BODY);
    }

    private JsonArray getCaseBackgroundSection(int caseIndex, String heading) {
        JsonObject caseBody = getCaseBackground(caseIndex);
        return caseBody.getArrayByKey(heading);
    }

    @Test
    public void comm_court_huye_2011_hasCaseBackgroundSection() {
        JsonObject caseBackground = getCaseBackground(0);
        assertTrue(caseBackground.hasKey("I . IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void comm_court_huye_2016_hasCaseBackgroundSection() {
        JsonObject caseBackground = getCaseBackground(1);
        assertTrue(caseBackground.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void comm_court_huye_2018_hasCaseBackgroundSection() {
        JsonObject caseBackground = getCaseBackground(2);
        assertTrue(caseBackground.hasKey("I . IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void comm_court_huye_2018_secondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = getCaseBodySubsection(2, 1);
        assertTrue(secondSubsection.hasKey("II. ISESENGURA RY\u2019INZITIZI YATANZWE"));
    }

    @Test
    public void comm_high_court_nyarugenge_2014_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(4);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void comm_high_court_nyarugenge_2016_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(5);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void comm_high_court_2016_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(6);
        assertTrue(caseBody.hasKey("IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void high_court_chamber_nyanza_2014_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(7);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void high_court_chamber_nyanza_2018_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(8);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void high_court_criminal_2011_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(9);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void interm_court_huye_2015_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(11);
        assertTrue(caseBody.hasKey("I.\t IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void interm_court_huye_2018_226_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(13);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019 URUBANZA"));
    }

    @Test
    public void primary_court_0003_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(15);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void primary_court_0020_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(16);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void supreme_court_comm_2009_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(17);
        assertTrue(caseBody.hasKey("1.\tImiterere y\u2019urubanza"));
    }

    @Test
    public void supreme_court_comm_2017_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(18);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void comm_court_huye_2011_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = getCaseBackgroundSection(0, "I . IMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void comm_court_huye_2016_BackgroundSectionHasLengthTwoArray() {
        JsonArray caseBackground = getCaseBackgroundSection(1, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void comm_court_huye_2018_BackgroundSectionHasLengthThreeArray() {
        JsonArray caseBackground = getCaseBackgroundSection(2, "I . IMITERERE Y\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void comm_court_nyarugenge_2014_BackgroundSectionHasLengthThreeArray() {
        JsonArray caseBackground = getCaseBackgroundSection(4, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void comm_court_nyarugenge_2016_BackgroundSectionHasLengthTwoArray() {
        JsonArray caseBackground = getCaseBackgroundSection(5, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void comm_high_court_2016_BackgroundSectionHasLengthFourArray() {
        JsonArray caseBackground = getCaseBackgroundSection(6, "IMITERERE Y\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void high_court_chamber_nyanza_2014_BackgroundSectionHasLengthFourArray() {
        JsonArray caseBackground = getCaseBackgroundSection(7, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void high_court_chamber_nyanza_2018_BackgroundSectionHasLengthFourArray() {
        JsonArray caseBackground = getCaseBackgroundSection(8, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Ignore("Ignore: repeated numbering")
    @Test
    public void high_court_criminal_2011_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = getCaseBackgroundSection(9, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void interm_court_huye_2015_BackgroundSectionHasLengthTwoArray() {
        JsonArray caseBackground = getCaseBackgroundSection(11, "I.\t IMITERERE Y\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void interm_coworurt_huye_2018_226_BackgroundSectionHasLengthThirteenArray() {
        JsonArray caseBackground = getCaseBackgroundSection(13, "I.\tIMITERERE Y\u2019 URUBANZA");
        assertEquals(13, caseBackground.getSize());
    }

    @Test
    public void primary_court_0003_BackgroundSectionHasLengthThreeArray() {
        JsonArray caseBackground = getCaseBackgroundSection(15, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void primary_court_0020_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void supreme_court_comm_2009_BackgroundSectionHasLengthNineArray() {
        JsonArray caseBackground = getCaseBackgroundSection(17, "1.\tImiterere y\u2019urubanza");
        assertEquals(9, caseBackground.getSize());
    }

    @Test
    public void supreme_court_comm_2017_BackgroundSectionHasLengthNineArray() {
        JsonArray caseBackground = getCaseBackgroundSection(18, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(7, caseBackground.getSize());
    }

    private JsonArray getExpectedCaseBackground(int caseIndex, int sectionIndex, String heading) {
        JsonArray caseBodyArray = getExpectedCaseBodyArray(caseIndex, sectionIndex);
        JsonObject caseBackground = caseBodyArray.getJsonByIndex(0);
        return caseBackground.getArrayByKey(heading);
    }

    private JsonArray getExpectedCaseBodyArray(int caseIndex, int sectionIndex) {
        JsonObject caseObject = TestsSetup.expectedJsonContent.get(caseIndex);
        JsonArray caseArray = caseObject.getArrayByKey(Keywords.CASE);
        JsonObject caseBody = caseArray.getJsonByIndex(sectionIndex);
        return caseBody.getArrayByKey(Keywords.CASE_BODY);
    }

    @Test
    public void comm_court_huye_2011_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(0, "I . IMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(0, 4, "I . IMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void comm_court_huye_2016_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(1, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(1, 4, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void comm_court_huye_2018_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(2, "I . IMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(2, 4, "I . IMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void comm_court_nyarugenge_2014_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(4, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(4, 4, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void comm_court_nyarugenge_2016_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(5, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(5, 4, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void comm_high_court_2016_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(6, "IMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(6, 4, "IMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void high_court_chamber_nyanza_2014_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(7, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(7, 4, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void high_court_chamber_nyanza_2018_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(8, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(8, 4, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void interm_court_huye_2015_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(11, "I.\t IMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(11, 4, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void interm_court_huye_2018_226_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(13, "I.\tIMITERERE Y\u2019 URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseBackground(13, 4, "I.\tIMITERERE Y\u2019 URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void primary_court_0003_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(15, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.getStringByIndex(0);

        JsonArray expectedArray = getExpectedCaseBackground(15, 4, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.getStringByIndex(0);

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void primary_court_0020_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.getStringByIndex(0);

        JsonArray expectedArray = getExpectedCaseBackground(16, 4, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.getStringByIndex(0);

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void supreme_court_comm_2009_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(17, "1.\tImiterere y\u2019urubanza");
        String actualContent = caseBackground.getStringByIndex(0);

        JsonArray expectedArray = getExpectedCaseBackground(17, 4, "1.\tImiterere y\u2019urubanza");
        String expectedContent = expectedArray.getStringByIndex(0);

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void supreme_court_comm_2017_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(18, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.getStringByIndex(0);

        JsonArray expectedArray = getExpectedCaseBackground(18, 4, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.getStringByIndex(0);

        assertEquals(expectedContent, actualContent);
    }
}
