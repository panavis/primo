package com.panavis.WordToJsonConverter;

import static com.panavis.WordToJsonConverter.Constants.Keywords.*;

import com.panavis.WordToJsonConverter.Constants.Headings;
import com.panavis.WordToJsonConverter.ResultTypes.SectionResult;
import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParsingValidatorTests {

    private ParsedCase parsedCase;

    @Before
    public void setUp() {
        parsedCase = new ParsedCase();
    }

    @Test
    public void caseMissingTitleSectionIsNotValid() {
        ParsingValidator validator = new ParsingValidator(parsedCase);
        assertFalse(validator.isTitleValid());
    }

    @Test
    public void caseWithEmptyTitleIsNotValid() {
        JsonObject titleJson = new JsonObject();
        titleJson.addNameValuePair(TITLE, "");
        SectionResult titleResult = new SectionResult(titleJson, 0);
        parsedCase.set(TITLE, titleResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isTitleValid());
    }

    @Test
    public void titleSectionIsValidWhenNonEmpty() {
        JsonObject titleJson = new JsonObject();
        titleJson.addNameValuePair(TITLE, "Some title");
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
        JsonObject partiesJson = new JsonObject();
        partiesJson.addNameValuePair("HABURANA", new JsonArray());
        SectionResult partiesResult = new SectionResult(partiesJson, 0);
        parsedCase.set(PARTIES, partiesResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.arePartiesValid());
    }

    @Test
    public void caseWithOnePartyIsNotValid() {
        JsonObject partiesJson = new JsonObject();
        JsonArray partiesArray = new JsonArray();
        partiesArray.putValue("Just one party");
        partiesJson.addNameValuePair("HABURANA", partiesArray);
        SectionResult partiesResult = new SectionResult(partiesJson, 0);
        parsedCase.set(PARTIES, partiesResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.arePartiesValid());
    }

    @Test
    public void partiesSectionWithTwoOrMorePartiesIsValid() {
        JsonObject partiesJson = new JsonObject();
        JsonArray partiesArray = new JsonArray();
        partiesArray.putValue("first party");
        partiesArray.putValue("second party");
        partiesJson.addNameValuePair(Headings.HABURANA, partiesArray);
        SectionResult partiesResult = new SectionResult(partiesJson, 0);
        parsedCase.set(PARTIES, partiesResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertTrue(validator.arePartiesValid());
    }

    @Test
    public void caseMissingSubjectMatterSectionIsNotValid() {
        ParsingValidator validator = new ParsingValidator(parsedCase);
        assertFalse(validator.isSubjectMatterValid());
    }

    @Test
    public void caseWithEmptySubjectMatterIsNotValid() {
        JsonObject subjectMatterJson = new JsonObject();
        subjectMatterJson.addNameValuePair(SUBJECT_MATTER, new JsonArray());
        SectionResult subjectMatterResult = new SectionResult(subjectMatterJson, 0);
        parsedCase.set(SUBJECT_MATTER, subjectMatterResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isSubjectMatterValid());
    }

    @Test
    public void subjectMatterSectionWithSubheadingAndEmptyArrayIsNotValid() {
        JsonObject subjectMatterContent = new JsonObject();
        subjectMatterContent.addNameValuePair("SUBHEADING", new JsonArray());
        JsonArray subjectMatterArray = new JsonArray();
        subjectMatterArray.putValue(subjectMatterContent);
        JsonObject subjectMatterJson = new JsonObject();
        subjectMatterJson.addNameValuePair(SUBJECT_MATTER, subjectMatterArray);
        SectionResult subjectMatterResult = new SectionResult(subjectMatterJson, 0);
        parsedCase.set(SUBJECT_MATTER, subjectMatterResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isSubjectMatterValid());
    }

    @Test
    public void subjectMatterSectionWithSubheadingAndEmptyStringIsNotValid() {
        JsonObject subjectMatterContent = new JsonObject();
        JsonArray subjectMatterText = new JsonArray();
        subjectMatterText.putValue("");
        subjectMatterContent.addNameValuePair("SUBHEADING", subjectMatterText);
        JsonArray subjectMatterArray = new JsonArray();
        subjectMatterArray.putValue(subjectMatterContent);
        JsonObject subjectMatterJson = new JsonObject();
        subjectMatterJson.addNameValuePair(SUBJECT_MATTER, subjectMatterArray);
        SectionResult subjectMatterResult = new SectionResult(subjectMatterJson, 0);
        parsedCase.set(SUBJECT_MATTER, subjectMatterResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isSubjectMatterValid());
    }

    @Test
    public void subjectMatterSectionWithAtLeastOneSubheadingAndActualContentIsValid() {
        JsonObject subjectMatterContent = new JsonObject();
        JsonArray subjectMatterText = new JsonArray();
        subjectMatterText.putValue("What the subject matter is");
        subjectMatterContent.addNameValuePair("SUBHEADING", subjectMatterText);
        JsonArray subjectMatterArray = new JsonArray();
        subjectMatterArray.putValue(subjectMatterContent);
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
        JsonObject caseBodyJson = new JsonObject();
        caseBodyJson.addNameValuePair(CASE_BODY, new JsonArray());
        SectionResult caseBodyResult = new SectionResult(caseBodyJson, 0);
        parsedCase.set(CASE_BODY, caseBodyResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isCaseBodyValid());
    }

    @Test
    public void caseWithOneCaseBodySubsectionIsNotValid() {
        JsonArray subsectionText = new JsonArray();
        subsectionText.putValue("some body subsection content");
        JsonObject subsection = new JsonObject();
        subsection.addNameValuePair("CASE_BACKGROUND", subsectionText);
        JsonArray bodyArray = new JsonArray();
        bodyArray.putValue(subsection);
        JsonObject caseBodyJson = new JsonObject();
        caseBodyJson.addNameValuePair(CASE_BODY, bodyArray);
        SectionResult caseBodyResult = new SectionResult(caseBodyJson, 0);
        parsedCase.set(CASE_BODY, caseBodyResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isCaseBodyValid());

    }

    @Test
    public void caseBodySectionWithThreeSubsectionsWithActualContentIstValid() {
        JsonArray subsectionText = new JsonArray();
        subsectionText.putValue("some body subsection content");
        JsonObject subsection = new JsonObject();
        subsection.addNameValuePair("CASE_BACKGROUND", subsectionText);
        JsonArray bodyArray = new JsonArray();
        bodyArray.putValue(subsection);
        bodyArray.putValue(subsection);
        bodyArray.putValue(subsection); // Adding subsection three times
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
        JsonObject casePanelJson = new JsonObject();
        casePanelJson.addNameValuePair(INTEKO, new JsonArray());
        SectionResult casePanelResult = new SectionResult(casePanelJson, 0);
        parsedCase.set(INTEKO, casePanelResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isCasePanelValid());
    }

    @Test
    public void caseWithOnePanelistIsNotValid() {
        JsonObject casePanelJson = new JsonObject();
        JsonObject panelist = new JsonObject();
        panelist.addNameValuePair("title", "full name");
        JsonArray panelArray = new JsonArray();
        panelArray.putValue(panelist);
        casePanelJson.addNameValuePair(INTEKO, panelArray);
        SectionResult casePanelResult = new SectionResult(casePanelJson, 0);
        parsedCase.set(INTEKO, casePanelResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isCasePanelValid());
    }

    @Test
    public void caseWithAtLeastTwoPanelistAndNonEmptyTileAndNameIsValid() {
        JsonObject casePanelJson = new JsonObject();
        JsonObject panelist = new JsonObject();
        panelist.addNameValuePair("title", "full name");
        JsonArray panelArray = new JsonArray();
        panelArray.putValue(panelist);
        panelArray.putValue(panelist);  // Added panelist twice
        casePanelJson.addNameValuePair(INTEKO, panelArray);
        SectionResult casePanelResult = new SectionResult(casePanelJson, 0);
        parsedCase.set(INTEKO, casePanelResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertTrue(validator.isCasePanelValid());
    }

    @Test
    public void caseWithWithTwoTitleNamePairsInOneJsonObjectIsNottValid() {
        JsonObject casePanelJson = new JsonObject();
        JsonObject panelist = new JsonObject();
        panelist.addNameValuePair("title", "full name");
        panelist.addNameValuePair("titleTwo", "full name"); // Two key/value pairs
        JsonArray panelArray = new JsonArray();
        panelArray.putValue(panelist);
        panelArray.putValue(panelist);  // Added panelist twice
        casePanelJson.addNameValuePair(INTEKO, panelArray);
        SectionResult casePanelResult = new SectionResult(casePanelJson, 0);
        parsedCase.set(INTEKO, casePanelResult);

        ParsingValidator validator = new ParsingValidator(parsedCase);

        assertFalse(validator.isCasePanelValid());
    }
}
