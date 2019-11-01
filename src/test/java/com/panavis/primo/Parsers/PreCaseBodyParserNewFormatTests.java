package com.panavis.primo.Parsers;

import com.panavis.primo.Primo;
import com.panavis.primo.TestsSetup;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static com.panavis.primo.Constants.Keywords.PRE_CASE_BODY;

public class PreCaseBodyParserNewFormatTests {


    @BeforeClass
    public static void setUp() {
        TestsSetup.setUp();
    }

    @Ignore("Skip: identify pre_case_body start or end of subjectMatter")
    @Test
    public void case_035_preCaseBodyHasExpectedContent() {
        String wordFilePath = TestsSetup.wordFilePaths.get(35);
        Primo primo = TestsSetup.getConverterObject(wordFilePath, PRE_CASE_BODY);
        primo.parseCaseSections();
        JsonObject actualCaseClosing = primo.getParsedCaseSection(PRE_CASE_BODY).getSectionContent();
        JsonArray preCaseBodyContent = actualCaseClosing.getArrayByKey(PRE_CASE_BODY);
        System.out.println(preCaseBodyContent);
    }
}
