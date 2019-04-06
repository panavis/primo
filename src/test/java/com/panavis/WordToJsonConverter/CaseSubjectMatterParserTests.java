package com.panavis.WordToJsonConverter;

import static com.panavis.WordToJsonConverter.Constants.Headings.*;
import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import static junit.framework.TestCase.*;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.*;
import org.junit.*;

public class CaseSubjectMatterParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
        CaseSubjectMatterTestsHelpers.setUpAllActualSubjectMatterJsons();
    }

    @Ignore("Ignore: SubjectMatter section troubleshooter.")
    @Test
    public void troubleshootOneCaseSeparately() {
        SectionResult subjectMatter = CaseSubjectMatterTestsHelpers.parseOneCaseAndReturnSubjectMatterSection(6);
        JsonArray sectionArray = subjectMatter.getSectionContent().getArrayByKey(SUBJECT_MATTER);
    }

    @Test
    public void Case_000_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(0);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_001_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(1);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_002_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(2);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_004_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(4);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_005_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(5);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_006_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(6);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_007_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(7);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_008_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(8);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_009_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(9);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_011_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(11);
        assertEquals(1, subjectMatterArray.getSize());
    }
    
    @Test
    public void Case_013_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(13);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_014_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(14);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_015_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(15);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_016_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(16);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_017_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(17);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void Case_018_SubjectMatterHasExpectedLength() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(18);
        assertEquals(2, subjectMatterArray.getSize());
    }

    @Test
    public void case_000_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(0, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void case_001_has_Ikiburanwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(1, 0);
        assertTrue(subsection.hasKey(IKIBURANWA));
    }

    @Test
    public void case_002_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(2, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void case_004_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(4, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void case_005_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(5, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void case_006_Ikiburanwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(6, 0);
        assertTrue(subsection.hasKey(IKIBURANWA));
    }

    @Test
    public void case_007_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(7, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void case_008_has_Ikiburanwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(8, 0);
        assertTrue(subsection.hasKey(IKIBURANWA));
    }

    @Test
    public void case_009_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(9, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void case_011_has_Ibyaha_Ashinjwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(11, 0);
        assertTrue(subsection.hasKey(IBYAHA_ASHINJWA));
    }

    @Test
    public void case_013_has_Icyaha_Aregwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(13, 0);
        assertTrue(subsection.hasKey(ICYAHA_AREGWA));
    }

    @Test
    public void case_014_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(14, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void case_015_has_Icyaha_Ashinjwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(15, 0);
        assertTrue(subsection.hasKey(ICYAHA_ASHINJWA));
    }

    @Test
    public void case_016_has_Icyaha_Ashinjwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(16, 0);
        assertTrue(subsection.hasKey("ICYAHA ashinjwa"));
    }

    @Test
    public void case_017_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(17, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void case_018_has_Ikiburanwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(18, 0);
        assertTrue(subsection.hasKey(IKIBURANWA));
    }

    @Test
    public void case_018_hasSecond_Ikiburanwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(18, 1);
        assertTrue(subsection.hasKey(IKIBURANWA));
    }

    @Test
    public void Case_000_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(0, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_001_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(1, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIBURANWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_002_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(2, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_004_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(4, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_005_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(5, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(0, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_006_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(6, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIBURANWA);
        assertEquals(3, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_007_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(7, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_008_SubsectionHasANestedArrayWithLExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(8, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIBURANWA);
        assertEquals(2, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_009_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(9, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_011_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(11, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IBYAHA_ASHINJWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_013_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(13, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(ICYAHA_AREGWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_014_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(14, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_015_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(15, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(ICYAHA_ASHINJWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_016_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(16, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey("ICYAHA ashinjwa");
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_017_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(17, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(8, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_018_SubsectionHasANestedArrayWithExpectedLength() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(18, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIBURANWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void Case_000_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(0,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(0,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void Case_001_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(1,
                IKIBURANWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(1,
                IKIBURANWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void Case_002_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(2,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(2,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void Case_004_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(4,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(4,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void Case_005_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(5,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(5,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void Case_006_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(6,
                IKIBURANWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(6,
                IKIBURANWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void Case_007_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(7,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(7,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void Case_008_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(8,
                IKIBURANWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(8,
                IKIBURANWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void Case_009_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(9,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(9,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void Case_011_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(11,
                IBYAHA_ASHINJWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(11,
                IBYAHA_ASHINJWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }


    @Test
    public void Case_013_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(13,
                ICYAHA_AREGWA, 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(13,
                ICYAHA_AREGWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void Case_014_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(14,
                IKIREGERWA, 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(14,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void Case_015_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(15,
                ICYAHA_ASHINJWA, 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(15,
                ICYAHA_ASHINJWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void Case_016_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(16,
                "ICYAHA ashinjwa", 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(16,
                "ICYAHA ashinjwa", 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void Case_017_SubjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(17,
                IKIREGERWA, 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(17,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void Case_018_SubjectMatterMatchesExpectedContentSubsectionOne() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(18,
                IKIBURANWA, 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(18,
                IKIBURANWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void case_018_SubjectMatterMatchesExpectedContentSubsectionTwo() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(18,
                IKIBURANWA, 1);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(18,
                IKIBURANWA, 1);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void Case_019_SubjectMatterMatchesExpectedContentSubsectionOne() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(19,
                ICYAHA_AREGWA, 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(19                   ,
                ICYAHA_AREGWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void case_019_SubjectMatterMatchesExpectedContentSubsectionTwo() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(19,
                IKIREGERWA, 1);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(19,
                IKIREGERWA, 1);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }
}
