package com.panavis.primo.Validation;

import  static com.panavis.primo.Constants.Keywords.*;

import com.panavis.primo.Parsers.ParsedCase;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

import java.util.Set;

public class ParsingValidator extends Validator {

    ParsingValidator(ParsedCase parsedCase) {
        super(parsedCase);
    }

    public boolean isTitleValid() {
        boolean validTitle = false;
        if (parsedCase.hasSection(TITLE)) {
            JsonObject titleJson = parsedCase.get(TITLE).getSectionContent();
            String titleText = titleJson.getStringByKey(TITLE);
            validTitle = !titleText.isEmpty();
        }
        return validTitle;
    }

    public boolean arePartiesValid() {
        boolean validParties = false;
        if (parsedCase.hasSection(PARTIES)) {
            JsonObject partiesJson = parsedCase.get(PARTIES).getSectionContent();
            Set<String> keys = partiesJson.getKeys();
            if (hasPartiesSectionHeading(keys)) {
                validParties = hasAtLeastTwoValidSubsections(partiesJson, keys);
            }
        }
        return validParties;
    }

    private boolean hasAtLeastTwoValidSubsections(JsonObject partiesJson, Set<String> keys) {
        boolean validParties;
        String partiesHeading = keys.iterator().next();
        JsonArray partiesArray = partiesJson.getArrayByKey(partiesHeading);
        validParties = partiesArray.getSize() >= 2 && allSubsectionsHaveContent(partiesArray);
        return validParties;
    }

    private boolean hasPartiesSectionHeading(Set<String> keys) {
        return keys.size() == 1;
    }

    public boolean isSubjectMatterValid() {
        boolean subjectMatterValid = false;
        if (parsedCase.hasSection(SUBJECT_MATTER)) {
            JsonObject subjectMatterJson = parsedCase.get(SUBJECT_MATTER).getSectionContent();
            JsonArray sectionArray = subjectMatterJson.getArrayByKey(SUBJECT_MATTER);
            subjectMatterValid = allSubsectionsHaveContent(sectionArray);

        }
        return subjectMatterValid;
    }

    private boolean allSubsectionsHaveContent(JsonArray sectionArray) {
        boolean subjectMatterValid = false;
        for (int i = 0; i < sectionArray.getSize(); i++) {
            JsonObject subsectionJson = getSubsectionJson(sectionArray, i);
            subjectMatterValid = subsectionHasActualContent(subsectionJson);
            if (!subjectMatterValid) break;
        }
        return subjectMatterValid;
    }

    private boolean subsectionHasActualContent(JsonObject subsectionJson) {
        boolean hasContent = false;
        Set<String> subheadings = subsectionJson.getKeys();
        if (subheadings.size() != 0) {
            String heading = subheadings.iterator().next();
            JsonArray subheadingContent = subsectionJson.getArrayByKey(heading);
            if (arrayHasNonEmptyString(subheadingContent))
                hasContent = true;
        }
        return hasContent;
    }

    private JsonObject getSubsectionJson(JsonArray sectionArray, int subsectionIndex) {
        JsonObject firstSubsection = new JsonObject();
        try {
        firstSubsection = sectionArray.getJsonByIndex(subsectionIndex);
        } catch (ClassCastException e) {
            //
        }
        return firstSubsection;
    }

    private boolean arrayHasNonEmptyString(JsonArray subheadingContent) {
        return subheadingContent.getSize() != 0 &&
                !subheadingContent.getStringByIndex(0).isEmpty();
    }

    public boolean isCaseBodyValid(){
        boolean caseBodyValid = false;
        if (parsedCase.hasSection(CASE_BODY)) {
            JsonObject subjectMatterJson = parsedCase.get(CASE_BODY).getSectionContent();
            JsonArray sectionArray = subjectMatterJson.getArrayByKey(CASE_BODY);
            caseBodyValid = sectionArray.getSize() >= 3 && allSubsectionsHaveContent(sectionArray);
        }
        return caseBodyValid;
    }

    public boolean isCasePanelValid() {
        boolean casePanelvalid = false;
        if (parsedCase.hasSection(INTEKO)) {
            JsonObject casePanelJson = parsedCase.get(INTEKO).getSectionContent();
            JsonArray panelArray = casePanelJson.getArrayByKey(INTEKO);
            casePanelvalid = panelArray.getSize() >= 2 && eachPanelistHasTitleAndName(panelArray);
        }
        return casePanelvalid;
    }

    private boolean eachPanelistHasTitleAndName(JsonArray panelArray) {
        int panelSize = panelArray.getSize();
        if (panelSize == 0) return false;
        boolean panelistValid = false;
        for (int i = 0; i < panelSize; i++) {
            panelistValid = isTitleAndNameNonEmpty(panelArray, i);
            if (!panelistValid) break;
        }
        return panelistValid;
    }

    private boolean isTitleAndNameNonEmpty(JsonArray panelArray, int i) {
        boolean nonEmpty = false;
        JsonObject panelist = getSubsectionJson(panelArray, i);
        if (panelist.getKeys().size() == 1) {
            String panelistTitle = panelist.getKeys().iterator().next();
            String panelistName = panelist.getStringByKey(panelistTitle);
            nonEmpty = !panelistTitle.isEmpty() && !panelistName.isEmpty();
        }
        return nonEmpty;
    }
}
