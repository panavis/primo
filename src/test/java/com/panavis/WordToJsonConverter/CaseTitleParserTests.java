package com.panavis.WordToJsonConverter;

import org.junit.BeforeClass;
import org.junit.Test;

public class CaseTitleParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
        CaseTitleTestsHelpers.setupActualAndExpectedCaseTitles();
    }

    @Test
    public void comm_court_huye_2011_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(0);
    }

    @Test
    public void comm_court_huye_2016_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(1);
    }

    @Test
    public void comm_court_huye_2018_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(2);
    }

    @Test
    public void comm_court_musanze_2011_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(3);
    }

    @Test
    public void comm_court_nyarugenge_2014_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(4);
    }

    @Test
    public void comm_court_nyarugenge_2016_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(5);
    }

    @Test
    public void comm_high_court_2016_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(6);
    }

    @Test
    public void high_court_chamber_nyanza_2014_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(7);
    }

    @Test
    public void high_court_chamber_nyanza_2018_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(8);
    }

    @Test
    public void high_court_criminal_2011_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(9);
    }

    @Test
    public void interm_court_huye_2008_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(10);
    }

    @Test
    public void interm_court_huye_2015_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(11);
    }

    @Test
    public void interm_court_huye_2016_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(12);
    }

    @Test
    public void interm_court_huye_2018_226_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(13);
    }
    @Test
    public void interm_court_huye_2018_aff_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(14);
    }

    @Test
    public void primary_court_nzige_2011_rp0003_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(15);
    }

    @Test
    public void primary_court_nzige_2011_rp0020_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(16);
    }

    @Test
    public void supreme_court_comm_2009_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(17);
    }

    @Test
    public void supreme_court_comm_2017_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(18);
    }

    @Test
    public void supreme_court_criminal_2018_caseHasExpectedTitle() {
        CaseTitleTestsHelpers.assertActualTitleMatchesExactlyExpectedTitle(19);
    }
}