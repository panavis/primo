package com.panavis.primo.Parsers;

import com.panavis.primo.ResultTypes.HeadingParagraphIndex;
import com.panavis.primo.ResultTypes.SectionResult;
import com.panavis.primo.Style.CaseParagraph;
import com.panavis.primo.Utils.JsonCreator;
import com.panavis.primo.Utils.StringFormatting;
import com.panavis.primo.Wrappers.JsonArray;
import com.panavis.primo.Wrappers.JsonObject;

import java.util.ArrayList;
import java.util.List;

import static com.panavis.primo.Constants.Headings.*;
import static com.panavis.primo.Constants.Keywords.*;

public class CasePartiesParser implements ICaseSectionParser {

    private CaseParagraph caseParagraph;
    private Section section;
    private JsonArray partiesSubsections;
    private boolean reachedSubjectMatterSection;
    private int subsectionStart;
    private static final String AND_CONJUNCTION = "na";
    private List<String> ongoingSubsectionWithoutHeading;

    public CasePartiesParser(CaseParagraph caseParagraph, Section sectionParties) {
        this.caseParagraph = caseParagraph;
        this.section = sectionParties;
        this.partiesSubsections = new JsonArray();
        this.reachedSubjectMatterSection = false;
        this.ongoingSubsectionWithoutHeading = new ArrayList<>();
    }

    public SectionResult parse(int startParagraph) {
        HeadingParagraphIndex partiesSectionHeading = findPartiesSectionHeading(startParagraph);
        int paragraphIndex = partiesSectionHeading.getParagraphIndex();
        paragraphIndex = parsePartiesSubsections(paragraphIndex + 1);
        addOngoingSubsectionIfAny();
        verifySubsectionsStructure();
        sanitizePartiesSubsections();
        JsonObject parties = new JsonObject();
        String sectionHeading = partiesSectionHeading.getHeadingName().toUpperCase();
        parties.addNameValuePair(sectionHeading, this.partiesSubsections);
        return new SectionResult(parties, paragraphIndex);
    }

    private void addOngoingSubsectionIfAny() {
        if (ongoingSubsectionWithoutHeading.size() != 0) {
            addSubsectionContent(DEFAULT_PARTY_SUBHEADING, getOngoingSubsectionContent());
        }
    }

    private void verifySubsectionsStructure() {
        JsonArray copy = partiesSubsections;
        for (int i=0; i < partiesSubsections.getSize(); i++) {
            JsonObject subsection = partiesSubsections.getJsonByIndex(i);
            String subheading = (String) subsection.getKeys().toArray()[0];
            String subsectionContent = getSubsectionContent(subsection, subheading);
            subsectionContent = StringFormatting.trimPeriods(subsectionContent);
            if (subheading.equals(DEFAULT_PARTY_SUBHEADING) &&
                endsWithPlaintiffHeading(subsectionContent)) {
                copy = getCopyWithHeadingReplaced(copy, i, UREGA);
            }
            if (subheading.equals(DEFAULT_PARTY_SUBHEADING) &&
                endsWithDefendantHeading(subsectionContent)) {
                copy = getCopyWithHeadingReplaced(copy, i, UREGWA);
            }
        }

        this.partiesSubsections = copy;
    }

    private static JsonArray getCopyWithHeadingReplaced(JsonArray copy, int i, String heading) {
        JsonArray tempCopy = new JsonArray();
        for (int j = 0; j < copy.getSize(); j++) {
            JsonObject nestedSubsection = copy.getJsonByIndex(j);
            String subsectionHeading = (String) nestedSubsection.getKeys().toArray()[0];
            JsonArray nestedContent = nestedSubsection.getArrayByKey(subsectionHeading);
            if (i == j) {
                subsectionHeading = heading;
            }
            JsonObject replacementSubsection = new JsonObject();
            replacementSubsection.addNameValuePair(subsectionHeading, nestedContent);
            tempCopy.putValue(replacementSubsection);
        }
        return tempCopy;
    }

    private static String getSubsectionContent(JsonObject subsection, String subheading) {
        JsonArray textSegments = subsection.getArrayByKey(subheading);
        List<String> subsectionContentRaw = new ArrayList<>();
        for (int j=0; j < textSegments.getSize(); j++) {
            subsectionContentRaw.add(textSegments.getStringByIndex(j));
        }
        return String.join(StringFormatting.LINE_SEPARATOR, subsectionContentRaw);
    }

    private static boolean endsWithPlaintiffHeading(String text) {
        text = text.toLowerCase();
        return text.endsWith("(urega)");
    }

    private static boolean endsWithDefendantHeading(String text) {
        text = text.toLowerCase();
        return text.endsWith("(uregwa)") || text.endsWith("(abaregwa)");
    }


    private void sanitizePartiesSubsections() {
        placeProsecutorInContentIfNoContent();
        combineDuplicatedHeadings();
    }

    private void placeProsecutorInContentIfNoContent() {
        for (int i = 0; i < this.partiesSubsections.getSize(); i++) {
            JsonObject subsection = this.partiesSubsections.getJsonByIndex(i);
            String subsectionHeading = (String) subsection.getKeys().toArray()[0];
            JsonArray subsectionContent = subsection.getArrayByKey(subsectionHeading);
            String firstParagraph = subsectionContent.getSize() > 0 ? subsectionContent.getStringByIndex(0) : "";

            boolean isProsecutor = hasProsecutor(subsectionHeading);
            boolean hasEmptyContent = firstParagraph.equals(StringFormatting.EMPTY_STRING);

            if (isProsecutor && hasEmptyContent) {
                JsonArray defaultContent = new JsonArray();
                defaultContent.putValue(UBUSHINJACYAHA.toUpperCase());
                JsonObject newSubsection = new JsonObject();
                newSubsection.addNameValuePair(DEFAULT_PARTY_SUBHEADING, defaultContent);
                this.partiesSubsections.putValueAtIndex(i, newSubsection);
            }
        }
    }

    private  void combineDuplicatedHeadings() {
        List<String> seenHeadings = new ArrayList<>();
        JsonArray copy = new JsonArray();
        for (int i=0; i < this.partiesSubsections.getSize(); i++) {
            JsonObject nestedSubsection = this.partiesSubsections.getJsonByIndex(i);
            String subsectionHeading = (String) nestedSubsection.getKeys().toArray()[0];
            if (!seenHeadings.contains(subsectionHeading) || subsectionHeading.equals(DEFAULT_PARTY_SUBHEADING)) {
                copy.putValue(nestedSubsection);
                seenHeadings.add(subsectionHeading);
            }
            else {
                JsonArray existingSubsectionContent = getContentForSubsectionHeading(copy, subsectionHeading);
                JsonArray currentSubsectionContent = nestedSubsection.getArrayByKey(subsectionHeading);
                JsonArray combinedSubsectionContent = mergeBothContent(existingSubsectionContent, currentSubsectionContent);
                JsonObject newSubsection = new JsonObject();
                newSubsection.addNameValuePair(subsectionHeading, combinedSubsectionContent);
                JsonArray copyWithoutSection = getCopyWithoutSubsection(copy, subsectionHeading);
                copyWithoutSection.putValue(newSubsection);
                copy = copyWithoutSection;
            }
        }
        this.partiesSubsections = copy;
    }

    private JsonArray getContentForSubsectionHeading(JsonArray subsections, String heading) {
        JsonArray content = new JsonArray();
        for (int i = 0; i < subsections.getSize(); i++) {
            JsonObject nestedSubsection = subsections.getJsonByIndex(i);
            String subsectionHeading = (String) nestedSubsection.getKeys().toArray()[0];
            if (subsectionHeading.equals(heading)) {
                content = nestedSubsection.getArrayByKey(subsectionHeading);
                break;
            }
        }
        return content;
    }

    private JsonArray mergeBothContent(JsonArray existingSubsectionContent, JsonArray currentSubsectionContent) {
        for (int i=0; i < currentSubsectionContent.getSize(); i++) {
            String subsectionParagraph = currentSubsectionContent.getStringByIndex(i);
            existingSubsectionContent.putValue(subsectionParagraph);
        }
        return existingSubsectionContent;
    }

    private JsonArray getCopyWithoutSubsection(JsonArray subsections, String heading) {
        JsonArray copy = new JsonArray();
        for (int i = 0; i < subsections.getSize(); i++) {
            JsonObject nestedSubsection = subsections.getJsonByIndex(i);
            String subsectionHeading = (String) nestedSubsection.getKeys().toArray()[0];
            if (!subsectionHeading.equals(heading)) {
                copy.putValue(nestedSubsection);
            }
        }
        return copy;
    }

    private HeadingParagraphIndex findPartiesSectionHeading(int startParagraph) {
        HeadingParagraphIndex headingAndIndex = findFirstHeading(startParagraph);
        String partiesHeading = headingAndIndex.getHeadingName();
        int paragraphIndex = headingAndIndex.getParagraphIndex();
        if (!isPartySectionHeading(partiesHeading)) {
            partiesHeading = HABURANA;
            paragraphIndex--;
        }
        return new HeadingParagraphIndex(partiesHeading, paragraphIndex);
    }

    private HeadingParagraphIndex findFirstHeading(int startParagraph) {
        String firstHeading = "";
        int paragraphIndex;
        paragraphIndex = startParagraph;
        while (caseParagraph.paragraphExists(paragraphIndex)) {
            firstHeading = getFirstHeading(firstHeading, paragraphIndex);
            if (!firstHeading.isEmpty() && !exceedsPartyHeadingLength(firstHeading))
                break;
            paragraphIndex++;
        }
        return new HeadingParagraphIndex(firstHeading, paragraphIndex);
    }

    private boolean exceedsPartyHeadingLength(String heading) {
        int MAX_PARTIES_HEADING_LENGTH = 3;
        return heading.split(" ").length > MAX_PARTIES_HEADING_LENGTH;
    }

    private String getFirstHeading(String firstHeading, int paragraphIndex) {
        if (this.caseParagraph.isSectionHeading(paragraphIndex)) {
            firstHeading = this.caseParagraph.getHeadingFromParagraph(paragraphIndex);
        }
        return firstHeading;
    }

    private boolean isPartySectionHeading(String partiesHeading) {
        for (String heading : PARTIES_HEADINGS) {
            if (partiesHeading.toUpperCase().contains(heading)) {
                return true;
            }
        }
        return false;
    }

    private int parsePartiesSubsections(int startParagraph) {
        subsectionStart = startParagraph;
        while (caseParagraph.paragraphExists(subsectionStart) && !reachedSubjectMatterSection &&
                startParagraph < caseParagraph.getNumberOfParagraphs()) {
            if (section.startsSubjectMatterSection(subsectionStart)) {
                reachedSubjectMatterSection = true;
                break;
            }
            addPartiesSubsection(subsectionStart);
        }
        return subsectionStart;
    }

    private void addPartiesSubsection(int paragraphIndex) {
        String paragraphText = caseParagraph.getParagraphText(paragraphIndex);
        boolean isProsecutor = startsProsecutorSubsection(paragraphIndex);
        boolean isHeading = this.caseParagraph.isSectionHeading(paragraphIndex);
        String heading = caseParagraph.getHeadingFromParagraph(paragraphIndex);

        if (isHeading && !hasProsecutor(heading)) {
            parseAndAddNormalSubsection(paragraphIndex);
        }
        else if (isProsecutor) {
            parseAndAddCriminalCaseProsecutorSubsection(paragraphIndex, paragraphText);
        }
        else {
            parseAndAddPartyWithoutExplicitHeading(paragraphIndex);
        }
    }

    private void parseAndAddNormalSubsection(int startParagraph) {
        String inlineParagraph = caseParagraph.getInlineHeadingFirstParagraph(startParagraph);
        section.setStartingParagraph(startParagraph)
                .setInlineParagraph(inlineParagraph)
                .parse();
        String subsectionName = caseParagraph.getHeadingFromParagraph(startParagraph);
        addSubsectionContent(subsectionName, section.getBody());
        updateSubsectionStart(section.getLastParagraph());
    }

    private void addSubsectionContent(String subsectionName, JsonArray subsectionBody) {
        this.partiesSubsections.putValue(JsonCreator.getJsonObject(subsectionName, subsectionBody));
    }

    private void updateSubsectionStart(int paragraphIndex) {
        subsectionStart = paragraphIndex;
    }

    private boolean startsProsecutorSubsection(int paragraphIndex) {
        String text = caseParagraph.getParagraphText(paragraphIndex);
        return hasProsecutor(text) &&
                previousParagraphIsNotSubsectionHeading(paragraphIndex);
    }

    private boolean hasProsecutor(String text) {
        return text.toLowerCase().contains(UBUSHINJACYAHA);
    }

    private void parseAndAddCriminalCaseProsecutorSubsection(int startParagraph, String paragraphText) {
        boolean conjunctionOnNextLine = hasConjunctionOnNextLine(startParagraph);
        boolean conjunctionOnSameLine = paragraphText.toLowerCase().endsWith(" " + AND_CONJUNCTION);

        if (conjunctionOnNextLine || conjunctionOnSameLine) {
            JsonArray sectionBody = new JsonArray();
            sectionBody.putValue(paragraphText);
            addSubsectionContent(DEFAULT_PARTY_SUBHEADING, sectionBody);
            int nextSubsectionStart = conjunctionOnSameLine ? startParagraph + 1 : startParagraph + 2;
            updateSubsectionStart(nextSubsectionStart);
        }
        else {
            section.setStartingParagraph(startParagraph)
                    .setInlineParagraph(paragraphText)
                    .parse();
            addSubsectionContent(UBUSHINJACYAHA, section.getBody());
            updateSubsectionStart(section.getLastParagraph());
        }
    }

    private boolean hasConjunctionOnNextLine(int paragraphIndex) {
        String text = "";
        if (caseParagraph.paragraphExists(paragraphIndex + 1))
            text = caseParagraph.getParagraphText(paragraphIndex + 1);
        return text.toLowerCase().equals(AND_CONJUNCTION) ;
    }

    private boolean previousParagraphIsNotSubsectionHeading(int paragraphIndex) {
        if (paragraphIndex - 1 < 0) return true;
        String pP = caseParagraph.getHeadingFromParagraph(paragraphIndex - 1).toUpperCase();
        return !caseParagraph.isSectionHeading(paragraphIndex - 1) ||
                PARTIES_HEADINGS.contains(pP);
    }

    private void parseAndAddPartyWithoutExplicitHeading(int paragraphIndex) {
        String textContent = caseParagraph.getParagraphText(paragraphIndex);
        boolean isConjunction = textContent.toLowerCase().equals(AND_CONJUNCTION);
        int blanksAfter = caseParagraph.getNumberOfPostParagraphBlanks(paragraphIndex);

        if (isConjunction && ongoingSubsectionWithoutHeading.size() != 0) {
            addSubsectionContent(DEFAULT_PARTY_SUBHEADING, getOngoingSubsectionContent());
            ongoingSubsectionWithoutHeading = new ArrayList<>();
        }
        else if (!isConjunction) {
            if (blanksAfter >= 2) {
                ongoingSubsectionWithoutHeading.add(textContent);

                addSubsectionContent(DEFAULT_PARTY_SUBHEADING, getOngoingSubsectionContent());
                ongoingSubsectionWithoutHeading = new ArrayList<>();
            } else {
                ongoingSubsectionWithoutHeading.add(textContent);
            }
        }
        updateSubsectionStart(paragraphIndex + 1);
    }

    private JsonArray getOngoingSubsectionContent() {
        String content = String.join(StringFormatting.LINE_SEPARATOR, ongoingSubsectionWithoutHeading);
        JsonArray subsectionContent = new JsonArray();
        subsectionContent.putValue(content);
        return subsectionContent;
    }

    public boolean skippedParagraphs() {
        return false;
    }
}
