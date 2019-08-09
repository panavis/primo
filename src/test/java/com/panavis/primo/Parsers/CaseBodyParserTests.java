package com.panavis.primo.Parsers;

import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.*;
import org.junit.*;

import static com.panavis.primo.Constants.Headings.URUKIKO;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CaseBodyParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
        CaseBodyParserHelpers.setUpAllActualAndExpectedJsons();
    }

    @Ignore("Ignore: Body section troubleshooter.")
    @Test
    public void troubleshootOneCaseSeparately() {
       SectionResult result = CaseBodyParserHelpers.parseOneCaseAndReturnCaseBodySection(31);
       System.out.println(result.getSectionContent().toString());
    }

    @Test
    public void case_000_hasCaseBackgroundSection() {
        JsonObject caseBackground = CaseBodyParserHelpers.getCaseBackground(0);
        assertTrue(caseBackground.hasKey("I . IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_000_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(0, "I . IMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void case_000_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(0, "I . IMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(0, 0, "I . IMITERERE Y\u2019URUBANZA").toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_000_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBackground = CaseBodyParserHelpers.getCaseBodySubsection(0, 1);
        assertTrue(caseBackground.hasKey("II.ISESENGURA RY\u2019URUBANZA"));
    }

    @Test
    public void case_000_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(0, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.ISESENGURA RY\u2019URUBANZA");
        assertEquals(2, subsectionArray.getSize());
    }

    @Test
    public void case_000_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(0, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.ISESENGURA RY\u2019URUBANZA").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(0,
                1, "II.ISESENGURA RY\u2019URUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_000_ThirdSubsectionHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(0, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_000_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(0, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_000_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(0, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(0,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_001_hasCaseBackgroundSection() {
        JsonObject caseBackground = CaseBodyParserHelpers.getCaseBackground(1);
        assertTrue(caseBackground.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_001_BackgroundSectionHasLengthTwoArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(1, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void case_001_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(1, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(1, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_001_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 1);
        assertTrue(secondSubsection.hasKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_001_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA");
        assertEquals(17, subsectionArray.getSize());
    }

    @Test
    public void case_001_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(1,
                1, "II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_001_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_001_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(2, subsectionArray.getSize());
    }

    @Test
    public void case_001_ThirdSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 2);
        String actualSubsection = secondSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(1,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_002_hasCaseBackgroundSection() {
        JsonObject caseBackground = CaseBodyParserHelpers.getCaseBackground(2);
        assertTrue(caseBackground.hasKey("I . IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_002_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(2, "I . IMITERERE Y\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void case_002_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(2, "I . IMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(2, 0, "I . IMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_002_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 1);
        assertTrue(secondSubsection.hasKey("II. ISESENGURA RY\u2019INZITIZI YATANZWE"));
    }

    @Test
    public void case_002_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II. ISESENGURA RY\u2019INZITIZI YATANZWE");
        assertEquals(2, subsectionArray.getSize());
    }

    @Test
    public void case_002_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II. ISESENGURA RY\u2019INZITIZI YATANZWE").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(2,
                1, "II. ISESENGURA RY\u2019INZITIZI YATANZWE").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_002_FourthSubsectionOfBodyHasExpectedHeading() {
        JsonObject fourthSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 2);
        assertTrue(fourthSubsection.hasKey("III. ICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_002_FourthSubsectionHasExpectedLength() {
        JsonObject fourthSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 2);
        JsonArray subsectionArray = fourthSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_002_FourthSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 2);
        String actualSubsection = secondSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(2,
                2, "III. ICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_004_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(4);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_004_BackgroundSectionHasLengthThreeArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(4, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void case_004_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(4, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(4, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_004_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 1);
        assertTrue(secondSubsection.hasKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZEURUBANZA"));
    }

    @Test
    public void case_004_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZEURUBANZA");
        assertEquals(12, subsectionArray.getSize());
    }

    @Test
    public void case_004_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZEURUBANZA").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(4,
                1, "II.\tISESENGURA RY\u2019IBIBAZO BIGIZEURUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_004_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_004_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_004_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(4,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_005_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(5);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_005_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(5, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void case_005_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(5, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(5, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_005_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 1);
        assertTrue(secondSubsection.hasKey("II.\tISESENGURA RY\u2018INZITIZI"));
    }

    @Test
    public void case_005_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2018INZITIZI");
        assertEquals(5, subsectionArray.getSize());
    }

    @Test
    public void case_005_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2018INZITIZI").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(5,
                1, "II.\tISESENGURA RY\u2018INZITIZI").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_005_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_005_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(5, subsectionArray.getSize());
    }

    @Test
    public void case_005_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(5,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_006_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(6);
        assertTrue(caseBody.hasKey("IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_006_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(6, "IMITERERE Y\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void case_006_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(6, "IMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(6, 0, "IMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_006_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(6, 1);
        assertTrue(caseBody.hasKey("ISESENGURA RY\u2019IBIBAZO BIRI MU RUBANZA"));
    }

    @Test
    public void case_006_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(6, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("ISESENGURA RY\u2019IBIBAZO BIRI MU RUBANZA");
        assertEquals(13, subsectionArray.getSize());
    }

    @Test
    public void case_006_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(6, 1);
        String actualSubsection = secondSubsection.getArrayByKey("ISESENGURA RY\u2019IBIBAZO BIRI MU RUBANZA").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(6,
                1, "ISESENGURA RY\u2019IBIBAZO BIRI MU RUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_006_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(6, 2);
        assertTrue(thirdSubsection.hasKey("ICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_006_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(6, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("ICYEMEZO CY\u2019URUKIKO");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_006_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(6, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("ICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(6,
                2, "ICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_007_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(7);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_007_BackgroundSectionHasLengthFourArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(7, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void case_007_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(7, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(7, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_007_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(7, 1);
        assertTrue(caseBody.hasKey("II.\tISESENGURA RY\u2019IKIBAZO KIRI MU RUBANZA"));
    }

    @Test
    public void case_007_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(7, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IKIBAZO KIRI MU RUBANZA");
        assertEquals(13, subsectionArray.getSize());
    }

    @Test
    public void case_007_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(7, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IKIBAZO KIRI MU RUBANZA").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(7,
                1, "II.\tISESENGURA RY\u2019IKIBAZO KIRI MU RUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_007_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(7, 2);
        assertTrue(thirdSubsection.hasKey("III. ICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_007_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(7, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_007_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(7, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(7,
                2, "III. ICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_008_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(8);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_008_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(8, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void case_008_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(8, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(8, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_008_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(8, 1);
        assertTrue(caseBody.hasKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_008_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(8, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_008_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(8, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(8,
                1, "II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_008_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(8, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_008_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(8, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_008_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(8, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(8,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_009_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(9);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Ignore("Ignore: repeated numbering (body section)")
    @Test
    public void case_009_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(9, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void case_011_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(11);
        assertTrue(caseBody.hasKey("I.\t IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_011_BackgroundSectionHasLengthTwoArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(11, "I.\t IMITERERE Y\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void case_011_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(11, "I.\t IMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(11, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_011_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(11, 1);
        assertTrue(caseBody.hasKey("II.IKIBAZO KIGIZE URUBANZA N\u2019ISESENGURWA RYACYO"));
    }

    @Test
    public void case_011_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(11, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.IKIBAZO KIGIZE URUBANZA N\u2019ISESENGURWA RYACYO");
        assertEquals(18, subsectionArray.getSize());
    }

    @Test
    public void case_011_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(11, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.IKIBAZO KIGIZE URUBANZA N\u2019ISESENGURWA RYACYO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(11,
                1, "II.IKIBAZO KIGIZE URUBANZA N\u2019ISESENGURWA RYACYO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_011_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(11, 2);
        assertTrue(thirdSubsection.hasKey("III. ICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_011_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(11, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO");
        assertEquals(5, subsectionArray.getSize());
    }

    @Test
    public void case_011_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(11, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(11,
                2, "III. ICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_013_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(13);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019 URUBANZA"));
    }

    @Test
    public void case_013_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(13, "I.\tIMITERERE Y\u2019 URUBANZA");
        assertEquals(13, caseBackground.getSize());
    }

    @Test
    public void case_013_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(13, "I.\tIMITERERE Y\u2019 URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(13, 0, "I.\tIMITERERE Y\u2019 URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_013_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(13, 1);
        assertTrue(caseBody.hasKey("II.\tISESENGURA RY\u2019 IKIBAZO KIGIZE URUBANZA"));
    }

    @Test
    public void case_013_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(13, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019 IKIBAZO KIGIZE URUBANZA");
        assertEquals(26, subsectionArray.getSize());
    }

    @Test
    public void case_013_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(13, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019 IKIBAZO KIGIZE URUBANZA").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(13,
                1, "II.\tISESENGURA RY\u2019 IKIBAZO KIGIZE URUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_013_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(13, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019 URUKIKO"));
    }

    @Test
    public void case_013_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(13, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019 URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_013_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(13, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019 URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(13,
                2, "III.\tICYEMEZO CY\u2019 URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_014_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(14);
        assertTrue(caseBody.hasKey("I. IMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_014_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(14, "I. IMITERERE Y\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void case_014_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(14, "I. IMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(14, 0, "I. IMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_014_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(14, 1);
        assertTrue(caseBody.hasKey("II. ISESENGURA RY\u2018IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_014_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(14, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II. ISESENGURA RY\u2018IBIBAZO BIGIZE URUBANZA");
        assertEquals(13, subsectionArray.getSize());
    }

    @Test
    public void case_014_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(14, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II. ISESENGURA RY\u2018IBIBAZO BIGIZE URUBANZA").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(14,
                1, "II. ISESENGURA RY\u2018IBIBAZO BIGIZE URUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_014_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(14, 2);
        assertTrue(thirdSubsection.hasKey("III. ICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_014_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(14, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_014_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(14, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(14,
                2, "III. ICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_015_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(15);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_015_BackgroundSectionHasLengthThreeArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(15, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void case_015_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(15, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent  = CaseBodyParserHelpers.getExpectedCaseSubsection(15, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_015_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(15, 1);
        assertTrue(caseBody.hasKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_015_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(15, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_015_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(15, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(15,
                1, "II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_015_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(15, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_015_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(15, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_015_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(15, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(15,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_016_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(16);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_016_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void case_016_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(16, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(16, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_016_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(16, 1);
        assertTrue(caseBody.hasKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_016_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(16, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_016_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(16, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(16,
                1, "II.\tISESENGURA RY\u2019IBIBAZO BIGIZE URUBANZA").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_016_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(16, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_016_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(16, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_016_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(16, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(16,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_017_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(17);
        assertTrue(caseBody.hasKey("1.\tImiterere y\u2019urubanza"));
    }

    @Test
    public void case_017_BackgroundSectionHasLengthNineArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(17, "1.\tImiterere y\u2019urubanza");
        assertEquals(9, caseBackground.getSize());
    }

    @Test
    public void case_017_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(17, "1.\tImiterere y\u2019urubanza").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(17, 0, "1.\tImiterere y\u2019urubanza").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_017_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(17, 1);
        assertTrue(caseBody.hasKey("2.\tImigendekere y\u2019urubanza."));
    }

    @Test
    public void case_017_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("2.\tImigendekere y\u2019urubanza.");
        assertEquals(43, subsectionArray.getSize());
    }

    @Test
    public void case_017_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 1);
        String actualSubsection = secondSubsection.getArrayByKey("2.\tImigendekere y\u2019urubanza.").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(17,
                1, "2.\tImigendekere y\u2019urubanza.").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_017_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 2);
        assertTrue(thirdSubsection.hasKey("3.\tUko Urukiko rubibona"));
    }

    @Test
    public void case_017_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("3.\tUko Urukiko rubibona");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_017_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("3.\tUko Urukiko rubibona").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(17,
                2, "3.\tUko Urukiko rubibona").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_017_FourthSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 3);
        assertTrue(thirdSubsection.hasKey("4.\tIcyemezo cy\u2019Urukiko"));
    }

    @Test
    public void case_017_FourthSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 3);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("4.\tIcyemezo cy\u2019Urukiko");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_017_FourthSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 3);
        String actualSubsection = thirdSubsection.getArrayByKey("4.\tIcyemezo cy\u2019Urukiko").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(17,
                3, "4.\tIcyemezo cy\u2019Urukiko").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_018_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(18);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_018_BackgroundSectionHasLengthNineArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(18, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(7, caseBackground.getSize());
    }

    @Test
    public void case_018_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(18, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(18, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_018_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(18, 1);
        assertTrue(caseBody.hasKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO"));
    }

    @Test
    public void case_018_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(18, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO");
        assertEquals(17, subsectionArray.getSize());
    }

    @Test
    public void case_018_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(18, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(18,
                1, "II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_018_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(18, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_018_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(18, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(2, subsectionArray.getSize());
    }

    @Test
    public void case_018_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(18, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(18,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_019_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(19);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA."));
    }

    @Test
    public void case_019_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(19, "I.\tIMITERERE Y\u2019URUBANZA.");
        assertEquals(7, caseBackground.getSize());
    }

    @Test
    public void case_019_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(19, "I.\tIMITERERE Y\u2019URUBANZA.").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(19, 0, "I.\tIMITERERE Y\u2019URUBANZA.").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_019_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(19, 1);
        assertTrue(caseBody.hasKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO."));
    }

    @Test
    public void case_019_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(19, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.");
        assertEquals(26, subsectionArray.getSize());
    }

    @Test
    public void case_019_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(19, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(19,
                1, "II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_019_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(19, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_019_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(19, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_019_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(19, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(19,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_020_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(20);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA"));
    }

    @Test
    public void case_020_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(20, "I.\tIMITERERE Y\u2019URUBANZA");
        assertEquals(5, caseBackground.getSize());
    }

    @Test
    public void case_020_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(20, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(20, 0, "I.\tIMITERERE Y\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_020_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(20, 1);
        assertTrue(caseBody.hasKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO"));
    }

    @Test
    public void case_020_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(20, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO");
        assertEquals(27, subsectionArray.getSize());
    }

    @Test
    public void case_020_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(20, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(20,
                1, "II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURWA RYABYO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_020_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(20, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO"));
    }

    @Test
    public void case_020_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(20, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_020_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(20, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(20,
                2, "III.\tICYEMEZO CY\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_021_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(21);
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y\u2019URUBANZA."));
    }

    @Test
    public void case_021_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(21, "I.\tIMITERERE Y\u2019URUBANZA.");
        assertEquals(16, caseBackground.getSize());
    }

    @Test
    public void case_021_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(21, "I.\tIMITERERE Y\u2019URUBANZA.").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(21, 0, "I.\tIMITERERE Y\u2019URUBANZA.").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_021_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(21, 1);
        assertTrue(caseBody.hasKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO."));
    }

    @Test
    public void case_021_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(21, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.");
        assertEquals(39, subsectionArray.getSize());
    }

    @Test
    public void case_021_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(21, 1);
        String actualSubsection = secondSubsection.getArrayByKey("II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(21,
                1, "II.\tIBIBAZO BIGIZE URUBANZA N\u2019ISESENGURA RYABYO.").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_021_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(21, 2);
        assertTrue(thirdSubsection.hasKey("III.\tICYEMEZO CY\u2019URUKIKO."));
    }

    @Test
    public void case_021_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(21, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO.");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_021_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(21, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\tICYEMEZO CY\u2019URUKIKO.").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(21,
                2, "III.\tICYEMEZO CY\u2019URUKIKO.").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_030_CaseBodyHasExpectedLength() {
        JsonArray caseBody = CaseBodyParserHelpers.getActualCaseBody(30);
        assertEquals(2, caseBody.getSize());
    }

    @Test
    public void case_030_hasOldCaseBody() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(30);
        assertTrue(caseBody.hasKey(URUKIKO));
    }

    @Test
    public void case_030_firstCaseBodySubsectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(30, URUKIKO);
        assertEquals(24, caseBackground.getSize());
    }

    @Test
    public void case_030_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(30, 1);
        assertTrue(caseBody.hasKey("Kubera iyo mpamvu"));
    }

    @Test
    public void case_030_secondCaseBodySubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(30, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("Kubera iyo mpamvu");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_031_CaseBodyHasExpectedLength() {
        JsonArray caseBody = CaseBodyParserHelpers.getActualCaseBody(31);
        assertEquals(3, caseBody.getSize());
    }

    @Test
    public void case_031_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(31);
        assertTrue(caseBody.hasKey("I.IMITERERE Y\u2019URUBANZA."));
    }

    @Test
    public void case_031_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(31, 1);
        assertTrue(caseBody.hasKey("II.ISESENGURA RY\u2019IKIBAZO KIGIZE URUBANZA."));
    }

    @Test
    public void case_031_ThirdSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(31, 2);
        assertTrue(caseBody.hasKey("III .ICYEMEZO CY\u2019URUKIKO."));
    }
}