package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CasePanelParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    private JsonArray getCasePanelArray() {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(4);
        Converter converter = TestsSetup.getConverterObject(wordDocument, INTEKO);
        converter.parseCaseSections();
        JsonObject actualCasePanel = converter.getParsedCaseSection(INTEKO).getSectionContent();
        return actualCasePanel.getArrayByKey(INTEKO);
    }

    private JsonArray getExpectedCasePanel() {
        JsonObject expectedCase = TestsSetup.expectedJsonContent.get(4);
        JsonObject expectedCasePanel = expectedCase.getArrayByKey(CASE).getJsonByIndex(6);
        return expectedCasePanel.getArrayByKey(INTEKO);
    }


    @Test
    public void case_004_shouldHaveTwoPeopleInPanelSection() {
        JsonArray casePanelArray = getCasePanelArray();
        assertEquals(2, casePanelArray.getSize());
    }

    @Test
    public void case_004_firstPanelistShouldBeJudge() {
        JsonObject firstPanelist = getCasePanelArray().getJsonByIndex(0);
        assertTrue(firstPanelist.hasKey("UMUCAMANZA"));
    }

    @Test
    public void case_004_firstPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray().getJsonByIndex(0);
        String actualName = firstPanelist.getStringByKey("UMUCAMANZA");

        JsonObject expectedPanelist = getExpectedCasePanel().getJsonByIndex(0);
        String expectedName = expectedPanelist.getStringByKey("UMUCAMANZA");
        assertEquals(expectedName, actualName);
    }

    @Test
    public void case_004_secondPanelistShouldBeWriter() {
        JsonObject firstPanelist = getCasePanelArray().getJsonByIndex(1);
        assertTrue(firstPanelist.hasKey("UMWANDITSI"));
    }

    @Test
    public void case_004_secondPanelistShouldHaveExpectedName() {
        JsonObject firstPanelist = getCasePanelArray().getJsonByIndex(1);
        String actualName = firstPanelist.getStringByKey("UMWANDITSI");

        JsonObject expectedPanelist = getExpectedCasePanel().getJsonByIndex(1);
        String expectedName = expectedPanelist.getStringByKey("UMWANDITSI");
        assertEquals(expectedName, actualName);
    }

}
