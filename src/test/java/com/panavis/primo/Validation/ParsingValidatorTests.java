package com.panavis.primo.Validation;

import static com.panavis.primo.Constants.Keywords.*;

import com.panavis.primo.Constants.Headings;
import com.panavis.primo.Parsers.ParsedCase;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ParsingValidatorTests {

    private ParsedCase parsedCase;

    @Before
    public void setUp() {
        parsedCase = new ParsedCase();
    }

    private JsonObject getSubsectionJson(String heading, String content) {
        JsonArray subsectionArray = new JsonArray();
        subsectionArray.putValue(content);
        JsonObject subsectionJson = new JsonObject();
        subsectionJson.addNameValuePair(heading, subsectionArray);
        return subsectionJson;
    }

    private JsonArray getArrayWithSubsectionNtimes(JsonObject subsection, int times) {
        JsonArray sectionArray = new JsonArray();
        IntStream.range(0, times).forEach(i -> sectionArray.putValue(subsection));
        return sectionArray;
    }

    private JsonObject createJsonWithEmptyArray(String caseBody) {
        JsonObject caseBodyJson = new JsonObject();
        caseBodyJson.addNameValuePair(caseBody, new JsonArray());
        return caseBodyJson;
    }

    @Test
    public void caseMissingTitleSectionIsNotValid() {
        ParsingValidator validator = new ParsingValidator(parsedCase);
        assertFalse(validator.isTitleValid());
    }

    @Test
    public void caseWithEmptyTitleIsNotValid() {
        JsonObject titleJson = createJsonWithText(TITLE, "");
        SectionResult titleResult = new SectionResult(titleJson, 0);
        parsedCase.set(TITLE, titleResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isTitleValid());
    }

    private JsonObject createJsonWithText(String title, String s) {
        JsonObject titleJson = new JsonObject();
        titleJson.addNameValuePair(title, s);
        return titleJson;
    }

    @Test
    public void titleSectionIsValidWhenNonEmpty() {
        JsonObject titleJson = createJsonWithText(TITLE, "Some title");
        SectionResult titleResult = new SectionResult(titleJson, 0);
        parsedCase.set(TITLE, titleResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertTrue(validator.isTitleValid());
    }

    @Test
    public void caseMissingPartiesSectionIsNotValid() {
        ParsingValidator validator = new ParsingValidator(parsedCase);
        assertFalse(validator.arePartiesValid());
    }

    @Test
    public void caseWithEmptyPartiesIsNotValid() {
        JsonObject partiesJson = createJsonWithEmptyArray("HABURANA");
        SectionResult partiesResult = new SectionResult(partiesJson, 0);
        parsedCase.set(PARTIES, partiesResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.arePartiesValid());
    }

    @Test
    public void caseWithOnePartyIsNotValid() {
        JsonObject subsectionJson = getSubsectionJson("SUBHEADING", "subsection Content");
        JsonArray sectionArray = getArrayWithSubsectionNtimes(subsectionJson, 1);
        JsonObject partiesJson = new JsonObject();
        partiesJson.addNameValuePair(Headings.HABURANA, sectionArray);
        SectionResult partiesResult = new SectionResult(partiesJson, 0);
        parsedCase.set(PARTIES, partiesResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.arePartiesValid());
    }

    @Test
    public void caseWithAtLeastTwoNonEmptySubsectionsIsValid() {
        JsonObject subsectionJson = getSubsectionJson("SUBHEADING", "some actual content");
        JsonArray sectionArray = getArrayWithSubsectionNtimes(subsectionJson, 2);
        JsonObject partiesJson = new JsonObject();
        partiesJson.addNameValuePair(Headings.HABURANA, sectionArray);
        SectionResult partiesResult = new SectionResult(partiesJson, 0);
        parsedCase.set(PARTIES, partiesResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertTrue(validator.arePartiesValid());
    }

    @Test
    public void caseWithOnePartyAndIkiregoKeywordInHeadingIsValid() {
        // E.g. "Uwakatanze ikirego:"
        JsonObject subsectionJson = getSubsectionJson("Ikirego", "subsection Content");
        JsonArray sectionArray = getArrayWithSubsectionNtimes(subsectionJson, 1);
        JsonObject partiesJson = new JsonObject();
        partiesJson.addNameValuePair(Headings.HABURANA, sectionArray);
        SectionResult partiesResult = new SectionResult(partiesJson, 0);
        parsedCase.set(PARTIES, partiesResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertTrue(validator.arePartiesValid());
    }

    @Test
    public void caseWithEmptySectionIsNotValid() {
        JsonObject subsectionJson = getSubsectionJson("SUBHEADING", "");
        JsonArray sectionArray = getArrayWithSubsectionNtimes(subsectionJson, 2);
        JsonObject partiesJson = new JsonObject();
        partiesJson.addNameValuePair(Headings.HABURANA, sectionArray);
        SectionResult partiesResult = new SectionResult(partiesJson, 0);
        parsedCase.set(PARTIES, partiesResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.arePartiesValid());
    }

    @Test
    public void caseMissingSubjectMatterSectionIsNotValid() {
        ParsingValidator validator = new ParsingValidator(parsedCase);
        assertFalse(validator.isSubjectMatterValid());
    }

    @Test
    public void caseWithEmptySubjectMatterIsNotValid() {
        JsonObject subjectMatterJson = createJsonWithEmptyArray(SUBJECT_MATTER);
        SectionResult subjectMatterResult = new SectionResult(subjectMatterJson, 0);
        parsedCase.set(SUBJECT_MATTER, subjectMatterResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isSubjectMatterValid());
    }

    @Test
    public void subjectMatterSectionWithSubheadingAndEmptyArrayIsNotValid() {
        JsonObject subjectMatterContent = createJsonWithEmptyArray("SUBHEADING");
        JsonArray subjectMatterArray = getArrayWithSubsectionNtimes(subjectMatterContent, 1);
        JsonObject subjectMatterJson = new JsonObject();
        subjectMatterJson.addNameValuePair(SUBJECT_MATTER, subjectMatterArray);
        SectionResult subjectMatterResult = new SectionResult(subjectMatterJson, 0);
        parsedCase.set(SUBJECT_MATTER, subjectMatterResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isSubjectMatterValid());
    }

    @Test
    public void subjectMatterSectionWithSubheadingAndEmptyStringIsNotValid() {
        JsonObject subjectMatterContent = getSubsectionJson("SUBHEADING", "");
        JsonArray subjectMatterArray = getArrayWithSubsectionNtimes(subjectMatterContent, 1);
        JsonObject subjectMatterJson = new JsonObject();
        subjectMatterJson.addNameValuePair(SUBJECT_MATTER, subjectMatterArray);
        SectionResult subjectMatterResult = new SectionResult(subjectMatterJson, 0);
        parsedCase.set(SUBJECT_MATTER, subjectMatterResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isSubjectMatterValid());
    }

    @Test
    public void subjectMatterSectionWithOneSubsectionIsNotValid() {
        JsonObject subjectMatterContent = getSubsectionJson("SUBHEADING", "What the subject matter is");
        JsonArray subjectMatterArray = getArrayWithSubsectionNtimes(subjectMatterContent, 1);
        JsonObject subjectMatterJson = new JsonObject();
        subjectMatterJson.addNameValuePair(SUBJECT_MATTER, subjectMatterArray);
        SectionResult subjectMatterResult = new SectionResult(subjectMatterJson, 0);
        parsedCase.set(SUBJECT_MATTER, subjectMatterResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertTrue(validator.isSubjectMatterValid());
    }

    @Test
    public void caseMissingCaseBodySectionIsNotValid() {
        ParsingValidator validator = new ParsingValidator(parsedCase);
        assertFalse(validator.isCaseBodyValid());
    }

    @Test
    public void caseWithEmptyCaseBodyIsNotValid() {
        JsonObject caseBodyJson = createJsonWithEmptyArray(CASE_BODY);
        SectionResult caseBodyResult = new SectionResult(caseBodyJson, 0);
        parsedCase.set(CASE_BODY, caseBodyResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isCaseBodyValid());
    }

    @Test
    public void caseWithNewFormatAndOneCaseBodySubsectionIsNotValid() {
        JsonObject subsection = getSubsectionJson("Imiterere y'urubanza", "some content");
        JsonArray bodyArray = getArrayWithSubsectionNtimes(subsection, 1);
        JsonObject caseBodyJson = new JsonObject();
        caseBodyJson.addNameValuePair(CASE_BODY, bodyArray);
        SectionResult caseBodyResult = new SectionResult(caseBodyJson, 0);
        parsedCase.set(CASE_BODY, caseBodyResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isCaseBodyValid());

    }

    @Test
    public void caseWithOldFormatAndOneCaseBodySubsectionIsValid() {
        JsonObject subsection = getSubsectionJson("Urukiko", "some content");
        JsonArray bodyArray = getArrayWithSubsectionNtimes(subsection, 1);
        JsonObject caseBodyJson = new JsonObject();
        caseBodyJson.addNameValuePair(CASE_BODY, bodyArray);
        SectionResult caseBodyResult = new SectionResult(caseBodyJson, 0);
        parsedCase.set(CASE_BODY, caseBodyResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertTrue(validator.isCaseBodyValid());
    }

    @Test
    public void caseBodySectionWithThreeSubsectionsWithActualContentIstValid() {
        JsonObject subsection = getSubsectionJson("CASE_BACKGROUND", "some content");
        JsonArray bodyArray = getArrayWithSubsectionNtimes(subsection, 3);
        JsonObject caseBodyJson = new JsonObject();
        caseBodyJson.addNameValuePair(CASE_BODY, bodyArray);
        SectionResult caseBodyResult = new SectionResult(caseBodyJson, 0);
        parsedCase.set(CASE_BODY, caseBodyResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertTrue(validator.isCaseBodyValid());
    }

    @Test
    public void caseBodySectionTwoSectionsIsValidIfJudgeDecisionIsLastSection() {
        JsonObject subsection = getSubsectionJson("CASE_BACKGROUND", "some content");
        JsonArray bodyArray = getArrayWithSubsectionNtimes(subsection, 1);
        JsonObject lastSubsection = getSubsectionJson("ICYEMEZO CY'URUKIKO", "some content");
        bodyArray.putValue(lastSubsection);

        JsonObject caseBodyJson = new JsonObject();
        caseBodyJson.addNameValuePair(CASE_BODY, bodyArray);
        SectionResult caseBodyResult = new SectionResult(caseBodyJson, 0);
        parsedCase.set(CASE_BODY, caseBodyResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertTrue(validator.isCaseBodyValid());
    }

    @Test
    public void caseMissingCasePanelSectionIsNotValid() {
        ParsingValidator validator = new ParsingValidator(parsedCase);
        assertFalse(validator.isCasePanelValid());
    }

    @Test
    public void caseWithEmptyCasePanelIsNotValid() {
        JsonObject casePanelJson = createJsonWithEmptyArray(INTEKO);
        SectionResult casePanelResult = new SectionResult(casePanelJson, 0);
        parsedCase.set(INTEKO, casePanelResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isCasePanelValid());
    }

    @Test
    public void caseWithOnePanelistIsNotValid() {
        JsonObject panelist = createJsonWithText("title", "full name");
        JsonArray panelArray = getArrayWithSubsectionNtimes(panelist, 1);
        JsonObject casePanelJson = new JsonObject();
        casePanelJson.addNameValuePair(INTEKO, panelArray);
        SectionResult casePanelResult = new SectionResult(casePanelJson, 0);
        parsedCase.set(INTEKO, casePanelResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isCasePanelValid());
    }

    @Test
    public void caseWithAtLeastTwoPanelistsAndNonEmptyTileAndNameIsValid() {
        JsonObject panelist = createJsonWithText("title", "full name");
        JsonArray panelArray = getArrayWithSubsectionNtimes(panelist, 2);
        JsonObject casePanelJson = new JsonObject();
        casePanelJson.addNameValuePair(INTEKO, panelArray);
        SectionResult casePanelResult = new SectionResult(casePanelJson, 0);
        parsedCase.set(INTEKO, casePanelResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertTrue(validator.isCasePanelValid());
    }

    @Test
    public void caseWithWithTwoTitleNamePairsInOneJsonObjectIsNottValid() {
        JsonObject panelist = createJsonWithText("title", "full name");
        panelist.addNameValuePair("titleTwo", "full name"); // Two key/isValid pairs
        JsonArray panelArray = getArrayWithSubsectionNtimes(panelist, 2);
        JsonObject casePanelJson = new JsonObject();
        casePanelJson.addNameValuePair(INTEKO, panelArray);
        SectionResult casePanelResult = new SectionResult(casePanelJson, 0);
        parsedCase.set(INTEKO, casePanelResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isCasePanelValid());
    }

    @Test
    public void caseIsNotValidIfParserSkippedSomeParagraphs() {
        parsedCase.setSkippedParagraphs(true);
        Validator mockValidator = new MockParsingValidator(parsedCase);
        assertFalse(mockValidator.isParsedCaseValid());
    }

    @Test
    public void entireCaseIsValidIfRequiredSectionsAreValidAndDidNotSkipParagraphs() {
        parsedCase.setSkippedParagraphs(false);
        Validator mockValidator = new MockParsingValidator(parsedCase);
        assertTrue(mockValidator.isParsedCaseValid());
    }
}

class MockParsingValidator extends Validator {

    MockParsingValidator(ParsedCase parsedCase) {
        super(parsedCase);
    }

    public boolean isTitleValid() {
        return true;
    }

    public boolean arePartiesValid() {
        return true;
    }

    public boolean isSubjectMatterValid() {
        return true;
    }

    public boolean isCaseBodyValid() {
        return true;
    }

    public boolean isCasePanelValid() {
        return true;
    }
}