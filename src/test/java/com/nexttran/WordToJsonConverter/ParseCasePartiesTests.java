package com.nexttran.WordToJsonConverter;

import com.nexttran.WordToJsonConverter.Constants.Headings;
import com.nexttran.WordToJsonConverter.Wrappers.JsonArray;
import com.nexttran.WordToJsonConverter.Wrappers.JsonObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParseCasePartiesTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    @Test
    public void comm_court_huye_2011_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(0, Headings.HABURANA);
    }

    @Test
    public void comm_court_huye_2016_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(1, Headings.ABABURANA);
    }

    @Test
    public void comm_court_huye_2018_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(2, Headings.HABURANA);
    }

    @Test
    public void comm_court_musanze_2011_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(3, Headings.HABURANA);
    }

    @Test
    public void comm_court_nyarugenge_2014_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(4, Headings.HABURANA);
    }

    @Test
    public void comm_court_nyarugenge_2016_PartiesSectionHas_ABABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(5, Headings.ABABURANA);
    }

    @Test
    public void comm_high_court_2016_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(6, Headings.HABURANA);
    }

    @Test
    public void interm_court_huye_2008_PartiesSectionHas_ABABURANYI_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(10, Headings.ABABURANYI);
    }

    @Test
    public void interm_court_huye_2016_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(12, Headings.HABURANA);
    }

    @Test
    public void interm_court_huye_2018_226_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(13, Headings.HABURANA);
    }
    @Test
    public void interm_court_huye_2018_aff_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(14, Headings.HABURANA);
    }

    @Test
    public void primary_court_nzige_2011_rp0003_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(15, Headings.ABABURANYI);
    }

    @Test
    public void primary_court_nzige_2011_rp0020_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(16, Headings.ABABURANYI);
    }

    @Test
    public void supreme_court_comm_2009_PartiesSectionsHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(17, Headings.HABURANA);
    }

    @Test
    public void supreme_court_comm_2017_PartiesSectionHas_HABURANA_asJsonKey() {
        ParseCasePartiesTestsHelpers.assertJsonContainsPartiesSection(18, Headings.HABURANA);
    }

    @Test
    public void comm_court_huye_2011_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(0, Headings.HABURANA, 2);
    }

    @Test
    public void comm_court_huye_2016_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(1, Headings.ABABURANA, 3);
    }

    @Test
    public void comm_court_huye_2018_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(2, Headings.HABURANA, 2);
    }

    @Test
    public void comm_court_musanze_2011_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(3, Headings.HABURANA, 2);
    }

    @Test
    public void comm_court_nyarugenge_2014_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(4, Headings.HABURANA, 2);
    }

    @Test
    public void comm_court_nyarugenge_2016_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(5, Headings.ABABURANA, 2);
    }

    @Test
    public void comm_high_court_2016_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(6, Headings.HABURANA, 2);
    }

    @Test
    public void interm_court_huye_2008_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(10, Headings.ABABURANYI, 2);
    }

    @Test
    public void interm_court_huye_2016_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(12, Headings.HABURANA, 2);
    }

    @Test
    public void interm_court_huye_2018_226_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(13, Headings.HABURANA, 3);
    }

    @Test
    public void interm_court_huye_2018_aff_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(14, Headings.HABURANA, 2);
    }

    @Test
    public void primary_court_nzige_2011_rp0003_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(15, Headings.ABABURANYI, 2);
    }

    @Test
    public void primary_court_nzige_2011_rp0020_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(16, Headings.ABABURANYI, 2);
    }

    @Test
    public void supreme_court_comm_2009_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(17, Headings.HABURANA, 2);
    }

    @Test
    public void supreme_court_comm_2017_PartiesSectionHasTwoSubsections() {
        ParseCasePartiesTestsHelpers.assertCorrectNumberOfSubsections(18, Headings.HABURANA, 2);
    }

    @Test
    public void comm_court_huye_2011_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(0, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_court_huye_2016_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(1, Headings.ABABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_court_huye_2018_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(2, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_court_musanze_2011_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(3, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_court_nyarugenge_2014_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(4, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_court_nyarugenge_2016_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(5, Headings.ABABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void comm_high_court_2016_PartiesSectionHas_UWAJURIYE_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(6, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UWAJURIYE));
    }

    @Test
    public void interm_court_huye_2008_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(10, Headings.ABABURANYI, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }
    @Test
    public void interm_court_huye_2016_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(12, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void interm_court_huye_2018_226_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(13, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void interm_court_huye_2018_aff_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(14, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void primary_court_nzige_2011_rp0003_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(15, Headings.ABABURANYI, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void primary_court_nzige_2011_rp0020_PartiesSectionHas_UREGA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(16, Headings.ABABURANYI, 0);
        assertTrue(actualParty.hasKey(Headings.UREGA));
    }

    @Test
    public void supreme_court_comm_2009_PartiesSectionsHas_UWAJURIYE_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(17, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey(Headings.UWAJURIYE));
    }

    @Test
    public void supreme_court_comm_2017_PartiesSectionsHas_UREGA_mu_bujurire_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(18, Headings.HABURANA, 0);
        assertTrue(actualParty.hasKey("UREGA mu bujurire"));
    }

    @Test
    public void comm_court_huye_2011_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(0, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void comm_court_huye_2016_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(1, Headings.ABABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void comm_court_huye_2018_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(2, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void comm_court_musanze_2011_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(3, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void comm_court_nyarugenge_2014_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(4, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void comm_court_nyarugenge_2016_PartiesSectionHas_ABAREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(5, Headings.ABABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.ABAREGWA));
    }

    @Test
    public void comm_high_court_2016_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(6, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey("UWAREZWE MU BUJURIRE"));
    }

    @Test
    public void interm_court_huye_2008_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(10, Headings.ABABURANYI, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void interm_court_huye_2016_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(12, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void interm_court_huye_2018_226_PartiesSectionHas_UREGERA_INDISHYI_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(13, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey("UREGERA INDISHYI WAJURIYE"));
    }

    @Test
    public void interm_court_huye_2018_226_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(13, Headings.HABURANA, 2);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void interm_court_huye_2018_226_Partie_UREGWA_Has_Two_subsections() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(13, Headings.HABURANA, 2);
        JsonArray defendant = actualParty.getArrayByKey(Headings.UREGWA);
        assertEquals(2, defendant.getSize());
    }

    @Test
    public void interm_court_huye_2018_aff_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(14, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void primary_court_nzige_2011_rp0003_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(15, Headings.ABABURANYI, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void primary_court_nzige_2011_rp0020_PartiesSectionHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(16, Headings.ABABURANYI, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void supreme_court_comm_2009_PartiesSectionsHas_UREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(17, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.UREGWA));
    }

    @Test
    public void supreme_court_comm_2017_PartiesSectionsHas_ABAREGWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(18, Headings.HABURANA, 1);
        assertTrue(actualParty.hasKey(Headings.ABAREGWA));
    }

    @Test
    public void comm_court_huye_2016_PartiesSectionHas_ABAGOBOKESHWA_subsection() {
        JsonObject actualParty = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(1, Headings.ABABURANA, 2);
        assertTrue(actualParty.hasKey(Headings.ABAGOBOKESHWA));
    }

    @Test
    public void comm_court_huye_2016_Parties_ABAGOBOKESHWA_subsection_should_have_two_elements() {
        JsonObject actualDefendant = ParseCasePartiesTestsHelpers.getActualPartiesSubsection(1, Headings.ABABURANA, 2);
        JsonArray actualDefendantContent = actualDefendant.getArrayByKey(Headings.ABAGOBOKESHWA);

        assertEquals(2, actualDefendantContent.getSize());
    }

    @Test
    public void comm_court_huye_2011_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(0, 0, Headings.HABURANA, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_huye_2016_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 0, Headings.ABABURANA, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_huye_2018_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(2, 0, Headings.HABURANA, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_musanze_2011_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(3, 0, Headings.HABURANA, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_nyarugenge_2014_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(4, 0, Headings.HABURANA, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_nyarugenge_2016_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(5, 0, Headings.ABABURANA, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_high_court_2016_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(6, 0, Headings.HABURANA, Headings.UWAJURIYE);
        ParseCasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2008_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(10, 0, Headings.ABABURANYI, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2016_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(12, 0, Headings.HABURANA, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent.toString());
    }

    @Test
    public void interm_court_huye_2018_226_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 0, Headings.HABURANA, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2018_aff_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(14, 0, Headings.HABURANA, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void primary_court_nzige_2011_rp0003_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(15, 0, Headings.ABABURANYI, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void primary_court_nzige_2011_rp0020_Parties_UREGA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(16, 0, Headings.ABABURANYI, Headings.UREGA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void supreme_court_comm_2009_Parties_UWAJURIYE_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(17, 0, Headings.HABURANA, Headings.UWAJURIYE);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void supreme_court_comm_2017_Parties_UREGA_mu_bujurire_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(18, 0, Headings.HABURANA, "UREGA mu bujurire");
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_huye_2011_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(0, 1, Headings.HABURANA, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_huye_2016_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 1, Headings.ABABURANA, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_huye_2018_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(2, 1, Headings.HABURANA, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_musanze_2011_Parties_UREGWA_subsectionMatchExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(3, 1, Headings.HABURANA, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_nyarugenge_2014_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(4, 1, Headings.HABURANA, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_court_nyarugenge_2016_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(5, 1, Headings.ABABURANA, Headings.ABAREGWA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void comm_high_court_2016_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(6, 1,
                            Headings.HABURANA, "UWAREZWE MU BUJURIRE");
        ParseCasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2008_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(10, 1, Headings.ABABURANYI, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2016_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(12, 1, Headings.HABURANA, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2018_226_Parties_UREGERA_INDISHYI_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 1, Headings.HABURANA, "UREGERA INDISHYI WAJURIYE");
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2018_226_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(13, 2, Headings.HABURANA, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void interm_court_huye_2018_aff_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(14, 1, Headings.HABURANA, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void primary_court_nzige_2011_rp0003_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(15, 1, Headings.ABABURANYI, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void primary_court_nzige_2011_rp0020_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(16, 1, Headings.ABABURANYI, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void supreme_court_comm_2009_Parties_UREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(17, 1, Headings.HABURANA, Headings.UREGWA);
        ParseCasePartiesTestsHelpers.assertExactContentEquals(content.expectedContent, content.actualContent);
    }

    @Test
    public void supreme_court_comm_2017_Parties_ABAREGWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(18, 1, Headings.HABURANA, Headings.ABAREGWA);
    }

    @Test
    public void comm_court_huye_2016_Parties_ABAGOBOKESWA_subsectionMatchesExpectedContent() {
        ExpectedActualContent content = ParseCasePartiesTestsHelpers.getExpectedAndActualContentForSubsection(1, 2, Headings.ABABURANA, Headings.ABAGOBOKESHWA);
        ParseCasePartiesTestsHelpers.assertContentLengthEquals(content.expectedContent, content.actualContent);
    }
}

