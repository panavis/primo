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
    public void comm_court_huye_2011_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(0);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void comm_court_huye_2016_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(1);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void comm_court_huye_2018_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(2);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void comm_court_nyarugenge_2014_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(4);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void comm_court_nyarugenge_2016_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(5);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void comm_high_court_2016_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(6);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void high_court_chamber_nyanza_2014_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(7);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void high_court_chamber_nyanza_2018_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(8);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void high_criminal_court_2011_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(9);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void interm_court_huye_2015_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(11);
        assertEquals(1, subjectMatterArray.getSize());
    }
    
    @Test
    public void interm_court_huye_2018_226_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(13);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void interm_court_huye_2018_aff_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(14);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void primary_court_nzige_2011_0003_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(15);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void primary_court_nzige_2011_0020_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(16);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void supreme_court_comm_2009_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(17);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void supreme_court_comm_2017_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSectionArray(18);
        assertEquals(2, subjectMatterArray.getSize());
    }

    @Test
    public void comm_court_huye_2011_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(0, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void comm_court_huye_2016_has_Ikiburanwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(1, 0);
        assertTrue(subsection.hasKey(IKIBURANWA));
    }

    @Test
    public void comm_court_huye_2018_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(2, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void comm_court_nyarugenge_2014_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(4, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void comm_court_nyarugenge_2016_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(5, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void comm_high_court_2016_has_Ikiburanwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(6, 0);
        assertTrue(subsection.hasKey(IKIBURANWA));
    }

    @Test
    public void high_court_chamber_nyanza_2014_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(7, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void high_court_chamber_nyanza_2018_has_Ikiburanwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(8, 0);
        assertTrue(subsection.hasKey(IKIBURANWA));
    }

    @Test
    public void high_court_criminal_2011_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(9, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void interm_court_huye_2015_has_Ibyaha_Ashinjwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(11, 0);
        assertTrue(subsection.hasKey(IBYAHA_ASHINJWA));
    }

    @Test
    public void interm_court_huye_2018_226_has_Icyaha_Aregwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(13, 0);
        assertTrue(subsection.hasKey(ICYAHA_AREGWA));
    }

    @Test
    public void interm_court_huye_2018_aff_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(14, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void primary_court_nzige_2011_0003_has_Icyaha_Ashinjwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(15, 0);
        assertTrue(subsection.hasKey(ICYAHA_ASHINJWA));
    }

    @Test
    public void primary_court_nzige_2011_0020_has_Icyaha_Ashinjwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(16, 0);
        assertTrue(subsection.hasKey("ICYAHA ashinjwa"));
    }

    @Test
    public void supreme_court_comm_2009_has_Ikiregerwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(17, 0);
        assertTrue(subsection.hasKey(IKIREGERWA));
    }

    @Test
    public void supreme_court_comm_2017_has_Ikiburanwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(18, 0);
        assertTrue(subsection.hasKey(IKIBURANWA));
    }

    @Test
    public void supreme_court_comm_2017_hasSecond_Ikiburanwa_subheading() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(18, 1);
        assertTrue(subsection.hasKey(IKIBURANWA));
    }

    @Test
    public void comm_court_huye_2011_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(0, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void comm_court_huye_2016_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(1, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIBURANWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void comm_court_huye_2018_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(2, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void comm_court_nyarugenge_2014_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(4, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void comm_court_nyarugenge_2016_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(5, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(0, actualSubsectionArray.getSize());
    }

    @Test
    public void comm_high_court_2016_subsectionHasANestedArrayWithLengthThree() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(6, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIBURANWA);
        assertEquals(3, actualSubsectionArray.getSize());
    }

    @Test
    public void high_court_chamber_2014_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(7, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void high_court_chamber_2018_subsectionHasANestedArrayWithLengthTwo() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(8, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIBURANWA);
        assertEquals(2, actualSubsectionArray.getSize());
    }

    @Test
    public void high_court_criminal_2011_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(9, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void interm_court_huye_2015_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(11, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IBYAHA_ASHINJWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void interm_court_huye_2018_226_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(13, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(ICYAHA_AREGWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void interm_court_huye_2018_aff_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(14, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void primary_court_nzige_2011_0003_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(15, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(ICYAHA_ASHINJWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void primary_court_nzige_2011_0020_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(16, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey("ICYAHA ashinjwa");
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void supreme_court_comm_2009_subsectionHasANestedArrayWithLengthEight() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(17, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIREGERWA);
        assertEquals(8, actualSubsectionArray.getSize());
    }

    @Test
    public void supreme_court_comm_2017_subsectionHasANestedArrayWithLengthTwo() {
        JsonObject subsection = CaseSubjectMatterTestsHelpers.getActualSubjectMatterSubsection(18, 0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(IKIBURANWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void comm_court_huye_2011_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(0,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(0,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void comm_court_huye_2016_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(1,
                IKIBURANWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(1,
                IKIBURANWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void comm_court_huye_2018_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(2,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(2,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void comm_court_nyarugenge_2014_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(4,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(4,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void comm_court_nyarugenge_2016_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(5,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(5,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void comm_high_court_2016_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(6,
                IKIBURANWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(6,
                IKIBURANWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void high_court_chamber_nyanza_2014_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(7,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(7,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void high_court_chamber_nyanza_2018_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(8,
                IKIBURANWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(8,
                IKIBURANWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void high_court_criminal_2011_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(9,
                IKIREGERWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(9,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void interm_court_huye_2015_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(11,
                IBYAHA_ASHINJWA, 0);
        String expectedSubsectionArray = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(11,
                IBYAHA_ASHINJWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }


    @Test
    public void interm_court_huye_2018_226_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(13,
                ICYAHA_AREGWA, 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(13,
                ICYAHA_AREGWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void interm_court_huye_2018_aff_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(14,
                IKIREGERWA, 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(14,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void primary_court_nzige_2011_0003_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(15,
                ICYAHA_ASHINJWA, 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(15,
                ICYAHA_ASHINJWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void primary_court_nzige_2011_0020_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(16,
                "ICYAHA ashinjwa", 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(16,
                "ICYAHA ashinjwa", 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void supreme_court_comm_2009_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(17,
                IKIREGERWA, 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(17,
                IKIREGERWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void supreme_court_comm_2017_subjectMatterMatchesExpectedContentSubsectionOne() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(18,
                IKIBURANWA, 0);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(18,
                IKIBURANWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void supreme_court_comm_2017_subjectMatterMatchesExpectedContentSubsectionTwo() {
        String actualSubsectionContent = CaseSubjectMatterTestsHelpers.getActualSubsectionContent(18,
                IKIBURANWA, 1);
        String expectedSubsectionContent = CaseSubjectMatterTestsHelpers.getExpectedSubsectionContent(18,
                IKIBURANWA, 1);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }
}
