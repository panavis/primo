package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CasePartiesParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
        CasePartiesTestsHelpers.setUpExpectedAndActualJsons();
    }

    @Ignore("Ignore: Parties section troubleshooter.")
    @Test
    public void troubleshootOneCaseSeparately() {
        SectionResult result = CasePartiesTestsHelpers.parseOneCaseAndReturnPartiesSection(3);
    }

    @Test
    public void comm_court_huye_2011_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(0, Headings.HABURANA);
    }

    @Test
    public void comm_court_huye_2016_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(1, Headings.ABABURANA);
    }

    @Test
    public void comm_court_huye_2018_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(2, Headings.HABURANA);
    }

    @Test
    public void comm_court_musanze_2011_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(3, Headings.HABURANA);
    }

    @Test
    public void comm_court_nyarugenge_2014_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(4, Headings.HABURANA);
    }

    @Test
    public void comm_court_nyarugenge_2016_PartiesSectionHas_ABABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(5, Headings.ABABURANA);
    }

    @Test
    public void comm_high_court_2016_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(6, Headings.HABURANA);
    }

    @Test
    public void high_court_chamber_nyanza_2014_PartiesSectionsHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(7, Headings.HABURANA);
    }

    @Test
    public void high_court_chamber_nyanza_2018_PartiesSectionsHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(8, Headings.HABURANA);
    }

    @Test
    public void high_court_criminal_2011_PartiesSectionsHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(9, Headings.HABURANA);
    }

    @Test
    public void interm_court_huye_2008_PartiesSectionHas_ABABURANYI_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(10, Headings.ABABURANYI);
    }

    @Test
    public void interm_court_huye_2015_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(11, Headings.HABURANA);
    }

    @Test
    public void interm_court_huye_2016_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(12, Headings.HABURANA);
    }

    @Test
    public void interm_court_huye_2018_226_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(13, Headings.HABURANA);
    }
    @Test
    public void interm_court_huye_2018_aff_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(14, Headings.HABURANA);
    }

    @Test
    public void primary_court_nzige_2011_rp0003_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(15, Headings.ABABURANYI);
    }

    @Test
    public void primary_court_nzige_2011_rp0020_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(16, Headings.ABABURANYI);
    }

    @Test
    public void supreme_court_comm_2009_PartiesSectionsHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(17, Headings.HABURANA);
    }

    @Test
    public void supreme_court_comm_2017_PartiesSectionHas_HABURANA_asJsonKey() {
        CasePartiesTestsHelpers.assertJsonContainsPartiesSection(18, Headings.HABURANA);
    }

    @Test
    public void comm_court_huye_2011_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(0, Headings.HABURANA, 2);
    }

    @Test
    public void comm_court_huye_2016_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(1, Headings.ABABURANA, 3);
    }

    @Test
    public void comm_court_huye_2018_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(2, Headings.HABURANA, 2);
    }

    @Test
    public void comm_court_musanze_2011_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(3, Headings.HABURANA, 2);
    }

    @Test
    public void comm_court_nyarugenge_2014_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(4, Headings.HABURANA, 2);
    }

    @Test
    public void comm_court_nyarugenge_2016_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(5, Headings.ABABURANA, 2);
    }

    @Test
    public void comm_high_court_2016_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(6, Headings.HABURANA, 2);
    }


    @Test
    public void high_court_chamber_2014_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(7, Headings.HABURANA, 2);
    }

    @Test
    public void high_court_chamber_2018_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(8, Headings.HABURANA, 2);
    }

    @Test
    public void high_court_criminal_2011_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(9, Headings.HABURANA, 2);
    }

    @Test
    public void interm_court_huye_2008_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(10, Headings.ABABURANYI, 2);
    }

    @Test
    public void interm_court_huye_2015_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(11, Headings.HABURANA, 2);
    }

    @Test
    public void interm_court_huye_2016_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(12, Headings.HABURANA, 2);
    }

    @Test
    public void interm_court_huye_2018_226_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(13, Headings.HABURANA, 3);
    }

    @Test
    public void interm_court_huye_2018_aff_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(14, Headings.HABURANA, 2);
    }

    @Test
    public void primary_court_nzige_2011_rp0003_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(15, Headings.ABABURANYI, 2);
    }

    @Test
    public void primary_court_nzige_2011_rp0020_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(16, Headings.ABABURANYI, 2);
    }

    @Test
    public void supreme_court_comm_2009_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(17, Headings.HABURANA, 2);
    }

    @Test
    public void supreme_court_comm_2017_PartiesSectionHasTwoSubsections() {
        CasePartiesTestsHelpers.assertCorrectNumberOfSubsections(18, Headings.HABURANA, 2);
    }

    @Test
    public void comm_court_huye_2011_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(0, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_court_huye_2016_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(1, Headings.ABABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_court_huye_2018_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(2, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_court_musanze_2011_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(3, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_court_nyarugenge_2014_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(4, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_court_nyarugenge_2016_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(5, Headings.ABABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_high_court_2016_PartiesSectionHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(6, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UWAJURIYE));
    }

    @Test
    public void comm_high_chamber_nyanza_2014_PartiesSectionHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(7, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UWAJURIYE));
    }

    @Test
    public void high_court_chamber_nyanza_2018_PartiesSectionsHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(8, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UWAJURIYE));
    }

    @Test
    public void high_court_criminal_2011_PartiesSectionsHas_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(9, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Keywords.UBUSHINJACYAHA));
    }

    @Test
    public void interm_court_huye_2008_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(10, Headings.ABABURANYI, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void interm_court_huye_2015_PartiesSectionHas_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(11, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Keywords.UBUSHINJACYAHA));
    }

    @Test
    public void interm_court_huye_2016_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(12, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void interm_court_huye_2018_226_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void interm_court_huye_2018_aff_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(14, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void primary_court_nzige_2011_rp0003_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(15, Headings.ABABURANYI, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void primary_court_nzige_2011_rp0020_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(16, Headings.ABABURANYI, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void supreme_court_comm_2009_PartiesSectionsHas_UWAJURIYE_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(17, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UWAJURIYE));
    }

    @Test
    public void supreme_court_comm_2017_PartiesSectionsHas_UREGA_mu_bujurire_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(18, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey("UREGA mu bujurire"));
    }

    @Test
    public void comm_court_huye_2011_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(0, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void comm_court_huye_2016_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(1, Headings.ABABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void comm_court_huye_2018_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(2, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void comm_court_musanze_2011_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(3, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void comm_court_nyarugenge_2014_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(4, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void comm_court_nyarugenge_2016_PartiesSectionHas_ABAREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(5, Headings.ABABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.ABAREGWA));
    }

    @Test
    public void comm_high_court_2016_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(6, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey("UWAREZWE MU BUJURIRE"));
    }

    @Test
    public void high_court_chamber_nyanza_2014_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(7, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Keywords.UBUSHINJACYAHA));
    }

    @Test
    public void high_court_chamber_nyanza_2018_PartiesSectionHs_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(8, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Keywords.UBUSHINJACYAHA));
    }

    @Test
    public void high_court_criminal_2011_PartiesSectionHs_Prosecutor_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(9, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UWAJURIYE));
    }

    @Test
    public void interm_court_huye_2008_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(10, Headings.ABABURANYI, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void interm_court_huye_2015_PartiesSectionHas_USHINJWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(11, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.USHINJWA));
    }

    @Test
    public void interm_court_huye_2016_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(12, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void interm_court_huye_2018_226_PartiesSectionHas_UREGERA_INDISHYI_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey("UREGERA INDISHYI WAJURIYE"));
    }

    @Test
    public void interm_court_huye_2018_226_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, Headings.HABURANA, 2);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void interm_court_huye_2018_226_Partie_UREGWA_Has_Two_subsections() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(13, Headings.HABURANA, 2);
        JsonArray defendant = actualParty.getArrayByKey(Headings.UREGWA);
        assertEquals(1, defendant.getSize());
    }

    @Test
    public void interm_court_huye_2018_aff_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(14, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void primary_court_nzige_2011_rp0003_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(15, Headings.ABABURANYI, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void primary_court_nzige_2011_rp0020_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(16, Headings.ABABURANYI, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void supreme_court_comm_2009_PartiesSectionsHas_UREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(17, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void supreme_court_comm_2017_PartiesSectionsHas_ABAREGWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(18, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.ABAREGWA));
    }

    @Test
    public void comm_court_huye_2016_PartiesSectionHas_ABAGOBOKESHWA_subsection() {
        JsonObject actualParty = CasePartiesTestsHelpers.getActualPartiesSubsection(1, Headings.ABABURANA, 2);
        assertTrue(actualParty.hasKey(Headings.ABAGOBOKESHWA));
    }

    @Test
    public void comm_court_huye_2016_Parties_ABAGOBOKESHWA_subsection_should_have_two_elements() {
        JsonObject actualDefendant = CasePartiesTestsHelpers.getActualPartiesSubsection(1, Headings.ABABURANA, 2);
        JsonArray actualDefendantContent = actualDefendant.getArrayByKey(Headings.ABAGOBOKESHWA);

        assertEquals(2, actualDefendantContent.getSize());
    }

    @Test
    public void comm_court_huye_2011_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(0, 0, Headings.HABURANA, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_huye_2016_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 0, Headings.ABABURANA, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_huye_2018_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(2, 0, Headings.HABURANA, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_musanze_2011_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(3, 0, Headings.HABURANA, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_nyarugenge_2014_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(4, 0, Headings.HABURANA, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_nyarugenge_2016_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(5, 0, Headings.ABABURANA, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_high_court_2016_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(6, 0, Headings.HABURANA, Headings.UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void high_court_chamber_nyanza_2014_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(7, 0, Headings.HABURANA, Headings.UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void high_court_chamber_nyanza_2018_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(8, 0, Headings.HABURANA, Headings.UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void high_court_criminal_Parties_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(9, 0, Headings.HABURANA, Keywords.UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2008_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(10, 0, Headings.ABABURANYI, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2015_Parties_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(11, 0, Headings.HABURANA, Keywords.UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }


    @Test
    public void interm_court_huye_2016_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(12, 0, Headings.HABURANA, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2018_226_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 0, Headings.HABURANA, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2018_aff_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(14, 0, Headings.HABURANA, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void primary_court_nzige_2011_rp0003_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(15, 0, Headings.ABABURANYI, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void primary_court_nzige_2011_rp0020_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(16, 0, Headings.ABABURANYI, Headings.UREGA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void supreme_court_comm_2009_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(17, 0, Headings.HABURANA, Headings.UWAJURIYE);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void supreme_court_comm_2017_Parties_UREGA_mu_bujurire_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(18, 0, Headings.HABURANA, "UREGA mu bujurire");
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_huye_2011_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(0, 1, Headings.HABURANA, Headings.UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_huye_2016_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 1, Headings.ABABURANA, Headings.UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_huye_2018_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(2, 1, Headings.HABURANA, Headings.UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_musanze_2011_Parties_UREGWA_subsectionMatchExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(3, 1, Headings.HABURANA, Headings.UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_nyarugenge_2014_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(4, 1, Headings.HABURANA, Headings.UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_nyarugenge_2016_Parties_ABAREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(5, 1, Headings.ABABURANA, Headings.ABAREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_high_court_2016_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(6, 1,
                            Headings.HABURANA, "UWAREZWE MU BUJURIRE");
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void high_court_chamber_nyanza_2014_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(7, 1,
                Headings.HABURANA, Keywords.UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void high_court_chamber_nyanza_2018_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(8, 1,
                Headings.HABURANA, Keywords.UBUSHINJACYAHA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void high_court_criminal_2011_Prosecutor_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(9, 1,
                Headings.HABURANA, Headings.UWAJURIYE);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2015_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(11, 1, Headings.HABURANA, Headings.USHINJWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2008_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(10, 1, Headings.ABABURANYI, Headings.UREGWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2016_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(12, 1, Headings.HABURANA, Headings.UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2018_226_Parties_UREGERA_INDISHYI_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 1, Headings.HABURANA, "UREGERA INDISHYI WAJURIYE");
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2018_226_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 2, Headings.HABURANA, Headings.UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2018_aff_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(14, 1, Headings.HABURANA, Headings.UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void primary_court_nzige_2011_rp0003_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(15, 1, Headings.ABABURANYI, Headings.UREGWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void primary_court_nzige_2011_rp0020_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(16, 1, Headings.ABABURANYI, Headings.UREGWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void supreme_court_comm_2009_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(17, 1, Headings.HABURANA, Headings.UREGWA);
        CasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void supreme_court_comm_2017_Parties_ABAREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(18, 1, Headings.HABURANA, Headings.ABAREGWA);
    }

    @Test
    public void comm_court_huye_2016_Parties_ABAGOBOKESWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = CasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 2, Headings.ABABURANA, Headings.ABAGOBOKESHWA);
        CasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }
}

