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
        String actualContent = getCaseBackgroundSection(0, "I . IMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(0, 0, "I . IMITERERE Y\u2019URUBANZA").toString();

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
        String expectedSubsection = getExpectedCaseSubsection(0,
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
        String expectedSubsection = getExpectedCaseSubsection(0,
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
        String actualContent = getCaseBackgroundSection(1, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(1, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
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
        String expectedSubsection = getExpectedCaseSubsection(1,
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
        String expectedSubsection = getExpectedCaseSubsection(1,
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
        String actualContent = getCaseBackgroundSection(2, "I . IMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(2, 0, "I . IMITERERE Y\u2019URUBANZA").toString();
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
        String expectedSubsection = getExpectedCaseSubsection(2,
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
        String expectedSubsection = getExpectedCaseSubsection(2,
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
        String actualContent = getCaseBackgroundSection(4, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(4, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
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
        String expectedSubsection = getExpectedCaseSubsection(4,
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
        String expectedSubsection = getExpectedCaseSubsection(4,
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
        String actualContent = getCaseBackgroundSection(5, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(5, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
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
        String expectedSubsection = getExpectedCaseSubsection(5,
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
        String expectedSubsection = getExpectedCaseSubsection(5,
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
        String actualContent = getCaseBackgroundSection(6, "IMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(6, 0, "IMITERERE Y\u2019URUBANZA").toString();
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
        String expectedSubsection = getExpectedCaseSubsection(6,
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
        String expectedSubsection = getExpectedCaseSubsection(6,
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
        String actualContent = getCaseBackgroundSection(7, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(7, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
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
        String expectedSubsection = getExpectedCaseSubsection(7,
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
        String expectedSubsection = getExpectedCaseSubsection(7,
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
        String actualContent = getCaseBackgroundSection(8, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(8, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
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
        String expectedSubsection = getExpectedCaseSubsection(8,
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
        String expectedSubsection = getExpectedCaseSubsection(8,
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
        String actualContent = getCaseBackgroundSection(11, "I.\t IMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(11, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
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
        String expectedSubsection = getExpectedCaseSubsection(11,
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
        String expectedSubsection = getExpectedCaseSubsection(11,
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
        String actualContent = getCaseBackgroundSection(13, "I.\tIMITERERE Y\u2019 URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(13, 0, "I.\tIMITERERE Y\u2019 URUBANZA").toString();
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
        String expectedSubsection = getExpectedCaseSubsection(13,
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
        String expectedSubsection = getExpectedCaseSubsection(13,
                2, "III.\tICYEMEZO CY\u2019 URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_015_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(15);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_015_BackgroundSectionHasLengthThreeArray() {
        JsonArray caseBackground = getCaseBackgroundSection(15, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void case_015_CaseBackgroundMatchesExpectedContent() {
        String actualContent = getCaseBackgroundSection(15, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent  = getExpectedCaseSubsection(15, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_015_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(15, 1);
        assertTrue(caseBody.hasKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_015_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(15, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_015_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(15, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        String expectedSubsection = getExpectedCaseSubsection(15,
                1, "II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_015_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(15, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_015_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(15, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_015_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(15, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = getExpectedCaseSubsection(15,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_016_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(16);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_016_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void case_016_CaseBackgroundMatchesExpectedContent() {
        String actualContent = getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(16, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_016_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(16, 1);
        assertTrue(caseBody.hasKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_016_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(16, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_016_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(16, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        String expectedSubsection = getExpectedCaseSubsection(16,
                1, "II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_016_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(16, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_016_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(16, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_016_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(16, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = getExpectedCaseSubsection(16,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_017_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(17);
        assertTrue(caseBody.hasKey("1.\tImiterere y\u2019urubanza"));
    }

    @Test
    public void case_017_BackgroundSectionHasLengthNineArray() {
        JsonArray caseBackground = getCaseBackgroundSection(17, "1.\tImiterere y\u2019urubanza");
        assertEquals(9, caseBackground.getSize());
    }

    @Test
    public void case_017_CaseBackgroundMatchesExpectedContent() {
        String actualContent = getCaseBackgroundSection(17, "1.\tImiterere y\u2019urubanza").toString();
        String expectedContent = getExpectedCaseSubsection(17, 0, "1.\tImiterere y\u2019urubanza").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_017_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(17, 1);
        assertTrue(caseBody.hasKey("2.\tImigendekere y\u2019urubanza."));
    }

    @Test
    public void case_017_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(17, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("2.\tImigendekere y\u2019urubanza.");
        assertEquals(43, subsectionArray.getSize());
    }

    @Test
    public void case_017_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(17, 1);
        String actualSubsection = secondSubsection.getArrayByKey("2.\tImigendekere y\u2019urubanza.").toString();
        String expectedSubsection = getExpectedCaseSubsection(17,
                1, "2.\tImigendekere y\u2019urubanza.").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_017_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(17, 2);
        assertTrue(thirdSubsection.hasKey("3.\tUko Urukiko rubibona"));
    }

    @Test
    public void case_017_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(17, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("3.\tUko Urukiko rubibona");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_017_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(17, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("3.\tUko Urukiko rubibona").toString();
        String expectedSubsection = getExpectedCaseSubsection(17,
                2, "3.\tUko Urukiko rubibona").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_017_FourthSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(17, 3);
        assertTrue(thirdSubsection.hasKey("4.\tIcyemezo cy\u2019Urukiko"));
    }

    @Test
    public void case_017_FourthSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(17, 3);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("4.\tIcyemezo cy\u2019Urukiko");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_017_FourthSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(17, 3);
        String actualSubsection = thirdSubsection.getArrayByKey("4.\tIcyemezo cy\u2019Urukiko").toString();
        String expectedSubsection = getExpectedCaseSubsection(17,
                3, "4.\tIcyemezo cy\u2019Urukiko").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_018_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(18);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_018_BackgroundSectionHasLengthNineArray() {
        JsonArray caseBackground = getCaseBackgroundSection(18, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(7, caseBackground.getSize());
    }

    @Test
    public void case_018_CaseBackgroundMatchesExpectedContent() {
        String actualContent = getCaseBackgroundSection(18, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(18, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_018_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(18, 1);
        assertTrue(caseBody.hasKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO"));
    }

    @Test
    public void case_018_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(18, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO");
        assertEquals(17, subsectionArray.getSize());
    }

    @Test
    public void case_018_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(18, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO").toString();
        String expectedSubsection = getExpectedCaseSubsection(18,
                1, "II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_018_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(18, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_018_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(18, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(2, subsectionArray.getSize());
    }

    @Test
    public void case_018_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(18, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = getExpectedCaseSubsection(18,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_019_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(19);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA."));
    }

    @Test
    public void case_019_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = getCaseBackgroundSection(19, "I.\tIMITERERE Y\u2019URUBANZA.");
        assertEquals(7, caseBackground.getSize());
    }

    @Test
    public void case_019_CaseBackgroundMatchesExpectedContent() {
        String actualContent = getCaseBackgroundSection(19, "I.\tIMITERERE Y\u2019URUBANZA.").toString();
        String expectedContent = getExpectedCaseSubsection(19, 0, "I.\tIMITERERE Y\u2019URUBANZA.").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_019_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(19, 1);
        assertTrue(caseBody.hasKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO."));
    }

    @Test
    public void case_019_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(19, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.");
        assertEquals(26, subsectionArray.getSize());
    }

    @Test
    public void case_019_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(19, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.").toString();
        String expectedSubsection = getExpectedCaseSubsection(19,
                1, "II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_019_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(19, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_019_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(19, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_019_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(19, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = getExpectedCaseSubsection(19,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_020_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(20);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_020_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = getCaseBackgroundSection(20, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(5, caseBackground.getSize());
    }

    @Test
    public void case_020_CaseBackgroundMatchesExpectedContent() {
        String actualContent = getCaseBackgroundSection(20, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = getExpectedCaseSubsection(20, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_020_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(20, 1);
        assertTrue(caseBody.hasKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO"));
    }

    @Test
    public void case_020_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(20, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO");
        assertEquals(27, subsectionArray.getSize());
    }

    @Test
    public void case_020_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(20, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO").toString();
        String expectedSubsection = getExpectedCaseSubsection(20,
                1, "II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_020_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(20, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_020_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(20, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_020_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(20, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = getExpectedCaseSubsection(20,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_021_hasCaseBackgroundSection() {
        JsonObject caseBody = getCaseBackground(21);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA."));
    }

    @Test
    public void case_021_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = getCaseBackgroundSection(21, "I.\tIMITERERE Y\u2019URUBANZA.");
        assertEquals(16, caseBackground.getSize());
    }

    @Test
    public void case_021_CaseBackgroundMatchesExpectedContent() {
        String actualContent = getCaseBackgroundSection(21, "I.\tIMITERERE Y\u2019URUBANZA.").toString();
        String expectedContent = getExpectedCaseSubsection(21, 0, "I.\tIMITERERE Y\u2019URUBANZA.").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_021_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = getCaseBodySubsection(21, 1);
        assertTrue(caseBody.hasKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO."));
    }

    @Test
    public void case_021_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = getCaseBodySubsection(21, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.");
        assertEquals(39, subsectionArray.getSize());
    }

    @Test
    public void case_021_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = getCaseBodySubsection(21, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.").toString();
        String expectedSubsection = getExpectedCaseSubsection(21,
                1, "II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_021_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = getCaseBodySubsection(21, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO."));
    }

    @Test
    public void case_021_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = getCaseBodySubsection(21, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO.");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_021_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = getCaseBodySubsection(21, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO.").toString();
        String expectedSubsection = getExpectedCaseSubsection(21,
                2, "III.\tICYEMEZO CY\u2019URUKIKO.").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    private JsonArray getExpectedCaseSubsection(int caseIndex,
                                                int subsectionIndex, String heading) {
        JsonArray caseBodyArray = getExpectedCaseBodyArray(caseIndex);
        JsonObject caseBackground = caseBodyArray.getJsonByIndex(subsectionIndex);
        return caseBackground.getArrayByKey(heading);
    }

    private JsonArray getExpectedCaseBodyArray(int caseIndex) {
        JsonObject caseObject = TestsSetup.expectedJsonContent.get(caseIndex);
        JsonArray caseArray = caseObject.getArrayByKey(CASE);
        JsonObject caseBody = caseArray.getJsonByIndex(4);
        return caseBody.getArrayByKey(CASE_BODY);
    }
}