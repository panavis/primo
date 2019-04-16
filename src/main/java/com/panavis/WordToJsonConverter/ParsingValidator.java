package com.panavis.WordToJsonConverter;

import  static com.panavis.WordToJsonConverter.Constants.Keywords.*;

import com.panavis.WordToJsonConverter.Wrappers.JsonArray;
import com.panavis.WordToJsonConverter.Wrappers.JsonObject;

import java.util.Set;

class ParsingValidator {
    private final ParsedCase parsedCase;

    ParsingValidator(ParsedCase parsedCase) {
        this.parsedCase = parsedCase;
    }

    boolean isParsedCaseValid() {
        return isTitleValid() && arePartiesValid() && isSubjectMatterValid() &&
                isCaseBodyValid() && isCasePanelValid();
    }

    boolean isTitleValid() {
        boolean validTitle = false;
        if (parsedCase.hasSection(TITLE)) {
            JsonObject titleJson = parsedCase.get(TITLE).getSectionContent();
            String titleText = titleJson.getStringByKey(TITLE);
            validTitle = !titleText.isEmpty();
        }
        return validTitle;
    }

    boolean arePartiesValid() {
        boolean validParties = false;
        if (parsedCase.hasSection(PARTIES)) {
            JsonObject partiesJson = parsedCase.get(PARTIES).getSectionContent();
            validParties = hasAtLeastTwoParties(partiesJson);
        }
        return validParties;
    }

    private boolean hasAtLeastTwoParties(JsonObject partiesJson) {
        boolean validParties = false;
        Set<String> keys = partiesJson.getKeys();
        if (hasOneHeading(keys))
            validParties = headingHasTwoSubsections(partiesJson, keys);
        return validParties;
    }

    private boolean headingHasTwoSubsections(JsonObject partiesJson, Set<String> keys) {
        boolean validParties = false;
        String partiesHeading = keys.iterator().next();
        JsonArray partiesArray = partiesJson.getArrayByKey(partiesHeading);
        if (hasAtLeastTwoElements(partiesArray)) validParties = true;
        return validParties;
    }

    private boolean hasOneHeading(Set<String> keys) {
        return keys.size() == 1;
    }

    private boolean hasAtLeastTwoElements(JsonArray subsectionArray) {
        return subsectionArray.getSize() >= 2;
    }

    boolean isSubjectMatterValid() {
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

    boolean isCaseBodyValid(){
        boolean caseBodyValid = false;
        if (parsedCase.hasSection(CASE_BODY)) {
            JsonObject subjectMatterJson = parsedCase.get(CASE_BODY).getSectionContent();
            JsonArray sectionArray = subjectMatterJson.getArrayByKey(CASE_BODY);
            caseBodyValid = sectionArray.getSize() >= 3 && allSubsectionsHaveContent(sectionArray);
        }
        return caseBodyValid;
    }

    boolean isCasePanelValid() {
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
