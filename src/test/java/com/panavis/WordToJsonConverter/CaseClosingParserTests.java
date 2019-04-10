package com.panavis.WordToJsonConverter;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;
import static junit.framework.TestCase.assertEquals;

import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.BeforeClass;
import org.junit.Test;

public class CaseClosingParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    @Test
    public void case_000_hasExpectedClosingText() {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(0);
        Converter converter = TestsSetup.getConverterObject(wordDocument, CASE_CLOSING);
        converter.parseCaseSections();
        JsonObject actualCaseClosing = converter.getParsedCaseSection(CASE_CLOSING).getSectionContent();
        String actualClosingText = actualCaseClosing.getStringByKey(CASE_CLOSING);

        JsonObject expectedCaseObject = TestsSetup.expectedJsonContent.get(0);
        JsonArray caseArray = expectedCaseObject.getArrayByKey(CASE);
        JsonObject expectedCaseClosing = caseArray.getJsonByIndex(5);
        String expectedClosingText = expectedCaseClosing.getStringByKey(CASE_CLOSING);

        assertEquals(expectedClosingText, actualClosingText);
    }
}
