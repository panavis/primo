package com.panavis.primo.Parsers;

import static com.panavis.primo.Constants.Headings.*;
import static com.panavis.primo.Constants.Keywords.*;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.*;
import org.junit.*;

import static org.junit.Assert.*;

public class CasePartiesParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
        CasePartiesTestsHelpers.setUpExpectedAndActualJsons();
    }

    @Ignore("Ignore: Parties section troubleshooter.")
    @Test
    public void troubleshootOneCaseSeparately() {
        SectionResult result = CasePartiesTestsHelpers.parseOneCaseAndReturnPartiesSection(8);
        System.out.println(result.getSectionContent().toString());
    }

    @Test
    public void case_000_comm_court_huye_2011_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(0, 2);
    }

    @Test
    public void case_001_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(1, 3);
    }

    @Test
    public void case_002_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(2, 2);
    }

    @Test
    public void case_003_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(3, 2);
    }

    @Test
    public void case_004_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(4, 2);
    }

    @Test
    public void case_005_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(5, 2);
    }

    @Test
    public void case_006_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(6, 2);
    }


    @Test
    public void case_007_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(7, 2);
    }

    @Test
    public void case_008_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(8, 2);
    }

    @Test
    public void case_009_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(9, 2);
    }

    @Test
    public void case_010_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(10, 2);
    }

    @Test
    public void case_011_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(11, 2);
    }

    @Test
    public void case_012_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(12, 2);
    }

    @Test
    public void case_013_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(13, 3);
    }

    @Test
    public void case_014_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(14, 2);
    }

    @Test
    public void case_015_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(15, 2);
    }

    @Test
    public void case_016_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(16, 2);
    }

    @Test
    public void case_017_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(17, 2);
    }

    @Test
    public void case_018_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(18, 2);
    }

    @Test
    public void case_019_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(19, 2);
    }

    @Test
    public void case_020_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(20, 3);
    }

    @Test
    public void case_021_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(21, 2);
    }

    @Test
    public void case_025_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(25, 2);
    }

    @Test
    public void case_026_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(26, 2);
    }

    @Test
    public void case_027_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(27, 2);
    }

    @Test
    public void case_028_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(28, 2);
    }

    @Test
    public void case_029_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(29, 2);
    }

    @Test
    public void case_031_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(31, 2);
    }

    @Test
    public void case_032_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(32, 2);
    }

    @Test
    public void case_033_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(33, 2);
    }

    @Test
    public void case_036_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(36, 2);
    }

    @Test
    public void case_037_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(37, 2);
    }

    @Test
    public void case_000_comm_court_huye_2011_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(0, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_001_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(1, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_002_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(2, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_003_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(3, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_004_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(4, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_005_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(5, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_006_PartiesSectionHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(6, 0);
        assertTrue(actualParty.hasKey(UWAJURIYE));
    }

    @Test
    public void case_007_PartiesSectionHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(7, 0);
        assertTrue(actualParty.hasKey(UWAJURIYE));
    }

    @Test
    public void case_008_PartiesSectionsHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(8, 0);
        assertTrue(actualParty.hasKey(UWAJURIYE));
    }

    @Test
    public void case_009_PartiesSectionsHas_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(9, 0);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_010_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(10, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_011_PartiesSectionHas_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(11, 0);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_012_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(12, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_013_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_014_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(14, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_015_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(15, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_016_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(16, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_017_PartiesSectionsHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(17, 0);
        assertTrue(actualParty.hasKey(UWAJURIYE));
    }

    @Test
    public void case_018_PartiesSectionsHas_UREGA_mu_bujurire_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(18, 0);
        assertTrue(actualParty.hasKey("UREGA mu bujurire"));
    }

    @Test
    public void case_019_PartiesSectionsHasProsecutorSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(19, 0);
        assertTrue(actualParty.hasKey(DEFAULT_PARTY_SUBHEADING));
    }

    @Test
    public void case_020_PartiesSectionsHasProsecutorSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(20, 0);
        assertTrue(actualParty.hasKey(DEFAULT_PARTY_SUBHEADING));
    }

    @Test
    public void case_021_PartiesSectionsHasProsecutorSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(21, 0);
        assertTrue(actualParty.hasKey(DEFAULT_PARTY_SUBHEADING));
    }

    @Test
    public void case_025_PartiesSectionsHasDefaultPartySubheadingAsFirstSection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(25, 0);
        assertTrue(actualParty.hasKey(DEFAULT_PARTY_SUBHEADING));
    }

    @Test
    public void case_028_PartiesSectionsHasDefaultPartySubheadingAsFirstSection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(28, 0);
        assertTrue(actualParty.hasKey(UWAJURIYE));
    }

    @Test
    public void case_032_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(32, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_033_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(33, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_036_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(36, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_037_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(37, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_000_comm_court_huye_2011_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(0, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_001_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(1, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_002_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(2, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_003_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(3, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_004_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(4, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_005_PartiesSectionHas_ABAREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(5, 1);
        assertTrue(actualParty.hasKey(ABAREGWA));
    }

    @Test
    public void case_006_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(6, 1);
        assertTrue(actualParty.hasKey("UWAREZWE MU BUJURIRE"));
    }

    @Test
    public void case_007_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(7, 1);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_008_PartiesSectionHas_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(8, 1);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_009_PartiesSectionHas_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(9, 1);
        assertTrue(actualParty.hasKey(UWAJURIYE));
    }

    @Test
    public void case_010_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(10, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_011_PartiesSectionHas_USHINJWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(11, 1);
        assertTrue(actualParty.hasKey(USHINJWA));
    }

    @Test
    public void case_012_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(12, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_013_PartiesSectionHas_UREGERA_INDISHYI_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, 1);
        assertTrue(actualParty.hasKey("UREGERA INDISHYI WAJURIYE"));
    }

    @Test
    public void case_013_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, 2);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_013_interm_court_huye_2018_226_Partie_UREGWA_Has_Two_subsections() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, 2);
        JsonArray defendant = actualParty.getArrayByKey(UREGWA);
        assertEquals(1, defendant.getSize());
    }

    @Test
    public void case_014_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(14, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_015_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(15, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_016_primary_court_nzige_2011_rp0020_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(16, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_017_PartiesSectionsHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(17, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_018_PartiesSectionsHas_ABAREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(18, 1);
        assertTrue(actualParty.hasKey(ABAREGWA));
    }

    @Test
    public void case_019_PartiesSectionsHasUshinjwaSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(19, 1);
        assertTrue(actualParty.hasKey(DEFAULT_PARTY_SUBHEADING));
    }

    @Test
    public void case_020_PartiesSectionsHasUshinjwaSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(20, 1);
        assertTrue(actualParty.hasKey(DEFAULT_PARTY_SUBHEADING));
    }

    @Test
    public void case_021_PartiesSectionsHasUshinjwaSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(21, 1);
        assertTrue(actualParty.hasKey(DEFAULT_PARTY_SUBHEADING));
    }

    @Test
    public void case_025_PartiesSectionsHasDefaultPartySubheadingAsSecondSection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(25, 1);
        assertTrue(actualParty.hasKey(DEFAULT_PARTY_SUBHEADING));
    }

    @Test
    public void case_028_PartiesSectionsHasDefaultPartySubheadingAsSecondSection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(28, 1);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_032_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(32, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_033_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(33, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_036_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(36, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_037_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(37, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_001_PartiesSectionHas_ABAGOBOKESHWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(1, 2);
        assertTrue(actualParty.hasKey(ABAGOBOKESHWA));
    }

    @Test
    public void case_001_Parties_ABAGOBOKESHWA_subsection_should_have_two_elements() {
        JsonObject actualDefendant = CasePartiesTestsHelpers.getActualPartiesSubsection(1, 2);
        JsonArray actualDefendantContent = actualDefendant.getArrayByKey(ABAGOBOKESHWA);

        assertEquals(2, actualDefendantContent.getSize());
    }

    @Test
    public void case_000_comm_court_huye_2011_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(0, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_001_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_002_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(2, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_003_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(3, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_004_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(4, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_005_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(5, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_006_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(6, 0, UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_007_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(7, 0, UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_008_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(8, 0, UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_009_Parties_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(9, 0, UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_010_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(10, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_011_Parties_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(11, 0, UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }


    @Test
    public void case_012_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(12, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_013_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_014_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(14, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_015_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(15, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_016_primary_court_nzige_2011_rp0020_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(16, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_017_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(17, 0, UWAJURIYE);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_018_Parties_UREGA_mu_bujurire_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(18, 0, "UREGA mu bujurire");
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_019_Parties_ProsecutorSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(19, 0, DEFAULT_PARTY_SUBHEADING);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_020_Parties_ProsecutorSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(20, 0, DEFAULT_PARTY_SUBHEADING);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_021_Parties_ProsecutorSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(21, 0, DEFAULT_PARTY_SUBHEADING);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_025_Parties_FirstSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(25, 0, DEFAULT_PARTY_SUBHEADING);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_032_Parties_FirstSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(32, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_033_Parties_FirstSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(33, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_036_Parties_FirstSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(36, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_037_Parties_FirstSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(37, 0, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_000_comm_court_huye_2011_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(0, 1, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_001_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 1, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_002_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(2, 1, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_003_Parties_UREGWA_subsectionMatchExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(3, 1, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_004_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(4, 1, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_005_Parties_ABAREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(5, 1, ABAREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_006_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(6, 1,
                "UWAREZWE MU BUJURIRE");
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_007_high_court_chamber_nyanza_2014_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(7, 1,
                UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_008_high_court_chamber_nyanza_2018_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(8, 1,
                UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_009_high_court_criminal_2011_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(9, 1,
                UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_011_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(11, 1, USHINJWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_010_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(10, 1, UREGWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_012_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(12, 1, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_013_Parties_UREGERA_INDISHYI_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 1, "UREGERA INDISHYI WAJURIYE");
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_013_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 2, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_014_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(14, 1, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_015_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(15, 1, UREGWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_016_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(16, 1, UREGWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_017_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(17, 1, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_018_Parties_ABAREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(18, 1, ABAREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_019_Parties_Ushinjwa_SubsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(19, 1, DEFAULT_PARTY_SUBHEADING);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_020_Parties_Ushinjwa_SubsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(20, 1, DEFAULT_PARTY_SUBHEADING);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_021_Parties_Ushinjwa_SubsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(21, 1, DEFAULT_PARTY_SUBHEADING);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_025_Parties_SecondSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(25, 1, DEFAULT_PARTY_SUBHEADING);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_028_Parties_SecondSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(28, 1, UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_036_Parties_SecondSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(36, 1, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_037_Parties_SecondSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(37, 1, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_001_Parties_ABAGOBOKESWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 2, ABAGOBOKESHWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }
}

