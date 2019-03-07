package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CaseSubjectMatterParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    private JsonArray getSubjectMatterSectionJsonArray(int wordDocIndex) {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(wordDocIndex);
        Converter converter = TestsSetup.getConverterObject(wordDocument, "subject_matter");
        converter.parseCaseSections();
        SectionResult subjectMatterResult = converter.getParsedCaseSection(Keywords.SUBJECT_MATTER);
        JsonObject subjectMatterContent = subjectMatterResult.getSectionContent();
        return subjectMatterContent.getArrayByKey(Keywords.SUBJECT_MATTER);
    }

    private JsonObject getSubjectMatterSubsection(int wordDocIndex) {
        return getSubjectMatterSectionJsonArray(wordDocIndex).getJsonObjectByIndex(0);
    }

    @Test
    public void comm_court_huye_2011_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(0);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void comm_court_huye_2016_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(1);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void comm_court_huye_2018_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(2);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void comm_court_nyarugenge_2014_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(4);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void comm_court_nyarugenge_2016_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(5);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void comm_high_court_2016_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(6);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void high_court_chamber_nyanza_2014_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(7);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void high_court_chamber_nyanza_2018_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(8);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void high_criminal_court_2011_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(9);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void interm_court_huye_2015_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(11);
        assertEquals(1, subjectMatterArray.getSize());
    }
    
    @Test
    public void interm_court_huye_2018_226_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(13);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void interm_court_huye_2018_aff_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(14);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void primary_court_nzige_2011_0003_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(15);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void primary_court_nzige_2011_0020_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(16);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void supreme_court_comm_2009_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(17);
        assertEquals(1, subjectMatterArray.getSize());
    }

    @Test
    public void supreme_court_comm_2017_SubjectMatterHasOneSubsection() {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(18);
        assertEquals(2, subjectMatterArray.getSize());
    }

    @Test
    public void comm_court_huye_2011_has_Ikiregerwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(0);
        assertTrue(subsection.hasKey(Headings.IKIREGERWA));
    }

    @Test
    public void comm_court_huye_2016_has_Ikiburanwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(1);
        assertTrue(subsection.hasKey(Headings.IKIBURANWA));
    }

    @Test
    public void comm_court_huye_2018_has_Ikiregerwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(2);
        assertTrue(subsection.hasKey(Headings.IKIREGERWA));
    }

    @Test
    public void comm_court_nyarugenge_2014_has_Ikiregerwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(4);
        assertTrue(subsection.hasKey(Headings.IKIREGERWA));
    }

    @Test
    public void comm_court_nyarugenge_2016_has_Ikiregerwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(5);
        assertTrue(subsection.hasKey(Headings.IKIREGERWA));
    }

    @Test
    public void comm_high_court_2016_has_Ikiburanwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(6);
        assertTrue(subsection.hasKey(Headings.IKIBURANWA));
    }

    @Test
    public void high_court_chamber_nyanza_2014_has_Ikiregerwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(7);
        assertTrue(subsection.hasKey(Headings.IKIREGERWA));
    }

    @Test
    public void high_court_chamber_nyanza_2018_has_Ikiburanwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(8);
        assertTrue(subsection.hasKey(Headings.IKIBURANWA));
    }

    @Test
    public void high_court_criminal_2011_has_Ikiregerwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(9);
        assertTrue(subsection.hasKey(Headings.IKIREGERWA));
    }

    @Test
    public void interm_court_huye_2015_has_Ibyaha_Ashinjwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(11);
        assertTrue(subsection.hasKey(Headings.IBYAHA_ASHINJWA));
    }

    @Test
    public void interm_court_huye_2018_226_has_Icyaha_Aregwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(13);
        assertTrue(subsection.hasKey(Headings.ICYAHA_AREGWA));
    }

    @Test
    public void interm_court_huye_2018_aff_has_Ikiregerwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(14);
        assertTrue(subsection.hasKey(Headings.IKIREGERWA));
    }

    @Test
    public void primary_court_nzige_2011_0003_has_Icyaha_Ashinjwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(15);
        assertTrue(subsection.hasKey(Headings.ICYAHA_ASHINJWA));
    }

    @Test
    public void primary_court_nzige_2011_0020_has_Icyaha_Ashinjwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(16);
        assertTrue(subsection.hasKey("ICYAHA ashinjwa"));
    }

    @Test
    public void supreme_court_comm_2009_has_Ikiregerwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(17);
        assertTrue(subsection.hasKey(Headings.IKIREGERWA));
    }

    @Test
    public void supreme_court_comm_2017_has_Ikiregerwa_subheading() {
        JsonObject subsection = getSubjectMatterSubsection(18);
        assertTrue(subsection.hasKey(Headings.IKIBURANWA));
    }

    @Test
    public void comm_court_huye_2011_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(0);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void comm_court_huye_2016_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(1);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIBURANWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void comm_court_huye_2018_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(2);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void comm_court_nyarugenge_2014_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(4);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void comm_court_nyarugenge_2016_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(5);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void comm_high_court_2016_subsectionHasANestedArrayWithLengthThree() {
        JsonObject subsection = getSubjectMatterSubsection(6);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIBURANWA);
        assertEquals(3, actualSubsectionArray.getSize());
    }

    @Test
    public void high_court_chamber_2014_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(7);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void high_court_chamber_2018_subsectionHasANestedArrayWithLengthTwo() {
        JsonObject subsection = getSubjectMatterSubsection(8);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIBURANWA);
        assertEquals(2, actualSubsectionArray.getSize());
    }

    @Test
    public void high_court_criminal_2011_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(9);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void interm_court_huye_2015_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(11);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IBYAHA_ASHINJWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void interm_court_huye_2018_226_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(13);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.ICYAHA_AREGWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void interm_court_huye_2018_aff_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(14);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIREGERWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void primary_court_nzige_2011_0003_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(15);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.ICYAHA_ASHINJWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void primary_court_nzige_2011_0020_subsectionHasANestedArrayWithLengthOne() {
        JsonObject subsection = getSubjectMatterSubsection(16);
        JsonArray actualSubsectionArray = subsection.getArrayByKey("ICYAHA ashinjwa");
        assertEquals(1, actualSubsectionArray.getSize());
    }

    @Test
    public void supreme_court_comm_2009_subsectionHasANestedArrayWithLengthEight() {
        JsonObject subsection = getSubjectMatterSubsection(17);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIREGERWA);
        assertEquals(8, actualSubsectionArray.getSize());
    }

    @Test
    public void supreme_court_comm_2017_subsectionHasANestedArrayWithLengthTwo() {
        JsonObject subsection = getSubjectMatterSubsection(18);
        JsonArray actualSubsectionArray = subsection.getArrayByKey(Headings.IKIBURANWA);
        assertEquals(1, actualSubsectionArray.getSize());
    }

    private String getActualSubsectionContent(int wordDocIndex, String heading, int subsectionIndex) {
        JsonArray subjectMatterArray = getSubjectMatterSectionJsonArray(wordDocIndex);
        JsonObject subsection = subjectMatterArray.getJsonObjectByIndex(subsectionIndex);
        return subsection.getArrayByKey(heading).toString();
    }

    private String getExpectedSubsectionContent(int jsonDocIndex, String heading, int subsectionIndex) {
        JsonObject expectedCaseJsonObject = TestsSetup.expectedJsonContent.get(jsonDocIndex);
        JsonArray expectedCase = expectedCaseJsonObject.getArrayByKey(Keywords.CASE);
        JsonObject expectedSection = expectedCase.getJsonObjectByIndex(2);
        JsonArray expectedSubjectMatterArray = expectedSection.getArrayByKey(Keywords.SUBJECT_MATTER);
        return expectedSubjectMatterArray.getJsonObjectByIndex(subsectionIndex)
                .getArrayByKey(heading).toString();
    }

    @Test
    public void comm_court_huye_2011_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(0,
                Headings.IKIREGERWA, 0);
        String expectedSubsectionArray = getExpectedSubsectionContent(0,
                Headings.IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void comm_court_huye_2016_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(1,
                Headings.IKIBURANWA, 0);
        String expectedSubsectionArray = getExpectedSubsectionContent(1,
                Headings.IKIBURANWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void comm_court_huye_2018_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(2,
                Headings.IKIREGERWA, 0);
        String expectedSubsectionArray = getExpectedSubsectionContent(2,
                Headings.IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void comm_court_nyarugenge_2014_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(4,
                Headings.IKIREGERWA, 0);
        String expectedSubsectionArray = getExpectedSubsectionContent(4,
                Headings.IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void comm_court_nyarugenge_2016_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(5,
                Headings.IKIREGERWA, 0);
        String expectedSubsectionArray = getExpectedSubsectionContent(5,
                Headings.IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void comm_high_court_2016_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(6,
                Headings.IKIBURANWA, 0);
        String expectedSubsectionArray = getExpectedSubsectionContent(6,
                Headings.IKIBURANWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void high_court_chamber_nyanza_2014_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(7,
                Headings.IKIREGERWA, 0);
        String expectedSubsectionArray = getExpectedSubsectionContent(7,
                Headings.IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void high_court_chamber_nyanza_2018_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(8,
                Headings.IKIBURANWA, 0);
        String expectedSubsectionArray = getExpectedSubsectionContent(8,
                Headings.IKIBURANWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void high_court_criminal_2011_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(9,
                Headings.IKIREGERWA, 0);
        String expectedSubsectionArray = getExpectedSubsectionContent(9,
                Headings.IKIREGERWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }

    @Test
    public void interm_court_huye_2015_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(11,
                Headings.IBYAHA_ASHINJWA, 0);
        String expectedSubsectionArray = getExpectedSubsectionContent(11,
                Headings.IBYAHA_ASHINJWA, 0);
        assertEquals(expectedSubsectionArray, actualSubsectionContent);
    }


    @Test
    public void interm_court_huye_2018_226_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(13,
                Headings.ICYAHA_AREGWA, 0);
        String expectedSubsectionContent = getExpectedSubsectionContent(13,
                Headings.ICYAHA_AREGWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void interm_court_huye_2018_aff_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(14,
                Headings.IKIREGERWA, 0);
        String expectedSubsectionContent = getExpectedSubsectionContent(14,
                Headings.IKIREGERWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void primary_court_nzige_2011_0003_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(15,
                Headings.ICYAHA_ASHINJWA, 0);
        String expectedSubsectionContent = getExpectedSubsectionContent(15,
                Headings.ICYAHA_ASHINJWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void primary_court_nzige_2011_0020_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(16,
                "ICYAHA ashinjwa", 0);
        String expectedSubsectionContent = getExpectedSubsectionContent(16,
                "ICYAHA ashinjwa", 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void supreme_court_comm_2009_subjectMatterMatchesExpectedContent() {
        String actualSubsectionContent = getActualSubsectionContent(17,
                Headings.IKIREGERWA, 0);
        String expectedSubsectionContent = getExpectedSubsectionContent(17,
                Headings.IKIREGERWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void supreme_court_comm_2017_subjectMatterMatchesExpectedContentSubsectionOne() {
        String actualSubsectionContent = getActualSubsectionContent(18,
                Headings.IKIBURANWA, 0);
        String expectedSubsectionContent = getExpectedSubsectionContent(18,
                Headings.IKIBURANWA, 0);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }

    @Test
    public void supreme_court_comm_2017_subjectMatterMatchesExpectedContentSubsectionTwo() {
        String actualSubsectionContent = getActualSubsectionContent(18,
                Headings.IKIBURANWA, 1);
        String expectedSubsectionContent = getExpectedSubsectionContent(18,
                Headings.IKIBURANWA, 1);
        assertEquals(expectedSubsectionContent, actualSubsectionContent);
    }
}
