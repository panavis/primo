package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.junit.*;

import static junit.framework.TestCase.*;

public class CasePanelParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
        CasePanelParserHelpers.setUpAllActualAndExpectedJsons();
    }

    @Ignore("Ignore: Panel section Troubleshooter")
    @Test
    public void troubleShootOneCaseSeparately() {
        JsonArray panelArray = CasePanelParserHelpers.getActualCasePanelArray(14);
    }

    @Test
    public void case_000_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(0);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_000_firstPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(0, 0);
        assertTrue(panelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_000_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(0, 0);
        String actualName = panelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(0, 0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_000_secondPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(0, 1);
        assertTrue(panelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_000_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(0, 1);
        String actualName = panelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(0, 1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_001_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(1);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_001_firstPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(1, 0);
        assertTrue(panelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_001_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(1, 0);
        String actualName = panelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(1, 0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_001_secondPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(1, 1);
        assertTrue(panelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_001_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(1, 1);
        String actualName = panelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(1, 1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_002_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(2);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_002_firstPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(2, 0);
        assertTrue(panelist.hasKey("UMUCAMANZA:"));
    }

    @Test
    public void case_002_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(2, 0);
        String actualName = panelist.getStringByKey("UMUCAMANZA:");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(2, 0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA:");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_002_secondPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(2, 1);
        assertTrue(panelist.hasKey("UMWANDITSI:"));
    }

    @Test
    public void case_002_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(2, 1);
        String actualName = panelist.getStringByKey("UMWANDITSI:");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(2, 1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI:");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_004_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(4);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_004_firstPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(4, 0);
        assertTrue(panelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_004_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(4, 0);
        String actualName = panelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(4, 0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_004_secondPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(4, 1);
        assertTrue(panelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_004_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(4, 1);
        String actualName = panelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(4, 1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_005_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(5);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_005_firstPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(5, 0);
        assertTrue(panelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_005_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(5, 0);
        String actualName = panelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(5, 0);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_005_secondPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(5, 1);
        assertTrue(panelist.hasKey("Umwanditsi"));
    }

    @Test
    public void case_005_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(5, 1);
        String actualName = panelist.getStringByKey("Umwanditsi");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(5, 1);
        String expectedName = expectedPanelist.getStringByKey("Umwanditsi");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_006_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(6);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_006_firstPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(6, 0);
        assertTrue(panelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_006_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(6, 0);
        String actualName = panelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(6, 0);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_006_secondPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(6, 1);
        assertTrue(panelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_006_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(6, 1);
        String actualName = panelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(6, 1);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_007_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(7);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_007_firstPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(7, 0);
        assertTrue(panelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_007_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(7, 0);
        String actualName = panelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(7, 0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_007_secondPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(7, 1);
        assertTrue(panelist.hasKey("UMWANDITSI W\u2019URUKIKO"));
    }

    @Test
    public void case_007_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(7, 1);
        String actualName = panelist.getStringByKey("UMWANDITSI W\u2019URUKIKO");

        JsonObject expectedPanelist = CasePanelParserHelpers.getActualPanelistObject(7, 1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI W\u2019URUKIKO");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_008_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(8);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_008_firstPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(8, 0);
        assertTrue(panelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_008_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(8, 0);
        String actualName = panelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(8, 0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_008_secondPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(8, 1);
        assertTrue(panelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_008_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(8, 1);
        String actualName = panelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(8, 1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_011_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(11);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_011_firstPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(11, 0);
        assertTrue(panelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_011_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(11, 0);
        String actualName = panelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(11, 0);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_011_secondPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(11, 1);
        assertTrue(panelist.hasKey("Umwanditsi"));
    }

    @Test
    public void case_011_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(11, 1);
        String actualName = panelist.getStringByKey("Umwanditsi");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(11, 1);
        String expectedName = expectedPanelist.getStringByKey("Umwanditsi");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_013_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(13);
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_013_firstPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(13, 0);
        assertTrue(panelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_013_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(13, 0);
        String actualName = panelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(13, 0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_013_secondPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(13, 1);
        assertTrue(panelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_013_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(13, 1);
        String actualName = panelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(13, 1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_017_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(17);
        assertEquals(4, casePanelArray.getSize());
    }

    @Test
    public void case_017_firstPanelistShouldBePresident() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(17, 0);
        assertTrue(panelist.hasKey("Perezida"));
    }

    @Test
    public void case_017_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(17, 0);
        String actualName = panelist.getStringByKey("Perezida");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(17, 0);
        String expectedName = expectedPanelist.getStringByKey("Perezida");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_017_secondPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(17, 1);
        assertTrue(panelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_017_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(17, 1);
        String actualName = panelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(17, 1);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_017_thirdPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(17, 2);
        assertTrue(panelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_017_thirdPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(17, 2);
        String actualName = panelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(17, 2);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_017_fourthPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(17, 3);
        assertTrue(panelist.hasKey("Umwanditsi"));
    }

    @Test
    public void case_017_fourthPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(17, 3);
        String actualName = panelist.getStringByKey("Umwanditsi");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(17, 3);
        String expectedName = expectedPanelist.getStringByKey("Umwanditsi");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_019_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(19);
        assertEquals(4, casePanelArray.getSize());
    }

    @Test
    public void case_019_firstPanelistShouldBePresident() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(19, 0);
        assertTrue(panelist.hasKey("Perezida"));
    }

    @Test
    public void case_019_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(19, 0);
        String actualName = panelist.getStringByKey("Perezida");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(19, 0);
        String expectedName = expectedPanelist.getStringByKey("Perezida");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_019_secondPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(19, 1);
        assertTrue(panelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_019_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(19, 1);
        String actualName = panelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(19, 1);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_019_thirdPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(19, 2);
        assertTrue(panelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_019_thirdPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(19, 2);
        String actualName = panelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(19, 2);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_019_fourthPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(19, 3);
        assertTrue(panelist.hasKey("Umwanditsi w\u2018Urukiko"));
    }

    @Test
    public void case_019_fourthPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(19, 3);
        String actualName = panelist.getStringByKey("Umwanditsi w\u2018Urukiko");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(19, 3);
        String expectedName = expectedPanelist.getStringByKey("Umwanditsi w\u2019Urukiko");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_020_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(20);
        assertEquals(4, casePanelArray.getSize());
    }

    @Test
    public void case_020_firstPanelistShouldBePresident() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(20, 0);
        assertTrue(panelist.hasKey("Perezida"));
    }

    @Test
    public void case_020_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(20, 0);
        String actualName = panelist.getStringByKey("Perezida");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(20, 0);
        String expectedName = expectedPanelist.getStringByKey("Perezida");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_020_secondPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(20, 1);
        assertTrue(panelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_020_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(20, 1);
        String actualName = panelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(20, 1);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_020_thirdPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(20, 2);
        assertTrue(panelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_020_thirdPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(20, 2);
        String actualName = panelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(20, 2);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_020_fourthPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(20, 3);
        assertTrue(panelist.hasKey("Umwanditsi w\u2019Urukiko"));
    }

    @Test
    public void case_020_fourthPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(20, 3);
        String actualName = panelist.getStringByKey("Umwanditsi w\u2019Urukiko");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(20, 3);
        String expectedName = expectedPanelist.getStringByKey("Umwanditsi w\u2019Urukiko");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_021_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = CasePanelParserHelpers.getActualCasePanelArray(21);
        assertEquals(4, casePanelArray.getSize());
    }

    @Test
    public void case_021_firstPanelistShouldBePresident() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(21, 0);
        assertTrue(panelist.hasKey("Perezida"));
    }

    @Test
    public void case_021_firstPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(21, 0);
        String actualName = panelist.getStringByKey("Perezida");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(21, 0);
        String expectedName = expectedPanelist.getStringByKey("Perezida");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_021_secondPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(21, 1);
        assertTrue(panelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_021_secondPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(21, 1);
        String actualName = panelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(21, 1);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_021_thirdPanelistShouldBeJudge() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(21, 2);
        assertTrue(panelist.hasKey("Umucamanza"));
    }

    @Test
    public void case_021_thirdPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(21, 2);
        String actualName = panelist.getStringByKey("Umucamanza");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(21, 2);
        String expectedName = expectedPanelist.getStringByKey("Umucamanza");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_021_fourthPanelistShouldBeWriter() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(21, 3);
        assertTrue(panelist.hasKey("Umwanditsi w\u2019Urukiko"));
    }

    @Test
    public void case_021_fourthPanelistShouldHaveExpectedName() {
        JsonObject panelist = CasePanelParserHelpers.getActualPanelistObject(21, 3);
        String actualName = panelist.getStringByKey("Umwanditsi w\u2019Urukiko");

        JsonObject expectedPanelist = CasePanelParserHelpers.getExpectedPanelistObject(21, 3);
        String expectedName = expectedPanelist.getStringByKey("Umwanditsi w\u2019Urukiko");
        assertEquals(expectedName, actualName);
    }
}
