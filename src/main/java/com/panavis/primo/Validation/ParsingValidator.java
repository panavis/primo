package com.panavis.primo.Validation;

import static com.panavis.primo.Constants.Headings.IKIREGO;
import static com.panavis.primo.Constants.Headings.URUKIKO;
import  static com.panavis.primo.Constants.Keywords.*;

import com.panavis.primo.Parsers.ParsedCase;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

import java.util.Set;

public class ParsingValidator extends Validator {

    public ParsingValidator(ParsedCase parsedCase) {
        super(parsedCase);
    }

    public boolean isTitleValid() {
        boolean validTitle = false;
        if (parsedCase.hasSection(CASE_TITLE)) {
            JsonObject titleJson = parsedCase.get(CASE_TITLE).getSectionContent();
            String titleText = titleJson.getStringByKey(CASE_TITLE);
            validTitle = !titleText.isEmpty();
        }
        return validTitle;
    }

    public boolean arePartiesValid() {
        boolean validParties = false;
        if (parsedCase.hasSection(CASE_PARTIES)) {
            JsonObject partiesJson = parsedCase.get(CASE_PARTIES).getSectionContent();
            Set<String> keys = partiesJson.getKeys();
            if (hasPartiesSectionHeading(keys)) {
                JsonArray partiesSubjections = getArrayFromJsonObject(partiesJson, keys);
                validParties = hasAtLeastTwoValidSubsections(partiesSubjections) ||
                                hasAtLeastOnePartyAndIkiregoKeywordInHeading(partiesSubjections);
            }
        }
        return validParties;
    }

    private boolean hasAtLeastTwoValidSubsections(JsonArray partiesArray) {
        return partiesArray.getSize() >= 2 && allSubsectionsHaveContent(partiesArray);
    }

    private boolean hasAtLeastOnePartyAndIkiregoKeywordInHeading(JsonArray partiesArray) {
        return partiesArray.getSize() >= 1 && hasIkiregoKeywordInHeading(partiesArray) &&
                allSubsectionsHaveContent(partiesArray);
    }

    private boolean hasIkiregoKeywordInHeading(JsonArray partiesArray) {
        Set<String> headings = partiesArray.getJsonByIndex(0).getKeys();
        String firstHeading = headings.isEmpty() ? "" : headings.iterator().next();
        return firstHeading.toUpperCase().contains(IKIREGO);
    }

    private JsonArray getArrayFromJsonObject(JsonObject partiesJson, Set<String> keys) {
        String partiesHeading = keys.iterator().next();
        return partiesJson.getArrayByKey(partiesHeading);
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
            JsonArray subheadingContent = getArrayFromJsonObject(subsectionJson, subheadings);
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
            JsonObject caseBodyJson = parsedCase.get(CASE_BODY).getSectionContent();
            JsonArray sectionArray = caseBodyJson.getArrayByKey(CASE_BODY);
            caseBodyValid = (
                                (sectionArray.getSize() >= 3 && allSubsectionsHaveContent(sectionArray)) ||
                                (sectionArray.getSize() >= 2 && hasOldCaseBodyFormat(sectionArray))
                            )
                            &&
                            !(sectionArray.getSize() > 10);
        }
        return caseBodyValid;
    }

    private boolean hasOldCaseBodyFormat(JsonArray caseBody) {
        JsonObject firstSection = caseBody.getJsonByIndex(0);
        String sectionHeading = firstSection.getKeys().size() > 0 ?
                (String) firstSection.getKeys().toArray()[0] : "";
        return sectionHeading.toUpperCase().contains(URUKIKO);

    }

    public boolean isCasePanelValid() {
        boolean casePanelValid = false;
        if (parsedCase.hasSection(INTEKO)) {
            JsonObject casePanelJson = parsedCase.get(INTEKO).getSectionContent();
            JsonArray panelArray = casePanelJson.getArrayByKey(INTEKO);
            casePanelValid = panelArray.getSize() >= 2 && eachPanelistHasTitleAndName(panelArray);
        }
        return casePanelValid;
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
