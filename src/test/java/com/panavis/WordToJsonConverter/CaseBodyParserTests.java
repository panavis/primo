package com.panavis.WordToJsonConverter;

import com.panavis.WordToJsonConverter.Constants.Keywords;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class CaseBodyParserTests {

    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    @Ignore
    @Test
    public void primary_court_0020_hasImiterereYurubanzaSection() {
        XWPFDocument wordDocument = TestsSetup.wordDocxData.get(16);
        Converter converter = TestsSetup.getConverterObject(wordDocument, Keywords.CASE_BODY);
        converter.parseCaseSections();
        SectionResult caseBodyResult = converter.getParsedCaseSection(Keywords.CASE_BODY);
        JsonObject caseBody = caseBodyResult.getSectionContent();
        assertTrue(caseBody.hasKey("I.\tIMITERERE Y'URUBANZA"));
    }

}
