package com.panavis.primo.Parsers;

import com.panavis.primo.Constants.Keywords;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

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
       SectionResult result = CaseBodyParserHelpers.parseOneCaseAndReturnCaseBodySection(39);
        JsonObject sectionContent = result.getSectionContent();
        System.out.println(sectionContent.toString());
    }

    @Test
    public void case_000_hasCaseBackgroundSection() {
        JsonObject caseBackground = CaseBodyParserHelpers.getCaseBackground(0);
        assertTrue(caseBackground.hasKey("I . IMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_000_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(0, "I . IMITERERE Y\\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void case_000_CaseBackgroundMatchesExpectedContent() {
        String heading = "I . IMITERERE Y\\u2019URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(0, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(0, 0, heading).toString();

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_000_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBackground = CaseBodyParserHelpers.getCaseBodySubsection(0, 1);
        assertTrue(caseBackground.hasKey("II.ISESENGURA RY\\u2019URUBANZA"));
    }

    @Test
    public void case_000_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(0, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.ISESENGURA RY\\u2019URUBANZA");
        assertEquals(2, subsectionArray.getSize());
    }

    @Test
    public void case_000_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(0, 1);
        String heading = "II.ISESENGURA RY\\u2019URUBANZA";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(0,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_000_ThirdSubsectionHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(0, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_000_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(0, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_000_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(0, 2);
        String heading = "III.\\tICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(0,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_001_hasCaseBackgroundSection() {
        JsonObject caseBackground = CaseBodyParserHelpers.getCaseBackground(1);
        assertTrue(caseBackground.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_001_BackgroundSectionHasLengthTwoArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(1, "I.\\tIMITERERE Y\\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void case_001_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(1, "I.\\tIMITERERE Y\\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(1, 0, "I.\\tIMITERERE Y\\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_001_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 1);
        assertTrue(secondSubsection.hasKey("II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_001_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA");
        assertEquals(17, subsectionArray.getSize());
    }

    @Test
    public void case_001_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 1);
        String heading = "II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(1,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_001_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_001_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO");
        assertEquals(2, subsectionArray.getSize());
    }

    @Test
    public void case_001_ThirdSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(1, 2);
        String heading = "III.\\tICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(1,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_002_hasCaseBackgroundSection() {
        JsonObject caseBackground = CaseBodyParserHelpers.getCaseBackground(2);
        assertTrue(caseBackground.hasKey("I . IMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_002_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(2, "I . IMITERERE Y\\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void case_002_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(2, "I . IMITERERE Y\\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(2, 0, "I . IMITERERE Y\\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_002_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 1);
        assertTrue(secondSubsection.hasKey("II. ISESENGURA RY\\u2019INZITIZI YATANZWE"));
    }

    @Test
    public void case_002_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II. ISESENGURA RY\\u2019INZITIZI YATANZWE");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_002_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 1);
        String heading = "II. ISESENGURA RY\\u2019INZITIZI YATANZWE";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(2,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_002_FourthSubsectionOfBodyHasExpectedHeading() {
        JsonObject fourthSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 2);
        assertTrue(fourthSubsection.hasKey("III. ICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_002_FourthSubsectionHasExpectedLength() {
        JsonObject fourthSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 2);
        JsonArray subsectionArray = fourthSubsection.getArrayByKey("III. ICYEMEZO CY\\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_002_FourthSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(2, 2);
        String heading = "III. ICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(2,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_004_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(4);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_004_BackgroundSectionHasLengthThreeArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(4, "I.\\tIMITERERE Y\\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void case_004_CaseBackgroundMatchesExpectedContent() {
        String heading = "I.\\tIMITERERE Y\\u2019URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(4, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(4, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_004_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 1);
        assertTrue(secondSubsection.hasKey("II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZEURUBANZA"));
    }

    @Test
    public void case_004_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZEURUBANZA");
        assertEquals(12, subsectionArray.getSize());
    }

    @Test
    public void case_004_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 1);
        String heading = "II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZEURUBANZA";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(4,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_004_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_004_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_004_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(4, 2);
        String heading = "III.\\tICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(4,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_005_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(5);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_005_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(5, "I.\\tIMITERERE Y\\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void case_005_CaseBackgroundMatchesExpectedContent() {
        String heading = "I.\\tIMITERERE Y\\u2019URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(5, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(5, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_005_SecondSubsectionOfBodyHasExpectedHeading() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 1);
        assertTrue(secondSubsection.hasKey("II.\\tISESENGURA RY\\u2018INZITIZI"));
    }

    @Test
    public void case_005_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tISESENGURA RY\\u2018INZITIZI");
        assertEquals(6, subsectionArray.getSize());
    }

    @Test
    public void case_005_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 1);
        String heading = "II.\\tISESENGURA RY\\u2018INZITIZI";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(5,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_005_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_005_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO");
        assertEquals(5, subsectionArray.getSize());
    }

    @Test
    public void case_005_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(5, 2);
        String heading = "III.\\tICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(5,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_006_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(6);
        assertTrue(caseBody.hasKey("IMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_006_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(6, "IMITERERE Y\\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void case_006_CaseBackgroundMatchesExpectedContent() {
        String heading = "IMITERERE Y\\u2019URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(6, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(6, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_006_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(6, 1);
        assertTrue(caseBody.hasKey("ISESENGURA RY\\u2019IBIBAZO BIRI MU RUBANZA"));
    }

    @Test
    public void case_006_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(6, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("ISESENGURA RY\\u2019IBIBAZO BIRI MU RUBANZA");
        assertEquals(13, subsectionArray.getSize());
    }

    @Test
    public void case_006_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(6, 1);
        String heading = "ISESENGURA RY\\u2019IBIBAZO BIRI MU RUBANZA";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(6,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_006_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(6, 2);
        assertTrue(thirdSubsection.hasKey("ICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_006_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(6, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("ICYEMEZO CY\\u2019URUKIKO");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_006_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(6, 2);
        String heading = "ICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(6,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_007_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(7);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_007_BackgroundSectionHasLengthFourArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(7, "I.\\tIMITERERE Y\\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void case_007_CaseBackgroundMatchesExpectedContent() {
        String heading = "I.\\tIMITERERE Y\\u2019URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(7, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(7, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_007_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(7, 1);
        assertTrue(caseBody.hasKey("II.\\tISESENGURA RY\\u2019IKIBAZO KIRI MU RUBANZA"));
    }

    @Test
    public void case_007_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(7, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tISESENGURA RY\\u2019IKIBAZO KIRI MU RUBANZA");
        assertEquals(11, subsectionArray.getSize());
    }

    @Test
    public void case_007_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(7, 1);
        String heading = "II.\\tISESENGURA RY\\u2019IKIBAZO KIRI MU RUBANZA";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(7,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_007_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(7, 2);
        assertTrue(thirdSubsection.hasKey("III. ICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_007_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(7, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\\u2019URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_007_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(7, 2);
        String heading = "III. ICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(7,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_008_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(8);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_008_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(8, "I.\\tIMITERERE Y\\u2019URUBANZA");
        assertEquals(3, caseBackground.getSize());
    }

    @Test
    public void case_008_CaseBackgroundMatchesExpectedContent() {
        String heading = "I.\\tIMITERERE Y\\u2019URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(8, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(8, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_008_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(8, 1);
        assertTrue(caseBody.hasKey("II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_008_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(8, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_008_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(8, 1);
        String heading = "II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(8,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_008_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(8, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_008_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(8, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_008_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(8, 2);
        String heading = "III.\\tICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(8,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_009_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(9);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Ignore("Ignore: repeated numbering (body section)")
    @Test
    public void case_009_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(9, "I.\\tIMITERERE Y\\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void case_011_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(11);
        assertTrue(caseBody.hasKey("I.\\t IMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_011_BackgroundSectionHasLengthTwoArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(11, "I.\\t IMITERERE Y\\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void case_011_CaseBackgroundMatchesExpectedContent() {
        String heading = "I.\\t IMITERERE Y\\u2019URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(11, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(11, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_011_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(11, 1);
        assertTrue(caseBody.hasKey("II.IKIBAZO KIGIZE URUBANZA N\\u2019ISESENGURWA RYACYO"));
    }

    @Test
    public void case_011_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(11, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.IKIBAZO KIGIZE URUBANZA N\\u2019ISESENGURWA RYACYO");
        assertEquals(16, subsectionArray.getSize());
    }

    @Test
    public void case_011_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(11, 1);
        String heading = "II.IKIBAZO KIGIZE URUBANZA N\\u2019ISESENGURWA RYACYO";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(11,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_011_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(11, 2);
        assertTrue(thirdSubsection.hasKey("III. ICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_011_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(11, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\\u2019URUKIKO");
        assertEquals(5, subsectionArray.getSize());
    }

    @Test
    public void case_011_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(11, 2);
        String heading = "III. ICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(11,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_013_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(13);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019 URUBANZA"));
    }

    @Test
    public void case_013_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(13, "I.\\tIMITERERE Y\\u2019 URUBANZA");
        assertEquals(12, caseBackground.getSize());
    }

    @Test
    public void case_013_CaseBackgroundMatchesExpectedContent() {
        String heading = "I.\\tIMITERERE Y\\u2019 URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(13, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(13, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_013_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(13, 1);
        assertTrue(caseBody.hasKey("II.\\tISESENGURA RY\\u2019 IKIBAZO KIGIZE URUBANZA"));
    }

    @Test
    public void case_013_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(13, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tISESENGURA RY\\u2019 IKIBAZO KIGIZE URUBANZA");
        assertEquals(23, subsectionArray.getSize());
    }

    @Test
    public void case_013_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(13, 1);
        String heading = "II.\\tISESENGURA RY\\u2019 IKIBAZO KIGIZE URUBANZA";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(13,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_013_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(13, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019 URUKIKO"));
    }

    @Test
    public void case_013_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(13, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019 URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_013_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(13, 2);
        String heading = "III.\\tICYEMEZO CY\\u2019 URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(13,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_014_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(14);
        assertTrue(caseBody.hasKey("I. IMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_014_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(14, "I. IMITERERE Y\\u2019URUBANZA");
        assertEquals(5, caseBackground.getSize());
    }

    @Test
    public void case_014_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(14, "I. IMITERERE Y\\u2019URUBANZA").toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(14, 0, "I. IMITERERE Y\\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_014_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(14, 1);
        assertTrue(caseBody.hasKey("II. ISESENGURA RY\\u2018IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_014_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(14, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II. ISESENGURA RY\\u2018IBIBAZO BIGIZE URUBANZA");
        assertEquals(12, subsectionArray.getSize());
    }

    @Test
    public void case_014_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(14, 1);
        String heading = "II. ISESENGURA RY\\u2018IBIBAZO BIGIZE URUBANZA";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(14,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_014_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(14, 2);
        assertTrue(thirdSubsection.hasKey("III. ICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_014_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(14, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III. ICYEMEZO CY\\u2019URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_014_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(14, 2);
        String heading = "III. ICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(14,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_015_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(15);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_015_BackgroundSectionHasLengthThreeArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(15, "I.\\tIMITERERE Y\\u2019URUBANZA");
        assertEquals(2, caseBackground.getSize());
    }

    @Test
    public void case_015_CaseBackgroundMatchesExpectedContent() {
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(15, "I.\\tIMITERERE Y\\u2019URUBANZA").toString();
        String expectedContent  = CaseBodyParserHelpers.getExpectedCaseSubsection(15, 0, "I.\\tIMITERERE Y\\u2019URUBANZA").toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_015_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(15, 1);
        assertTrue(caseBody.hasKey("II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_015_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(15, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA");
        assertEquals(6, subsectionArray.getSize());
    }

    @Test
    public void case_015_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(15, 1);
        String heading = "II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(15,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_015_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(15, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_015_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(15, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_015_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(15, 2);
        String heading = "III.\\tICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(15,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_016_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(16);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_016_BackgroundSectionHasLengthOneArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(16, "I.\\tIMITERERE Y\\u2019URUBANZA");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void case_016_CaseBackgroundMatchesExpectedContent() {
        String heading = "I.\\tIMITERERE Y\\u2019URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(16, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(16, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_016_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(16, 1);
        assertTrue(caseBody.hasKey("II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA"));
    }

    @Test
    public void case_016_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(16, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA");
        assertEquals(5, subsectionArray.getSize());
    }

    @Test
    public void case_016_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(16, 1);
        String heading = "II.\\tISESENGURA RY\\u2019IBIBAZO BIGIZE URUBANZA";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(16,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_016_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(16, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_016_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(16, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO");
        assertEquals(1, subsectionArray.getSize());
    }

    @Test
    public void case_016_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(16, 2);
        String heading = "III.\\tICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(16,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_017_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(17);
        assertTrue(caseBody.hasKey("1.\\tImiterere y\\u2019urubanza"));
    }

    @Test
    public void case_017_BackgroundSectionHasLengthNineArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(17, "1.\\tImiterere y\\u2019urubanza");
        assertEquals(9, caseBackground.getSize());
    }

    @Test
    public void case_017_CaseBackgroundMatchesExpectedContent() {
        String heading = "1.\\tImiterere y\\u2019urubanza";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(17, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(17, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_017_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(17, 1);
        assertTrue(caseBody.hasKey("2.\\tImigendekere y\\u2019urubanza."));
    }

    @Test
    public void case_017_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("2.\\tImigendekere y\\u2019urubanza.");
        assertEquals(41, subsectionArray.getSize());
    }

    @Test
    public void case_017_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 1);
        String heading = "2.\\tImigendekere y\\u2019urubanza.";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(17,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_017_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 2);
        assertTrue(thirdSubsection.hasKey("3.\\tUko Urukiko rubibona"));
    }

    @Test
    public void case_017_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("3.\\tUko Urukiko rubibona");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_017_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("3.\\tUko Urukiko rubibona").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(17,
                2, "3.\\tUko Urukiko rubibona").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_017_FourthSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 3);
        assertTrue(thirdSubsection.hasKey("4.\\tIcyemezo cy\\u2019Urukiko"));
    }

    @Test
    public void case_017_FourthSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 3);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("4.\\tIcyemezo cy\\u2019Urukiko");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_017_FourthSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(17, 3);
        String heading = "4.\\tIcyemezo cy\\u2019Urukiko";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(17,
                3, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_018_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(18);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_018_BackgroundSectionHasLengthNineArray() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(18, "I.\\tIMITERERE Y\\u2019URUBANZA");
        assertEquals(7, caseBackground.getSize());
    }

    @Test
    public void case_018_CaseBackgroundMatchesExpectedContent() {
        String heading = "I.\\tIMITERERE Y\\u2019URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(18, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(18, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_018_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(18, 1);
        assertTrue(caseBody.hasKey("II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURWA RYABYO"));
    }

    @Test
    public void case_018_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(18, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURWA RYABYO");
        assertEquals(18, subsectionArray.getSize());
    }

    @Test
    public void case_018_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(18, 1);
        String heading = "II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURWA RYABYO";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(18,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_018_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(18, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_018_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(18, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO");
        assertEquals(2, subsectionArray.getSize());
    }

    @Test
    public void case_018_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(18, 2);
        String heading = "III.\\tICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(18,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_019_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(19);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA."));
    }

    @Test
    public void case_019_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(19, "I.\\tIMITERERE Y\\u2019URUBANZA.");
        assertEquals(7, caseBackground.getSize());
    }

    @Test
    public void case_019_CaseBackgroundMatchesExpectedContent() {
        String heading = "I.\\tIMITERERE Y\\u2019URUBANZA.";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(19, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(19, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_019_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(19, 1);
        assertTrue(caseBody.hasKey("II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURA RYABYO."));
    }

    @Test
    public void case_019_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(19, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURA RYABYO.");
        assertEquals(24, subsectionArray.getSize());
    }

    @Test
    public void case_019_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(19, 1);
        String heading = "II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURA RYABYO.";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(19,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_019_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(19, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_019_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(19, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_019_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(19, 2);
        String actualSubsection = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO").toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(19,
                2, "III.\\tICYEMEZO CY\\u2019URUKIKO").toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_020_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(20);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_020_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(20, "I.\\tIMITERERE Y\\u2019URUBANZA");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void case_020_CaseBackgroundMatchesExpectedContent() {
        String heading = "I.\\tIMITERERE Y\\u2019URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(20, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(20, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_020_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(20, 1);
        assertTrue(caseBody.hasKey("II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURWA RYABYO"));
    }

    @Test
    public void case_020_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(20, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURWA RYABYO");
        assertEquals(26, subsectionArray.getSize());
    }

    @Test
    public void case_020_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(20, 1);
        String heading = "II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURWA RYABYO";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(20,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_020_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(20, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_020_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(20, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_020_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(20, 2);
        String heading = "III.\\tICYEMEZO CY\\u2019URUKIKO";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(20,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_021_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(21);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA."));
    }

    @Test
    public void case_021_BackgroundSectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(21, "I.\\tIMITERERE Y\\u2019URUBANZA.");
        assertEquals(13, caseBackground.getSize());
    }

    @Test
    public void case_021_CaseBackgroundMatchesExpectedContent() {
        String heading = "I.\\tIMITERERE Y\\u2019URUBANZA.";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(21, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(21, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_021_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(21, 1);
        assertTrue(caseBody.hasKey("II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURA RYABYO."));
    }

    @Test
    public void case_021_SecondSubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(21, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURA RYABYO.");
        assertEquals(36, subsectionArray.getSize());
    }

    @Test
    public void case_021_SecondSubsectionMatchesExpectedContent() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(21, 1);
        String heading = "II.\\tIBIBAZO BIGIZE URUBANZA N\\u2019ISESENGURA RYABYO.";
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(21,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_021_ThirdSubsectionOfBodyHasExpectedHeading() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(21, 2);
        assertTrue(thirdSubsection.hasKey("III.\\tICYEMEZO CY\\u2019URUKIKO."));
    }

    @Test
    public void case_021_ThirdSubsectionHasExpectedLength() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(21, 2);
        JsonArray subsectionArray = thirdSubsection.getArrayByKey("III.\\tICYEMEZO CY\\u2019URUKIKO.");
        assertEquals(3, subsectionArray.getSize());
    }

    @Test
    public void case_021_ThirdSubsectionMatchesExpectedContent() {
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(21, 2);
        String heading = "III.\\tICYEMEZO CY\\u2019URUKIKO.";
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(21,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_030_CaseBodyHasExpectedLength() {
        JsonArray caseBody = CaseBodyParserHelpers.getActualCaseBody(30);
        assertEquals(4, caseBody.getSize());
    }

    @Test
    public void case_030_firstSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(30);
        String expectedHeading = (URUKIKO + " " + Keywords.RUSHINGIYE).toUpperCase();
        assertTrue(caseBody.hasKey(expectedHeading));
    }

    @Test
    public void case_030_firstCaseBodySubsectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(30, "URUKIKO RUSHINGIYE");
        assertEquals(4, caseBackground.getSize());
    }

    @Test
    public void case_030_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(30, 1);
        assertTrue(caseBody.hasKey("URUKIKO RUMAZE"));
    }

    @Test
    public void case_030_secondCaseBodySubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(30, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("URUKIKO RUMAZE");
        assertEquals(16, subsectionArray.getSize());
    }

    @Test
    public void case_030_ThridSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(30, 2);
        assertTrue(caseBody.hasKey("URUKIKO RUSANZE"));
    }

    @Test
    public void case_030_ThirdCaseBodySubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(30, 2);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("URUKIKO RUSANZE");
        assertEquals(4, subsectionArray.getSize());
    }

    @Test
    public void case_030_FourthSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(30, 3);
        assertTrue(caseBody.hasKey("Kubera iyo mpamvu"));
    }

    @Test
    public void case_030_FourthCaseBodySubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(30, 3);
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
        assertTrue(caseBody.hasKey("I.IMITERERE Y\\u2019URUBANZA."));
    }

    @Test
    public void case_031_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(31, 1);
        assertTrue(caseBody.hasKey("II.ISESENGURA RY\\u2019IKIBAZO KIGIZE URUBANZA."));
    }

    @Test
    public void case_031_ThirdSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(31, 2);
        assertTrue(caseBody.hasKey("III .ICYEMEZO CY\\u2019URUKIKO."));
    }

    @Ignore("Skip: related to missing line break")
    @Test
    public void case_034_CaseBodyHasExpectedLength() {
        JsonArray caseBody = CaseBodyParserHelpers.getActualCaseBody(34);
        assertEquals(3, caseBody.getSize());
    }

    @Test
    public void case_034_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(34);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Ignore("Skip: Word document missing line break after heading")
    @Test
    public void case_034_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(34, 1);
        assertTrue(caseBody.hasKey("II.\\tIKIBAZO KIGIZE URUBANZA N\\u2019ISESENGURWA RYACYO"));
    }

    @Test
    public void case_035_CaseBodyHasExpectedLength() {
        JsonArray caseBody = CaseBodyParserHelpers.getActualCaseBody(35);
        assertEquals(3, caseBody.getSize());
    }

    @Test
    public void case_035_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(35);
        assertTrue(caseBody.hasKey("I.\\tIMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_036_CaseBodyHasExpectedLength() {
        JsonArray caseBody = CaseBodyParserHelpers.getActualCaseBody(36);
        assertEquals(3, caseBody.getSize());
    }

    @Test
    public void case_036_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(36);
        assertTrue(caseBody.hasKey("1.IMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_036_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(36, 1);
        assertTrue(caseBody.hasKey("2.ISESENGURA RY\\u2019IKIBAZO KIRI MU RUBANZA"));
    }

    @Test
    public void case_036_ThirdSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(36, 2);
        assertTrue(caseBody.hasKey("3. ICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_038_CaseBodyHasExpectedLength() {
        JsonArray caseBody = CaseBodyParserHelpers.getActualCaseBody(38);
        assertEquals(3, caseBody.getSize());
    }

    @Test
    public void case_038_hasCaseBackgroundSection() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(38);
        assertTrue(caseBody.hasKey("I . IMITERERE Y\\u2019URUBANZA"));
    }

    @Test
    public void case_038_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(38, 1);
        assertTrue(caseBody.hasKey("II.ISESENGURA RY\\u2019URUBANZA"));
    }

    @Test
    public void case_038_ThirdSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(38, 2);
        assertTrue(caseBody.hasKey("III. ICYEMEZO CY\\u2019URUKIKO"));
    }

    @Test
    public void case_038_CaseBackgroundMatchesExpectedContent() {
        String heading = "I . IMITERERE Y\\u2019URUBANZA";
        String actualContent = CaseBodyParserHelpers.getCaseBackgroundSection(38, heading).toString();
        String expectedContent = CaseBodyParserHelpers.getExpectedCaseSubsection(38, 0, heading).toString();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void case_038_SecondSubsectionMatchesExpectedContent() {
        String heading = "II.ISESENGURA RY\\u2019URUBANZA";
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(38, 1);
        String actualSubsection = secondSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(38,
                1, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_038_ThirdSubsectionMatchesExpectedContent() {
        String heading = "III. ICYEMEZO CY\\u2019URUKIKO";
        JsonObject thirdSubsection = CaseBodyParserHelpers.getCaseBodySubsection(38, 2);
        String actualSubsection = thirdSubsection.getArrayByKey(heading).toString();
        String expectedSubsection = CaseBodyParserHelpers.getExpectedCaseSubsection(38,
                2, heading).toString();
        assertEquals(expectedSubsection, actualSubsection);
    }

    @Test
    public void case_039_CaseBodyHasExpectedLength() {
        JsonArray caseBody = CaseBodyParserHelpers.getActualCaseBody(39);
        assertEquals(4, caseBody.getSize());
    }

    @Test
    public void case_039_firstSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBackground(39);
        assertTrue(caseBody.hasKey("URUKIKO RUSHINGIYE"));
    }

    @Test
    public void case_039_firstCaseBodySubsectionHasExpectedLength() {
        JsonArray caseBackground = CaseBodyParserHelpers.getCaseBackgroundSection(39, "URUKIKO RUSHINGIYE");
        assertEquals(1, caseBackground.getSize());
    }

    @Test
    public void case_039_SecondSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(39, 1);
        assertTrue(caseBody.hasKey("URUKIKO RUMAZE"));
    }

    @Test
    public void case_038_secondCaseBodySubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(39, 1);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("URUKIKO RUMAZE");
        assertEquals(6, subsectionArray.getSize());
    }

    @Test
    public void case_039_ThridSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(39, 2);
        assertTrue(caseBody.hasKey("URUKIKO RUSANZE"));
    }

    @Test
    public void case_039_ThirdCaseBodySubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(39, 2);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("URUKIKO RUSANZE");
        assertEquals(8, subsectionArray.getSize());
    }

    @Test
    public void case_039_FourthSubsectionHasExpectedHeading() {
        JsonObject caseBody = CaseBodyParserHelpers.getCaseBodySubsection(39, 3);
        assertTrue(caseBody.hasKey("KUBERA IZO MPAMVU"));
    }

    @Test
    public void case_039_FourthCaseBodySubsectionHasExpectedLength() {
        JsonObject secondSubsection = CaseBodyParserHelpers.getCaseBodySubsection(39, 3);
        JsonArray subsectionArray = secondSubsection.getArrayByKey("KUBERA IZO MPAMVU");
        assertEquals(2, subsectionArray.getSize());
    }

}