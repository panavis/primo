package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.*;

import static com.panavis.WordToJsonConverter.Constants.Keywords.CASE;
import static com.panavis.WordToJsonConverter.Constants.Keywords.CASE_BODY;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CaseBodyParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    private JsonObject getCaseBackground(int caseIndex) {
        return getCaseBodySubsection(caseIndex, 0);
    }

    private JsonObject getCaseBodySubsection(int caseIndex, int subsectionIndex) {
        SectionResult caseBodyResult = getCaseBodyResult(caseIndex);
        JsonObject caseBody = caseBodyResult.getSectionContent();
        JsonArray caseBodyArray = caseBody.getArrayByKey(CASE_BODY);
        return caseBodyArray.getJsonByIndex(subsectionIndex);
    }

    private SectionResult getCaseBodyResult(int caseIndex) {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(caseIndex);
        Converter converter = TestsSetup.getConverterObject(wordDocument, CASE_BODY);
        converter.parseCaseSections();
        return converter.getParsedCaseSection(CASE_BODY);
    }

    private JsonArray getCaseBackgroundSection(int caseIndex, String heading) {
        JsonObject caseBody = getCaseBackground(caseIndex);
        return caseBody.getArrayByKey(heading);
    }

    @Ignore("Ignore: Body section troubleshooter.")
    @Test
    public void troubleshootOneCaseSeparately() {
       JsonObject result = getCaseBodySubsection(0, 0);
    }

    @Test
    public void case_000_hasCaseBackgroundSection() {
        JsonObject caseBackground = getCaseBackground(0);
        assertTrue(caseBackground.hasKey("I . IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_000_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = getCaseBackgroundSection(0, "I . IMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void case_000_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(0, "I . IMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseSubsection(0, 4, 0, "I . IMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_000_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBackground = getCaseBodySubsection(0, 1);
        assertTrue(caseBackground.hasKey("II.ISESENGURA RY\u2019URUBANZA"));
    }

    @Test
    public void case_000_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(0, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.ISESENGURA RY\u2019URUBANZA");
        assertEquals(2, subsectionArray.getSize());
    }

    @Test
    public void case_000_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(0, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.ISESENGURA RY\u2019URUBANZA").toString();

        String expectedSubsection = getExpectedCaseSubsection(0, 4,
                1, "II.ISESENGURA RY\u2019URUBANZA").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_000_ThirdSubsectionHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(0, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_000_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(0, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_000_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(0, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();

        String expectedSubsection = getExpectedCaseSubsection(0, 4,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_001_hasCaseBackgroundSection() {
        JsonObject caseBackground = getCaseBackground(1);
        assertTrue(caseBackground.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_001_BackgroundSectionHasLengthTwoArray() {
        JsonArray caseBackground = getCaseBackgroundSection(1, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void case_001_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(1, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseSubsection(1, 4, 0, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_001_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = getCaseBodySubsection(1, 1);
        assertTrue(secondSubsection.hasKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_001_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(1, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA");

        assertEquals(17, subsectionArray.getSize());
    }

    @Test
    public void case_001_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(1, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();

        String expectedSubsection = getExpectedCaseSubsection(1, 4,
                1, "II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_001_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(1, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_001_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(1, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");

        assertEquals(2, subsectionArray.getSize());
    }

    @Test
    public void case_001_ThirdSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(1, 2);
        String actualSubsection = secondSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();

        String expectedSubsection = getExpectedCaseSubsection(1, 4,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_002_hasCaseBackgroundSection() {
        JsonObject caseBackground = getCaseBackground(2);
        assertTrue(caseBackground.hasKey("I . IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_002_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = getCaseBackgroundSection(2, "I . IMITERERE Y\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void case_002_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(2, "I . IMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseSubsection(2, 4, 0, "I . IMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_002_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = getCaseBodySubsection(2, 1);
        assertTrue(secondSubsection.hasKey("II. ISESENGURA RY\u2019INZITIZI YATANZWE"));
    }

    @Test
    public void case_002_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(2, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II. ISESENGURA RY\u2019INZITIZI YATANZWE");
        assertEquals(2, subsectionArray.getSize());
    }

    @Test
    public void case_002_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(2, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II. ISESENGURA RY\u2019INZITIZI YATANZWE").toString();

        String expectedSubsection = getExpectedCaseSubsection(2, 4,
                1, "II. ISESENGURA RY\u2019INZITIZI YATANZWE").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_002_FourthSubsectionOfBodyHasExpectedHeading() {
        JsonObject fourthSubsection = getCaseBodySubsection(2, 2);
        assertTrue(fourthSubsection.hasKey("III. ICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_002_FourthSubsectionHasExpectedLength() {
        JsonObject fourthSubsection = getCaseBodySubsection(2, 2);
        JsonArray subsectionArray = fourthSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_002_FourthSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(2, 2);
        String actualSubsection = secondSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO").toString();

        String expectedSubsection = getExpectedCaseSubsection(2, 4,
                2, "III. ICYEMEZO CY\u2019URUKIKO").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_004_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(4);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_004_BackgroundSectionHasLengthThreeArray() {
        JsonArray caseBackground = getCaseBackgroundSection(4, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void case_004_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(4, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseSubsection(4, 4, 0, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_004_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = getCaseBodySubsection(4, 1);
        assertTrue(secondSubsection.hasKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZEURUBANZA"));
    }

    @Test
    public void case_004_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(4, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZEURUBANZA");
        assertEquals(12, subsectionArray.getSize());
    }

    @Test
    public void case_004_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(4, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZEURUBANZA").toString();

        String expectedSubsection = getExpectedCaseSubsection(4, 4,
                1, "II.\tISESENGURA RY\u2019IBIBAZO BIGIZEURUBANZA").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_004_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(4, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_004_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(4, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_004_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(4, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();

        String expectedSubsection = getExpectedCaseSubsection(4, 4,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_005_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(5);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_005_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = getCaseBackgroundSection(5, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void case_005_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(5, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseSubsection(5, 4, 0, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_005_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = getCaseBodySubsection(5, 1);
        assertTrue(secondSubsection.hasKey("II.\tISESENGURA RY\u2018INZITIZI"));
    }

    @Test
    public void case_005_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(5, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2018INZITIZI");
        assertEquals(5, subsectionArray.getSize());
    }

    @Test
    public void case_005_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(5, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2018INZITIZI").toString();

        String expectedSubsection = getExpectedCaseSubsection(5, 4,
                1, "II.\tISESENGURA RY\u2018INZITIZI").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_005_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(5, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_005_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(5, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(5, subsectionArray.getSize());
    }

    @Test
    public void case_005_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(5, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();

        String expectedSubsection = getExpectedCaseSubsection(5, 4,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_006_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(6);
        assertTrue(caseBody.hasKey("IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_006_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = getCaseBackgroundSection(6, "IMITERERE Y\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void case_006_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(6, "IMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseSubsection(6, 4, 0, "IMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_006_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(6, 1);
        assertTrue(caseBody.hasKey("ISESENGURA RY\u2019IBIBAZO BIRI MU RUBANZA"));
    }

    @Test
    public void case_006_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(6, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("ISESENGURA RY\u2019IBIBAZO BIRI MU RUBANZA");
        assertEquals(13, subsectionArray.getSize());
    }

    @Test
    public void case_006_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(6, 1);
        String actualSubsection = secondSubsection.getArrayByKey("ISESENGURA RY\u2019IBIBAZO BIRI MU RUBANZA").toString();

        String expectedSubsection = getExpectedCaseSubsection(6, 4,
                1, "ISESENGURA RY\u2019IBIBAZO BIRI MU RUBANZA").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_006_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(6, 2);
        assertTrue(thirdSubsection.hasKey("ICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_006_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(6, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("ICYEMEZO CY\u2019URUKIKO");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_006_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(6, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("ICYEMEZO CY\u2019URUKIKO").toString();

        String expectedSubsection = getExpectedCaseSubsection(6, 4,
                2, "ICYEMEZO CY\u2019URUKIKO").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_007_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(7);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_007_BackgroundSectionHasLengthFourArray() {
        JsonArray caseBackground = getCaseBackgroundSection(7, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void case_007_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(7, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseSubsection(7, 4, 0, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_007_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(7, 1);
        assertTrue(caseBody.hasKey("II.\tISESENGURA RY\u2019IKIBAZO KIRI MU RUBANZA"));
    }

    @Test
    public void case_007_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(7, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IKIBAZO KIRI MU RUBANZA");
        assertEquals(13, subsectionArray.getSize());
    }

    @Test
    public void case_007_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(7, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IKIBAZO KIRI MU RUBANZA").toString();

        String expectedSubsection = getExpectedCaseSubsection(7, 4,
                1, "II.\tISESENGURA RY\u2019IKIBAZO KIRI MU RUBANZA").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_007_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(7, 2);
        assertTrue(thirdSubsection.hasKey("III. ICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_007_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(7, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_007_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(7, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO").toString();

        String expectedSubsection = getExpectedCaseSubsection(7, 4,
                2, "III. ICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_008_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(8);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_008_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = getCaseBackgroundSection(8, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void case_008_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(8, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseSubsection(8, 4, 0, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_008_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(8, 1);
        assertTrue(caseBody.hasKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_008_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(8, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_008_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(8, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();

        String expectedSubsection = getExpectedCaseSubsection(8, 4,
                1, "II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_008_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(8, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_008_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(8, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_008_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(8, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();

        String expectedSubsection = getExpectedCaseSubsection(8, 4,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_009_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(9);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Ignore("Ignore: repeated numbering")
    @Test
    public void case_009_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = getCaseBackgroundSection(9, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void case_011_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(11);
        assertTrue(caseBody.hasKey("I.\t IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_011_BackgroundSectionHasLengthTwoArray() {
        JsonArray caseBackground = getCaseBackgroundSection(11, "I.\t IMITERERE Y\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void case_011_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(11, "I.\t IMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseSubsection(11, 4, 0, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_011_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(11, 1);
        assertTrue(caseBody.hasKey("II.IKIBAZO KIGIZE URUBANZA N\u2019ISESENGURWA RYACYO"));
    }

    @Test
    public void case_011_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(11, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.IKIBAZO KIGIZE URUBANZA N\u2019ISESENGURWA RYACYO");
        assertEquals(18, subsectionArray.getSize());
    }

    @Test
    public void case_011_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(11, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.IKIBAZO KIGIZE URUBANZA N\u2019ISESENGURWA RYACYO").toString();

        String expectedSubsection = getExpectedCaseSubsection(11, 4,
                1, "II.IKIBAZO KIGIZE URUBANZA N\u2019ISESENGURWA RYACYO").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_011_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(11, 2);
        assertTrue(thirdSubsection.hasKey("III. ICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_011_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(11, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO");
        assertEquals(5, subsectionArray.getSize());
    }

    @Test
    public void case_011_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(11, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO").toString();

        String expectedSubsection = getExpectedCaseSubsection(11, 4,
                2, "III. ICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_013_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(13);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019 URUBANZA"));
    }

    @Test
    public void case_013_BackgroundSectionHasLengthThirteenArray() {
        JsonArray caseBackground = getCaseBackgroundSection(13, "I.\tIMITERERE Y\u2019 URUBANZA");
        assertEquals(13, caseBackground.getSize());
    }

    @Test
    public void case_013_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(13, "I.\tIMITERERE Y\u2019 URUBANZA");
        String actualContent = caseBackground.toString();

        JsonArray expectedArray = getExpectedCaseSubsection(13, 4, 0, "I.\tIMITERERE Y\u2019 URUBANZA");
        String expectedContent = expectedArray.toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_013_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(13, 1);
        assertTrue(caseBody.hasKey("II.\tISESENGURA RY\u2019 IKIBAZO KIGIZE URUBANZA"));
    }

    @Test
    public void case_013_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(13, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019 IKIBAZO KIGIZE URUBANZA");
        assertEquals(26, subsectionArray.getSize());
    }

    @Test
    public void case_013_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(13, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019 IKIBAZO KIGIZE URUBANZA").toString();

        String expectedSubsection = getExpectedCaseSubsection(13, 4,
                1, "II.\tISESENGURA RY\u2019 IKIBAZO KIGIZE URUBANZA").toString();

        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_013_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(13, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019 URUKIKO"));
    }

    @Test
    public void case_013_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(13, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019 URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_013_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(13, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019 URUKIKO").toString();

        String expectedSubsection = getExpectedCaseSubsection(13, 4,
                2, "III.\tICYEMEZO CY\u2019 URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_015_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(15);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_016_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(16);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_017_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(17);
        assertTrue(caseBody.hasKey("1.\tImiterere y\u2019urubanza"));
    }

    @Test
    public void case_018_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(18);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_015_BackgroundSectionHasLengthThreeArray() {
        JsonArray caseBackground = getCaseBackgroundSection(15, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void case_016_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void case_017_BackgroundSectionHasLengthNineArray() {
        JsonArray caseBackground = getCaseBackgroundSection(17, "1.\tImiterere y\u2019urubanza");
        assertEquals(9, caseBackground.getSize());
    }

    @Test
    public void case_018_BackgroundSectionHasLengthNineArray() {
        JsonArray caseBackground = getCaseBackgroundSection(18, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(7, caseBackground.getSize());
    }

    private JsonArray getExpectedCaseSubsection(int caseIndex, int sectionIndex,
                                                int subsectionIndex, String heading) {
        JsonArray caseBodyArray = getExpectedCaseBodyArray(caseIndex, sectionIndex);
        JsonObject caseBackground = caseBodyArray.getJsonByIndex(subsectionIndex);
        return caseBackground.getArrayByKey(heading);
    }

    private JsonArray getExpectedCaseBodyArray(int caseIndex, int sectionIndex) {
        JsonObject caseObject = TestsSetup.expectedJsonContent.get(caseIndex);
        JsonArray caseArray = caseObject.getArrayByKey(CASE);
        JsonObject caseBody = caseArray.getJsonByIndex(sectionIndex);
        return caseBody.getArrayByKey(CASE_BODY);
    }

    @Test
    public void case_015_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(15, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.getStringByIndex(0);

        JsonArray expectedArray = getExpectedCaseSubsection(15, 4, 0, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.getStringByIndex(0);

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_016_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.getStringByIndex(0);

        JsonArray expectedArray = getExpectedCaseSubsection(16, 4, 0, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.getStringByIndex(0);

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_017_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(17, "1.\tImiterere y\u2019urubanza");
        String actualContent = caseBackground.getStringByIndex(0);

        JsonArray expectedArray = getExpectedCaseSubsection(17, 4, 0, "1.\tImiterere y\u2019urubanza");
        String expectedContent = expectedArray.getStringByIndex(0);

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_018_CaseBackgroundMatchesExpectedContent() {
        JsonArray caseBackground = getCaseBackgroundSection(18, "I.\tIMITERERE Y\u2019URUBANZA");
        String actualContent = caseBackground.getStringByIndex(0);

        JsonArray expectedArray = getExpectedCaseSubsection(18, 4, 0, "I.\tIMITERERE Y\u2019URUBANZA");
        String expectedContent = expectedArray.getStringByIndex(0);

        assertEquals(expectedContent, actualContent);
    }
}
