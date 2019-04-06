package com.panavis.WordToJsonConverter;

import static com.panavis.WordToJsonConverter.Constants.Headings.*;
import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.*;
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
        SectionResult result = CasePartiesTestsHelpers.parseOneCaseAndReturnPartiesSection(21);
    }

    @Test
    public void case_000_comm_court_huye_2011_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(0, HABURANA);
    }

    @Test
    public void case_001_comm_court_huye_2016_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(1, ABABURANA);
    }

    @Test
    public void case_002_comm_court_huye_2018_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(2, HABURANA);
    }

    @Test
    public void case_003_comm_court_musanze_2011_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(3, HABURANA);
    }

    @Test
    public void case_004_comm_court_nyarugenge_2014_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(4, HABURANA);
    }

    @Test
    public void case_005_comm_court_nyarugenge_2016_PartiesSectionHas_ABABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(5, ABABURANA);
    }

    @Test
    public void case_006_comm_high_court_2016_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(6, HABURANA);
    }

    @Test
    public void case_007_high_court_chamber_nyanza_2014_PartiesSectionsHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(7, HABURANA);
    }

    @Test
    public void case_008_high_court_chamber_nyanza_2018_PartiesSectionsHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(8, HABURANA);
    }

    @Test
    public void case_009_high_court_criminal_2011_PartiesSectionsHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(9, HABURANA);
    }

    @Test
    public void case_010_interm_court_huye_2008_PartiesSectionHas_ABABURANYI_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(10, ABABURANYI);
    }

    @Test
    public void case_011_interm_court_huye_2015_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(11, HABURANA);
    }

    @Test
    public void case_012_interm_court_huye_2016_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(12, HABURANA);
    }

    @Test
    public void case_013_interm_court_huye_2018_226_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(13, HABURANA);
    }
    @Test
    public void case_014_interm_court_huye_2018_aff_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(14, HABURANA);
    }

    @Test
    public void case_015_primary_court_nzige_2011_rp0003_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(15, ABABURANYI);
    }

    @Test
    public void case_015_primary_court_nzige_2011_rp0020_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(16, ABABURANYI);
    }

    @Test
    public void case_017_supreme_court_comm_2009_PartiesSectionsHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(17, HABURANA);
    }

    @Test
    public void case_018_supreme_court_comm_2017_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(18, HABURANA);
    }

    @Test
    public void case_019_supreme_court_criminal_2018_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(19, HABURANA);
    }

    @Test
    public void case_020_supreme_court_criminal_2017_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(20, HABURANA);
    }

    @Test
    public void case_021_supreme_court_criminal_2017_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(21, HABURANA);
    }

    @Test
    public void case_000_comm_court_huye_2011_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(0, HABURANA, 2);
    }

    @Test
    public void case_001_comm_court_huye_2016_PartiesSectionHasThreeSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(1, ABABURANA, 3);
    }

    @Test
    public void case_002_comm_court_huye_2018_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(2, HABURANA, 2);
    }

    @Test
    public void case_003_comm_court_musanze_2011_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(3, HABURANA, 2);
    }

    @Test
    public void case_004_comm_court_nyarugenge_2014_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(4, HABURANA, 2);
    }

    @Test
    public void case_005_comm_court_nyarugenge_2016_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(5, ABABURANA, 2);
    }

    @Test
    public void case_006_comm_high_court_2016_PartiesSectionHasExpectedLength() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(6, HABURANA, 2);
    }


    @Test
    public void case_007_high_court_chamber_nyanza_2014_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(7, HABURANA, 2);
    }

    @Test
    public void case_008_high_court_chamber_nyanza_2018_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(8, HABURANA, 2);
    }

    @Test
    public void case_009_high_court_criminal_2011_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(9, HABURANA, 2);
    }

    @Test
    public void case_010_interm_court_huye_2008_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(10, ABABURANYI, 2);
    }

    @Test
    public void case_011_interm_court_huye_2015_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(11, HABURANA, 2);
    }

    @Test
    public void case_012_interm_court_huye_2016_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(12, HABURANA, 2);
    }

    @Test
    public void case_013_interm_court_huye_2018_226_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(13, HABURANA, 3);
    }

    @Test
    public void case_014_interm_court_huye_2018_aff_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(14, HABURANA, 2);
    }

    @Test
    public void case_015_primary_court_nzige_2011_rp0003_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(15, ABABURANYI, 2);
    }

    @Test
    public void case_015_primary_court_nzige_2011_rp0020_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(16, ABABURANYI, 2);
    }

    @Test
    public void case_017_supreme_court_comm_2009_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(17, HABURANA, 2);
    }

    @Test
    public void case_018_supreme_court_comm_2017_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(18, HABURANA, 2);
    }

    @Test
    public void case_019_supreme_court_criminal_2018_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(19, HABURANA, 2);
    }

    @Test
    public void case_020_supreme_court_criminal_2017_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(20, HABURANA, 2);
    }

    @Test
    public void case_021_supreme_court_criminal_2017_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(21, HABURANA, 2);
    }

    @Test
    public void case_000_comm_court_huye_2011_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(0, HABURANA, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_001_comm_court_huye_2016_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(1, ABABURANA, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_002_comm_court_huye_2018_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(2, HABURANA, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_003_comm_court_musanze_2011_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(3, HABURANA, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_004_comm_court_nyarugenge_2014_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(4, HABURANA, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_005_comm_court_nyarugenge_2016_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(5, ABABURANA, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_006_comm_high_court_2016_PartiesSectionHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(6, HABURANA, 0);
        assertTrue(actualParty.hasKey(UWAJURIYE));
    }

    @Test
    public void case_007_comm_high_chamber_nyanza_2014_PartiesSectionHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(7, HABURANA, 0);
        assertTrue(actualParty.hasKey(UWAJURIYE));
    }

    @Test
    public void case_008_high_court_chamber_nyanza_2018_PartiesSectionsHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(8, HABURANA, 0);
        assertTrue(actualParty.hasKey(UWAJURIYE));
    }

    @Test
    public void case_009_high_court_criminal_2011_PartiesSectionsHas_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(9, HABURANA, 0);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_010_interm_court_huye_2008_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(10, ABABURANYI, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_011_interm_court_huye_2015_PartiesSectionHas_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(11, HABURANA, 0);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_012_interm_court_huye_2016_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(12, HABURANA, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_013_interm_court_huye_2018_226_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, HABURANA, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_014_interm_court_huye_2018_aff_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(14, HABURANA, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_015_primary_court_nzige_2011_rp0003_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(15, ABABURANYI, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_015_primary_court_nzige_2011_rp0020_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(16, ABABURANYI, 0);
        assertTrue(actualParty.hasKey(UREGA));
    }

    @Test
    public void case_017_supreme_court_comm_2009_PartiesSectionsHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(17, HABURANA, 0);
        assertTrue(actualParty.hasKey(UWAJURIYE));
    }

    @Test
    public void case_018_supreme_court_comm_2017_PartiesSectionsHas_UREGA_mu_bujurire_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(18, HABURANA, 0);
        assertTrue(actualParty.hasKey("UREGA mu bujurire"));
    }

    @Test
    public void case_019_supreme_court_criminal_2018_PartiesSectionsHasProsecutorSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(19, HABURANA, 0);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_020_supreme_court_criminal_2017_PartiesSectionsHasProsecutorSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(20, HABURANA, 0);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_021_supreme_court_criminal_2017_PartiesSectionsHasProsecutorSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(21, HABURANA, 0);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_000_comm_court_huye_2011_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(0, HABURANA, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_001_comm_court_huye_2016_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(1, ABABURANA, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_002_comm_court_huye_2018_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(2, HABURANA, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_003_comm_court_musanze_2011_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(3, HABURANA, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_004_comm_court_nyarugenge_2014_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(4, HABURANA, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_005_comm_court_nyarugenge_2016_PartiesSectionHas_ABAREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(5, ABABURANA, 1);
        assertTrue(actualParty.hasKey(ABAREGWA));
    }

    @Test
    public void case_006_comm_high_court_2016_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(6, HABURANA, 1);
        assertTrue(actualParty.hasKey("UWAREZWE MU BUJURIRE"));
    }

    @Test
    public void case_007_high_court_chamber_nyanza_2014_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(7, HABURANA, 1);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_008_high_court_chamber_nyanza_2018_PartiesSectionHs_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(8, HABURANA, 1);
        assertTrue(actualParty.hasKey(UBUSHINJACYAHA));
    }

    @Test
    public void case_009_high_court_criminal_2011_PartiesSectionHs_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(9, HABURANA, 1);
        assertTrue(actualParty.hasKey(UWAJURIYE));
    }

    @Test
    public void case_010_interm_court_huye_2008_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(10, ABABURANYI, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_011_interm_court_huye_2015_PartiesSectionHas_USHINJWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(11, HABURANA, 1);
        assertTrue(actualParty.hasKey(USHINJWA));
    }

    @Test
    public void case_012_interm_court_huye_2016_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(12, HABURANA, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_013_interm_court_huye_2018_226_PartiesSectionHas_UREGERA_INDISHYI_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, HABURANA, 1);
        assertTrue(actualParty.hasKey("UREGERA INDISHYI WAJURIYE"));
    }

    @Test
    public void case_013_interm_court_huye_2018_226_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, HABURANA, 2);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_013_interm_court_huye_2018_226_Partie_UREGWA_Has_Two_subsections() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, HABURANA, 2);
        JsonArray defendant = actualParty.getArrayByKey(UREGWA);
        assertEquals(1, defendant.getSize());
    }

    @Test
    public void case_014_interm_court_huye_2018_aff_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(14, HABURANA, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_015_primary_court_nzige_2011_rp0003_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(15, ABABURANYI, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_016_primary_court_nzige_2011_rp0020_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(16, ABABURANYI, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_017_supreme_court_comm_2009_PartiesSectionsHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(17, HABURANA, 1);
        assertTrue(actualParty.hasKey(UREGWA));
    }

    @Test
    public void case_018_supreme_court_comm_2017_PartiesSectionsHas_ABAREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(18, HABURANA, 1);
        assertTrue(actualParty.hasKey(ABAREGWA));
    }

    @Test
    public void case_019_supreme_court_criminal_2018_PartiesSectionsHasUshinjwaSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(19, HABURANA, 1);
        assertTrue(actualParty.hasKey(USHINJWA.toLowerCase()));
    }

    @Test
    public void case_020_supreme_court_criminal_2017_PartiesSectionsHasUshinjwaSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(20, HABURANA, 1);
        assertTrue(actualParty.hasKey(USHINJWA.toLowerCase()));
    }

    @Test
    public void case_021_supreme_court_criminal_2017_PartiesSectionsHasUshinjwaSubsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(21, HABURANA, 1);
        assertTrue(actualParty.hasKey(USHINJWA.toLowerCase()));
    }

    @Test
    public void case_001_comm_court_huye_2016_PartiesSectionHas_ABAGOBOKESHWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(1, ABABURANA, 2);
        assertTrue(actualParty.hasKey(ABAGOBOKESHWA));
    }

    @Test
    public void case_001_comm_court_huye_2016_Parties_ABAGOBOKESHWA_subsection_should_have_two_elements() {
        JsonObject actualDefendant = CasePartiesTestsHelpers.getActualPartiesSubsection(1, ABABURANA, 2);
        JsonArray actualDefendantContent = actualDefendant.getArrayByKey(ABAGOBOKESHWA);

        assertEquals(2, actualDefendantContent.getSize());
    }

    @Test
    public void case_000_comm_court_huye_2011_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(0, 0, HABURANA, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_001_comm_court_huye_2016_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 0, ABABURANA, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_002_comm_court_huye_2018_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(2, 0, HABURANA, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_003_comm_court_musanze_2011_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(3, 0, HABURANA, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_004_comm_court_nyarugenge_2014_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(4, 0, HABURANA, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_005_comm_court_nyarugenge_2016_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(5, 0, ABABURANA, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_006_comm_high_court_2016_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(6, 0, HABURANA, UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_007_high_court_chamber_nyanza_2014_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(7, 0, HABURANA, UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_008_high_court_chamber_nyanza_2018_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(8, 0, HABURANA, UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_009_high_court_criminal_2011_Parties_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(9, 0, HABURANA, UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_010_interm_court_huye_2008_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(10, 0, ABABURANYI, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_011_interm_court_huye_2015_Parties_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(11, 0, HABURANA, UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }


    @Test
    public void case_012_interm_court_huye_2016_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(12, 0, HABURANA, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_013_interm_court_huye_2018_226_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 0, HABURANA, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_014_interm_court_huye_2018_aff_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(14, 0, HABURANA, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_015_primary_court_nzige_2011_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(15, 0, ABABURANYI, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_016_primary_court_nzige_2011_rp0020_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(16, 0, ABABURANYI, UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_017_supreme_court_comm_2009_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(17, 0, HABURANA, UWAJURIYE);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_018_supreme_court_comm_2017_Parties_UREGA_mu_bujurire_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(18, 0, HABURANA, "UREGA mu bujurire");
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_019_supreme_court_criminal_2018_Parties_ProsecutorSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(19, 0, HABURANA, UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_020_supreme_court_criminal_2018_Parties_ProsecutorSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(20, 0, HABURANA, UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_021_supreme_court_criminal_2018_Parties_ProsecutorSectionHasExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(21, 0, HABURANA, UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_000_comm_court_huye_2011_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(0, 1, HABURANA, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_001_comm_court_huye_2016_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 1, ABABURANA, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_002_comm_court_huye_2018_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(2, 1, HABURANA, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_003_comm_court_musanze_2011_Parties_UREGWA_subsectionMatchExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(3, 1, HABURANA, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_004_comm_court_nyarugenge_2014_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(4, 1, HABURANA, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_005_comm_court_nyarugenge_2016_Parties_ABAREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(5, 1, ABABURANA, ABAREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_006_comm_high_court_2016_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(6, 1,
                            HABURANA, "UWAREZWE MU BUJURIRE");
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_007_high_court_chamber_nyanza_2014_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(7, 1,
                HABURANA, UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_008_high_court_chamber_nyanza_2018_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(8, 1,
                HABURANA, UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_009_high_court_criminal_2011_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(9, 1,
                HABURANA, UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_011_interm_court_huye_2015_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(11, 1, HABURANA, USHINJWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_010_interm_court_huye_2008_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(10, 1, ABABURANYI, UREGWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_012_interm_court_huye_2016_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(12, 1, HABURANA, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_013_interm_court_huye_2018_226_Parties_UREGERA_INDISHYI_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 1, HABURANA, "UREGERA INDISHYI WAJURIYE");
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_013_interm_court_huye_2018_226_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 2, HABURANA, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_014_interm_court_huye_2018_aff_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(14, 1, HABURANA, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_015_primary_court_nzige_2011_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(15, 1, ABABURANYI, UREGWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_016_primary_court_nzige_2011_rp0020_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(16, 1, ABABURANYI, UREGWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_017_supreme_court_comm_2009_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(17, 1, HABURANA, UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_018_supreme_court_comm_2017_Parties_ABAREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(18, 1, HABURANA, ABAREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_019_supreme_court_criminal_2018_Parties_Ushinjwa_SubsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(19, 1, HABURANA, USHINJWA.toLowerCase());
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_020_supreme_court_criminal_2017_Parties_Ushinjwa_SubsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(20, 1, HABURANA, USHINJWA.toLowerCase());
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_021_supreme_court_criminal_2017_Parties_Ushinjwa_SubsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(21, 1, HABURANA, USHINJWA.toLowerCase());
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void case_001_comm_court_huye_2016_Parties_ABAGOBOKESWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 2, ABABURANA, ABAGOBOKESHWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }
}

